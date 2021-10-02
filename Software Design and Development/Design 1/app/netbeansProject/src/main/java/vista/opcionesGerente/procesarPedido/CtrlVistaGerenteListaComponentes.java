/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.opcionesGerente.procesarPedido;

import dominio.controladoresCU.ControladorCUProcesarPedido;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import vista.statemachine.GestorInterfazUsuario;

/**
 *
 * @author miris
 */
public class CtrlVistaGerenteListaComponentes {

    private VistaGerenteListaComponentes vista;
    private ControladorCUProcesarPedido controladorCU;

    public CtrlVistaGerenteListaComponentes(VistaGerenteListaComponentes v) {
        vista = v;
        controladorCU = new ControladorCUProcesarPedido();
    }

    public void procesarOrdenDeMontaje() throws SQLException {
        GestorInterfazUsuario.mostrarMensajeConfirmacion();
    }

    public void volverAlMenu() {
        GestorInterfazUsuario.mostrarOpcionesGerente();
    }

    public void opcionNoImplementada() {
        GestorInterfazUsuario.mostrarOpcionNoImplementada();
    }

    public void mostrarComponentes() {
        ArrayList<Integer> componentesDisponibles = controladorCU.procesarComponentes();
        if (componentesDisponibles == null) {
            vista.getJtextArea1().append("Error cantidad disponible");
            mostrarBotones();
        } else {

            for (int i = 0; i < componentesDisponibles.size(); i++) {
                vista.getJtextArea1().append("Componente " + i + " , Disponibles: " + componentesDisponibles.get(i) + "\n");

            }

            controladorCU.pedidoEnProceso();

        }
    }
    
    public void ocultarBotones() {
        JButton jButton1 = vista.getJButton1();
        JButton jButton2 = vista.getJButton2();
        JButton jButton3 = vista.getJButton3();
        jButton1.setVisible(false);
        jButton1.setEnabled(false);
        jButton3.setVisible(false);
        jButton3.setEnabled(false);

        jButton2.setVisible(true);
        jButton2.setEnabled(true);
    }
    public void mostrarBotones() {
        JButton jButton1 = vista.getJButton1();
        JButton jButton2 = vista.getJButton2();
        JButton jButton3 = vista.getJButton3();
        jButton1.setVisible(true);
        jButton1.setEnabled(true);
        jButton3.setVisible(true);
        jButton3.setEnabled(true);

        jButton2.setVisible(false);
        jButton2.setEnabled(false);
    }

}
