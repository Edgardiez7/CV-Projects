/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.controladoresCU;

import persistencia.DAOs.EspacioAlmacenamientoDAO;
import dominio.modelo.EspacioAlmacenamiento;
import java.util.ArrayList;

/**
 *
 * @author gonzalo
 */
public class ControladorCUBuscarEspacioPc {
    
      public ControladorCUBuscarEspacioPc(){
    }
      
    
    public int validarDatos(String cpu, String cantidad){
        int c;
        int error=0;
        boolean errorCpu;
        try{
            c = Integer.parseInt(cantidad);
            if(c<=0){
                error=1;
            }
        }
        catch (NumberFormatException e) {
            error=1;
        }
        
        errorCpu= EspacioAlmacenamientoDAO.comprobarTipoCpu(cpu);
        if(errorCpu)error=2;
        
        return error; 
    }
      
      
    public ArrayList<EspacioAlmacenamiento> procesarPeticion(String cpu) {
        ArrayList<EspacioAlmacenamiento> espacios = new ArrayList<>();
        for(String lista:EspacioAlmacenamientoDAO.consultarEspacioPc(cpu)){
            espacios.add(new EspacioAlmacenamiento(lista));
        }
        return espacios;
    }
    
}
