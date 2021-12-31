/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.opcionesGerente.procesarPedido;

import dominio.modelo.PedidoPCs;
import dominio.controladoresCU.ControladorCUProcesarPedido;
import dominio.modelo.SesionPedido;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import vista.statemachine.GestorInterfazUsuario;

/**
 *
 * @author miris
 */
public class CtrlVistaGerenteProcesarPedido {

    private VistaGerenteProcesarPedido vista;
    private ControladorCUProcesarPedido controladorCU;

    public CtrlVistaGerenteProcesarPedido(VistaGerenteProcesarPedido v) {
        vista = v;
        controladorCU = new ControladorCUProcesarPedido();
        controladorCU.destroySesion();
    }

    public void procesarSolicitudDeReserva() {
        GestorInterfazUsuario.mostrarGerenteListaComponentes();
    }

    public void solicitaConfirmacion() {
        GestorInterfazUsuario.mostrarMensajeConfirmacion();
    }

    public void cancelar() {
        GestorInterfazUsuario.mostrarOpcionesGerente();
    }

    public void rellenarPedidosSolicitados() {
        ArrayList<Integer> lista = controladorCU.rellenarPedidosSolicitados();
        DefaultListModel<String> listModel = new DefaultListModel();
        vista.getJList2().setModel(listModel);
        for (Integer pedido : lista) {
            listModel.addElement("Pedido nº " + pedido);
        }
        if (lista.isEmpty()) {
            listModel.addElement("¡No hay ningún pedido en estado Solicitado!");
        }
    }

    public void mostrarInfoPedido(String pedido) throws ParseException {
        vista.getJTextArea1().setText("");
        PedidoPCs infoPedido = controladorCU.mostrarInfoPedido(pedido);
        if (infoPedido != null) {
            vista.getJTextArea1().append(infoPedido.toString());
        }
    }

    public int getCantidadRestante() {
        return controladorCU.getCantidadRestante();
    }

    public void mostrarCantidadPCsDisponibles() throws ParseException {
        vista.getJTextArea2().setText("");
        PedidoPCs infoPedido = SesionPedido.getSesionPedido();/////////////////////////////////////////////////////
        int cantidadPedida = infoPedido.getCantidad();
        int cantidad = controladorCU.contarPCsDisponibles();
        int cantidadRestante = cantidadPedida - cantidad;
        if (cantidadRestante < 0) {
            cantidadRestante = 0;
        }
        int cantidadReserva = cantidadPedida - cantidadRestante;
        controladorCU.setCantidadRestante(cantidadRestante);
        vista.getJTextArea2().setText("La cantidad de PCs montados es de: " + cantidad + "\nAún se necesitan: " + cantidadRestante + " PCs por montar" + "\nSe van a reservar: " + cantidadReserva + " PC");
    }

    public void marcarComoReservado() {
        controladorCU.marcarComoReservado();
    }

    public void PCsMontadosSuficientes() {
        controladorCU.pedidoCompletado();
    }

    public void showButtonDisponibilidad() {
        JButton jButton = vista.getjButton5();
        jButton.setEnabled(true);
    }

}
