/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.opcionesAlmacen.buscarPc;

import dominio.controladoresCU.ControladorCUBuscarEspacioPc;
import dominio.modelo.EspacioAlmacenamiento;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import vista.statemachine.GestorInterfazUsuario;

/**
 *
 * @author gonzalo
 */
public class CtrlVistaAlmacenBuscarPc {

    private static final int ERRORCANTIDAD = 1;
    private static final int CPUNOEXISTE = 2;
    private VistaAlmacenBuscarPc vista;
    private String tipoCpu;
    private String cantidad;
    private ControladorCUBuscarEspacioPc controlador;

    public CtrlVistaAlmacenBuscarPc(VistaAlmacenBuscarPc v) {
        vista = v;
        controlador = new ControladorCUBuscarEspacioPc();
    }

    public void cancelar() {
        GestorInterfazUsuario.mostrarOpcionesAlmacen();
    }

    public void confirmar() {
        tipoCpu = vista.getTipoCpu();
        cantidad = vista.getCantidad();
        resetResultado();
        if (tipoCpu != null && cantidad != null) {
            int error = controlador.validarDatos(tipoCpu, cantidad);
            switch (error) {
                case ERRORCANTIDAD:
                    mensajeErrorCantidadIncorrecta();
                    break;
                case CPUNOEXISTE:
                    mensajeErrorCpu();
                    break;
                default:    
                    clearAll();
                    ArrayList<EspacioAlmacenamiento> listaEspacio = controlador.procesarPeticion(tipoCpu);                    
                    if(listaEspacio.size()<Integer.parseInt(cantidad)){
                        int faltan = Integer.parseInt(cantidad) - listaEspacio.size();
                        mensajeErrorEspacio(listaEspacio, faltan);
                    }
                    else{
                        espacioCorrecto(listaEspacio);
                    }
            }
        }

    }
    
    public void mensajeErrorCpu(){
        JLabel fieldCpu = vista.getFieldCpu();
        JTextField cpu = vista.getLabelCpu();
        JTextField cantidad1 = vista.getLabelCantidad();
        if (!fieldCpu.getText().contains("no existe")) {
            fieldCpu.setForeground (Color.red);
            String text = "CPU<br />El tipo de CPU no existe";
            fieldCpu.setText("<html><div style='text-align: center;'>" + text + "</div></html>");
            fieldCpu.setHorizontalTextPosition(SwingConstants.CENTER);
            cpu.setText("");
            cantidad1.setText("");
        }
    }
    
    public void mensajeErrorCantidadIncorrecta(){
        JLabel fieldCpu = vista.getFieldCpu();
        JLabel fieldCantidad = vista.getFieldCpu();
        JTextField cantidad1 = vista.getLabelCantidad();
        if (!fieldCantidad.getText().contains("debe ser")) {
            fieldCantidad.setForeground (Color.red);
            String text = "Cantidad<br />La cantidad debe ser un numero entero mayor que 0";
            fieldCantidad.setText("<html><div style='text-align: center;'>" + text + "</div></html>");
            fieldCpu.setHorizontalTextPosition(SwingConstants.CENTER);
            cantidad1.setText("");
        }
    }
    
    public void mensajeErrorEspacio(ArrayList<EspacioAlmacenamiento> lista, int num){
        JTextArea textAreaResultado = vista.getAreaResultado();
        textAreaResultado.setForeground(Color.red);
        textAreaResultado.append("¡FALTAN " + num + " ESPACIOS! \n");
        for(int i=0;i<lista.size();i++){
            textAreaResultado.append(lista.get(i).toString() + "\n");                  
        }        
    }
    
    public void espacioCorrecto(ArrayList<EspacioAlmacenamiento> lista){
        JTextArea textAreaResultado = vista.getAreaResultado();
        for(int i=0;i<lista.size();i++){
            textAreaResultado.append(lista.get(i).toString() + "\n");                   
        }
    }
    
    public void clearAll(){
        JLabel fieldCantidad = vista.getFieldCantidad();
        JLabel fieldCpu = vista.getFieldCpu();
        JTextField cpu = vista.getLabelCpu();
        JTextField cantidad1 = vista.getLabelCantidad();
        cpu.setText("");
        cantidad1.setText("");
        fieldCpu.setText("TIPO DE CPU");
        fieldCantidad.setText("CANTIDAD");
        fieldCantidad.setForeground (Color.black);
        fieldCpu.setForeground(Color.black);
        resetResultado();
    }
    
    public void resetResultado(){  
        JTextArea textAreaResultado = vista.getAreaResultado();
        textAreaResultado.setText("");
        textAreaResultado.setForeground(Color.black);
    }
    
}
