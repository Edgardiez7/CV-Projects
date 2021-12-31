/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.opcionesGerente.procesarPedido;

import dominio.controladoresCU.ControladorCUProcesarPedido;
import vista.statemachine.GestorInterfazUsuario;

/**
 *
 * @author miris
 */
public class CtrlVistaMensajeConfirmacion {

    private VistaMensajeConfirmacion vista;
    private ControladorCUProcesarPedido controlador;

    public CtrlVistaMensajeConfirmacion(VistaMensajeConfirmacion v) {
    controlador  = new ControladorCUProcesarPedido();

    vista  = v;
}

public void cancelar() {
        GestorInterfazUsuario.mostrarOpcionesGerente();
    }

    void aceptar() {
        controlador.aceptar();
        GestorInterfazUsuario.mostrarOpcionesGerente();
    }
}
