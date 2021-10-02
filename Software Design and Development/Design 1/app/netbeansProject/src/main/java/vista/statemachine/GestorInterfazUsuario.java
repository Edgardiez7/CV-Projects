/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.statemachine;

import vista.identificarse.VistaIdentificarse;
import javax.swing.JFrame;
import vista.opcionesAlmacen.buscarPc.VistaAlmacenBuscarPc;
import vista.opcionNoImplementada.VistaOpcionNoImplementada;
import vista.opcionesAlmacen.VistaOpcionesAlmacen;
import vista.opcionesGerente.VistaOpcionesGerente;
import vista.opcionesGerente.procesarPedido.VistaGerenteListaComponentes;
import vista.opcionesGerente.procesarPedido.VistaGerenteProcesarPedido;
import vista.opcionesGerente.procesarPedido.VistaMensajeConfirmacion;
import vista.opcionesTaller.VistaOpcionesTaller;
import vista.opcionesTaller.RegistrarMontaje.VistaRegistrarMontaje;

/**
 *
 * @author edgarpopos
 */
public class GestorInterfazUsuario {

    private static JFrame currentState;

    public GestorInterfazUsuario() {
        mostrarLogin();
    }

    /*
    Muestra la vista de login
     */
    public static void mostrarLogin() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (currentState != null) {
                    currentState.dispose();
                }
                currentState = new VistaIdentificarse();
                currentState.setVisible(true);
            }
        });
    }

    public static void mostrarOpcionesAlmacen() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (currentState != null) {
                    currentState.dispose();
                }
                currentState = new VistaOpcionesAlmacen();
                currentState.setVisible(true);
            }
        });
    }

    public static void mostrarOpcionesGerente() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (currentState != null) {
                    currentState.dispose();
                }
                currentState = new VistaOpcionesGerente();
                currentState.setVisible(true);
            }
        });
    }

    public static void mostrarOpcionesTaller() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (currentState != null) {
                    currentState.dispose();
                }
                currentState = new VistaOpcionesTaller();
                currentState.setVisible(true);
            }
        });
    }

    public static void mostrarOpcionNoImplementada() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (currentState != null) {
                    currentState.dispose();
                }
                currentState = new VistaOpcionNoImplementada();
                currentState.setVisible(true);
            }
        });
    }

    public static void mostrarAlmacenPc() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (currentState != null) {
                    currentState.dispose();
                }
                currentState = new VistaAlmacenBuscarPc();
                currentState.setVisible(true);
            }
        });
    }
    
    public static void mostrarGerenteProcesarPedido() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (currentState != null) {
                    currentState.dispose();
                }
                currentState = new VistaGerenteProcesarPedido();
                currentState.setVisible(true);
            }
        });
    }
    
    public static void mostrarGerenteListaComponentes() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (currentState != null) {
                    currentState.dispose();
                }
                currentState = new VistaGerenteListaComponentes();
                currentState.setVisible(true);
            }
        });
    }
    
    public static void mostrarMensajeConfirmacion() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (currentState != null) {
                    currentState.dispose();
                }
                currentState = new VistaMensajeConfirmacion();
                currentState.setVisible(true);
            }
        });
    }
    
    public static void mostrarRegistrarMontaje(){
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 if (currentState != null) {
                    currentState.dispose();
                }
                currentState = new VistaRegistrarMontaje();
                currentState.setVisible(true);
            }
        });
    }    

}
