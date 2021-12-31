/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.controladoresCU;

import dominio.modelo.ConfiguracionPC;
import dominio.modelo.PedidoPCs;
import dominio.modelo.SesionComandos;
import dominio.modelo.SesionPedido;
import dominio.modelo.SesionSingletonEmpleado;
import java.text.ParseException;
import java.util.ArrayList;
import persistencia.DAOs.ConfiguracionDAO;
import persistencia.DAOs.PedidoPCsDAO;

/**
 *
 * @author edgarpopos
 */
public class ControladorCURegistrarMontaje {

    public ArrayList<ConfiguracionPC> mostrarConfiguracionesPcs() {
        ArrayList<ConfiguracionPC> arrayConfigs = new ArrayList<>();
        ArrayList<String> arrayC = ConfiguracionDAO.consultarConfigs();
        for (String json : arrayC) {
            ConfiguracionPC c = new ConfiguracionPC(json);
            arrayConfigs.add(c);
        }

        return arrayConfigs;
    }

    public void destroySesion() {
        SesionPedido.destroySesion();
        SesionComandos.destroyComandos();
    }

    public ArrayList<PedidoPCs> mostrarPedidosAsociados(String idConfiguracion) throws ParseException {

        int idConf = Integer.parseInt(idConfiguracion.replaceAll("[\\D]", ""));
        ArrayList<PedidoPCs> arrayPedidos = new ArrayList<>();
        ArrayList<String> arrayC = ConfiguracionDAO.consultarPedidosAsociados(idConf);
        for (String json : arrayC) {
            PedidoPCs p = new PedidoPCs(json);
            arrayPedidos.add(p);
        }

        return arrayPedidos;
    }

    public boolean compruebaEtiquetaPc(String e) {
        int etiqueta = Integer.parseInt(e);
        if (PedidoPCsDAO.existePcEtiqueta(etiqueta)) {
            return true;
        } else {
            SesionComandos.addComandos("INSERT INTO PC VALUES (" + etiqueta + ",true,CURRENT_DATE," + SesionPedido.getSesionPedido().getConfiguracion().getIdConf() + ",'" + SesionSingletonEmpleado.getInstancia().getDNI() + "',NULL," + SesionPedido.getSesionPedido().getID() + ")");
            return false;
        }
    }

    public void guardarPedido(String id) throws ParseException {
        String idParseado = id.replaceAll("[\\D]", "");
        if (!idParseado.equals("") && !idParseado.equals(null)) {
            int idPedido = Integer.parseInt(idParseado);
            PedidoPCs pedido = new PedidoPCs(PedidoPCsDAO.consultarInfoPedido(idPedido));
            SesionPedido.setSesionPedido(pedido);
        }
    }

    public boolean compruebaEtiquetaComponente(String eC, String ePc) {
        int etiquetaComp = Integer.parseInt(eC);
        int etiquetaPc = Integer.parseInt(ePc);
        if (PedidoPCsDAO.existeComponenteEtiqueta(etiquetaComp)) {
            if (!SesionComandos.getComandos().contains("UPDATE COMPONENTE SET ETIQUETAPC = " + etiquetaPc + " WHERE IDETIQUETA = " + etiquetaComp)) {
                SesionComandos.addComandos("UPDATE COMPONENTE SET ETIQUETAPC = " + etiquetaPc + " WHERE IDETIQUETA = " + etiquetaComp);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean compruebaNumComponentes() {
        int numCompNecesarios = PedidoPCsDAO.compruebaNumComponentes(SesionPedido.getSesionPedido().getConfiguracion().getIdConf());
        int numCompIntroducidos = SesionComandos.getComandos().size();

        // El -1 es porque tenemos metida la query para introducir el PC montado.
        if (numCompIntroducidos - 1 < numCompNecesarios) {
            return false;
        } else {
            return true;
        }

    }

    public void ejecutarComandos() {
        int idPedido = SesionPedido.getSesionPedido().getID();
        PedidoPCsDAO.ejecutarTodo();
        int numPCsAsociados = PedidoPCsDAO.compruebaNumPCsEnPedido(idPedido);
        if (SesionPedido.getSesionPedido().getCantidad() == numPCsAsociados) {
            SesionComandos.addComandos("UPDATE PEDIDOPC SET ESTADO = " + 3 + " WHERE IDPEDIDO = " + idPedido);
            PedidoPCsDAO.pedidoCompletado(idPedido);

        }

    }

}
