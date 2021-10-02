/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.opcionesTaller;

import dominio.modelo.SesionSingletonEmpleado;
import vista.statemachine.GestorInterfazUsuario;
import vista.statemachine.GestorInterfazUsuario;
/**
 *
 * @author edgarpopos
 */
public class CtrlVistaOpcionesTaller {
    private VistaOpcionesTaller vista;
    
    public CtrlVistaOpcionesTaller(VistaOpcionesTaller v){
        vista=v;
    }
     
    public void logout(){
        SesionSingletonEmpleado.destroyInstancia();
        GestorInterfazUsuario.mostrarLogin();
    }
    
    public void registrarMontajePC(){
        GestorInterfazUsuario.mostrarRegistrarMontaje();
    }
}
