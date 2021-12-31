/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.controladoresCU;

import persistencia.DAOs.PedidoPCsDAO;
import dominio.modelo.PedidoPCs;
import dominio.modelo.SesionComandos;
import dominio.modelo.SesionPedido;
import java.text.ParseException;
import java.util.ArrayList;
import persistencia.DAOs.ComponenteDAO;

/**
 *
 * @author gonzalo
 */
public class ControladorCUProcesarPedido {

    private ArrayList<Integer> listaIDsComponentes;
    private ArrayList<Integer> componentesEnPC;

    public ArrayList<Integer> rellenarPedidosSolicitados() {
        return PedidoPCsDAO.consultarPedidosSolicitados();
    }

    public PedidoPCs mostrarInfoPedido(String infoPedido) throws ParseException {
        if (infoPedido.matches(".*\\d.*")) {
            int idPedido = Integer.parseInt(infoPedido.replaceAll("[\\D]", ""));
            PedidoPCs pedidoPc = new PedidoPCs(PedidoPCsDAO.consultarInfoPedido(idPedido));
            SesionPedido.setSesionPedido(pedidoPc);

            return SesionPedido.getSesionPedido();
        }
        return null;
    }

    public int contarPCsDisponibles() {
        return PedidoPCsDAO.consultarCantidadPCs(SesionPedido.getSesionPedido());
    }

    public void marcarComoReservado() {
        PedidoPCsDAO.marcarPCsComoReservado(SesionPedido.getSesionPedido(), SesionPedido.getSesionPedido().getCantidad()); //HACEMOS QUERY!
    }

    public void setCantidadRestante(int cantidad) {
        SesionPedido.setCantidadRestante(cantidad);
    }

    public int getCantidadRestante() {
        return SesionPedido.getCantidadRestante();
    }

    public ArrayList<Integer> procesarComponentes() {
        ArrayList<Integer> listaIDsComponentes = PedidoPCsDAO.procesarComponentes(SesionPedido.getSesionPedido());
        ArrayList<Integer> disponibilidades = new ArrayList<>();
        ArrayList<Integer> componentesEnPC = new ArrayList<>();
        int i = 0;
        for (Integer l : listaIDsComponentes) {
            disponibilidades.add(ComponenteDAO.disponibilidadComponente(l));
            componentesEnPC.add(PedidoPCsDAO.cantidadComponentesEnPC(l));
            if (disponibilidades.get(i) < componentesEnPC.get(i) * SesionPedido.getCantidadRestante()) {
                return null;
            }
            i++;
        }
        setComponentesEnPC(componentesEnPC);
        setListaIDsComponentes(listaIDsComponentes);

        return disponibilidades;
    }

    public void pedidoEnProceso() {
        SesionComandos.addComandos("UPDATE PEDIDOPC SET ESTADO = 2  WHERE IDPEDIDO = " + SesionPedido.getSesionPedido().getID());
    }

    public void pedidoCompletado() {
        SesionComandos.addComandos("UPDATE PEDIDOPC SET ESTADO = 3  WHERE IDPEDIDO = " + SesionPedido.getSesionPedido().getID());
    }

    public void aceptar() {
        PedidoPCsDAO.ejecutarTodo();
    }

    public void setComponentesEnPC(ArrayList<Integer> lista) {
        componentesEnPC = lista;
    }

    public void setListaIDsComponentes(ArrayList<Integer> lista) {
        listaIDsComponentes = lista;
    }

    public ArrayList<Integer> setComponentesEnPC() {
        return componentesEnPC;
    }

    public ArrayList<Integer> setListaIDsComponentes() {
        return listaIDsComponentes;
    }

    public void destroySesion() {
        SesionPedido.destroySesion();
        SesionComandos.destroyComandos();
    }
}
