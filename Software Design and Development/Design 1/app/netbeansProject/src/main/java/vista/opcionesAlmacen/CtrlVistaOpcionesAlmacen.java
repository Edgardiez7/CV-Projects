/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.opcionesAlmacen;

import dominio.modelo.SesionSingletonEmpleado;
import vista.statemachine.GestorInterfazUsuario;

/**
 *
 * @author edgarpopos
 */
public class CtrlVistaOpcionesAlmacen {
    private VistaOpcionesAlmacen vista; 
    
    public CtrlVistaOpcionesAlmacen(VistaOpcionesAlmacen v){
        vista=v;
    }
    
    public void logout(){
        SesionSingletonEmpleado.destroyInstancia();
        GestorInterfazUsuario.mostrarLogin();
    }
    public void opcionNoImplementada(){
        GestorInterfazUsuario.mostrarOpcionNoImplementada();
    }
    public void buscarEspacioDisponiblePC(){
        GestorInterfazUsuario.mostrarAlmacenPc();
    }
}
