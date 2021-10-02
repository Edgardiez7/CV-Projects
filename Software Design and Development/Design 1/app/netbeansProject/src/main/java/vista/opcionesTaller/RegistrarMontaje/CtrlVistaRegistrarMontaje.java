/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.opcionesTaller.RegistrarMontaje;

import dominio.controladoresCU.ControladorCURegistrarMontaje;
import dominio.modelo.ConfiguracionPC;
import dominio.modelo.PedidoPCs;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import vista.statemachine.GestorInterfazUsuario;
import vista.statemachine.GestorInterfazUsuario;

/**
 *
 * @author edgarpopos
 */
public class CtrlVistaRegistrarMontaje {

    private VistaRegistrarMontaje vista;
    private ControladorCURegistrarMontaje controlador;

    public CtrlVistaRegistrarMontaje(VistaRegistrarMontaje v) {
        vista = v;
        controlador = new ControladorCURegistrarMontaje();
        controlador.destroySesion();
    }

    public void mostrarConfiguracionesPcs() {
        ArrayList<ConfiguracionPC> arrayConfs = controlador.mostrarConfiguracionesPcs();
        DefaultListModel<String> listModel = new DefaultListModel();
        vista.getPanelPc().setModel(listModel);
        for (ConfiguracionPC conf : arrayConfs) {
            listModel.addElement("Configuración id: " + conf.getIdConf());
        }
        if (arrayConfs.isEmpty()) {
            listModel.addElement("¡Ninguna configuración en la BD!");
        }
    }

    public void mostarPedidosAsociados(String idConfiguracion) throws ParseException {
        ArrayList<PedidoPCs> arrayPedidos = controlador.mostrarPedidosAsociados(idConfiguracion);
        DefaultListModel<String> listModel = new DefaultListModel();
        vista.getJListPedido().setModel(listModel);
        for (PedidoPCs p : arrayPedidos) {
            listModel.addElement("Pedido id: " + p.getID());

        }
        if (arrayPedidos.isEmpty()) {
            listModel.addElement("¡Ningun pedido asociado!");
            vista.getTextoEtiquetaPc().setEnabled(false);
            //vista.getTextoEtiquetaPc().setEditable(false);
            vista.getBotonEtiqueta().setEnabled(false);
        }
    }

    public void seleccionaPedido(String idPedido) throws ParseException {
        controlador.guardarPedido(idPedido);

        if (!idPedido.equals("¡Ningun pedido asociado!")) {
            vista.getTextoEtiquetaPc().setEnabled(true);
            //vista.getTextoEtiquetaPc().setEditable(true);
            vista.getBotonEtiqueta().setEnabled(true);
        }

    }

    void volverMenuTaller() {
        GestorInterfazUsuario.mostrarOpcionesTaller();
    }

    public void compruebaEtiquetaPc() {
        if (!controlador.compruebaEtiquetaPc(vista.getTextoEtiquetaPc().getText())) {
            vista.getLabelErrores().setText("");

            vista.getTextoEtiquetaComponente().setEnabled(true);
            vista.getBotonComponente().setEnabled(true);
            vista.getTextoEtiquetaPc().setEnabled(false);
            vista.getBotonEtiqueta().setEnabled(false);
            vista.getPanelPc().setEnabled(false);
            vista.getJListPedido().setEnabled(false);
        } else {
            vista.getLabelErrores().setText("Ya existe un PC con esta etiqueta");
        }
    }

    void compruebaEtiquetaComponente() {
        if (controlador.compruebaEtiquetaComponente(vista.getTextoEtiquetaComponente().getText(), vista.getTextoEtiquetaPc().getText())) {

            vista.getLabelErrores().setText("");
            vista.getBotonAceptar().setEnabled(true);

        } else {
            vista.getLabelErrores().setText("No existe componente con esta etiqueta o está asignado");
        }
    }

    void comprobarNumComponentes() {
        if (controlador.compruebaNumComponentes()) {
            controlador.ejecutarComandos();
            volverMenuTaller();
        } else {
            vista.getLabelErrores().setText("Aun faltan componentes");
        }
    }

}
