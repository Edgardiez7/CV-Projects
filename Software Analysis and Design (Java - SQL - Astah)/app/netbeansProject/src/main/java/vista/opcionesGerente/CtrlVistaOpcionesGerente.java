/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.opcionesGerente;

import dominio.modelo.SesionSingletonEmpleado;
import vista.statemachine.GestorInterfazUsuario;

/**
 *
 * @author edgarpopos
 */
public class CtrlVistaOpcionesGerente {
    private VistaOpcionesGerente vista; 
    
    public CtrlVistaOpcionesGerente(VistaOpcionesGerente v){
        vista=v;
    }
    
    public void logout(){
        SesionSingletonEmpleado.destroyInstancia();
        GestorInterfazUsuario.mostrarLogin();
    }
    public void opcionNoImplementada(){
        GestorInterfazUsuario.mostrarOpcionNoImplementada();
    }
    public void procesarPedidoPC(){
        GestorInterfazUsuario.mostrarGerenteProcesarPedido();
    }
    
}
