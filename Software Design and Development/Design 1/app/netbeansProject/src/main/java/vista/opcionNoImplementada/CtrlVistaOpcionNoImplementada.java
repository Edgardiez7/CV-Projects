/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.opcionNoImplementada;

import dominio.modelo.SesionSingletonEmpleado;
import vista.statemachine.GestorInterfazUsuario;

/**
 *
 * @author edgarpopos
 */
public class CtrlVistaOpcionNoImplementada {
    private VistaOpcionNoImplementada vista; 
    
    public CtrlVistaOpcionNoImplementada(VistaOpcionNoImplementada v){
        vista=v;
    }
     
    public void volverMenuPrincipal(){
        switch(SesionSingletonEmpleado.getInstancia().getRolActivo().getTipoRol()){
            case PersonalAlmacen:
                GestorInterfazUsuario.mostrarOpcionesAlmacen();
                break;
            case GerenteVentas:
                GestorInterfazUsuario.mostrarOpcionesGerente();
                break;
            case TecnicoDelTaller:
                GestorInterfazUsuario.mostrarOpcionesTaller();
                break;
            default:
                GestorInterfazUsuario.mostrarLogin();
        }
    }
}
