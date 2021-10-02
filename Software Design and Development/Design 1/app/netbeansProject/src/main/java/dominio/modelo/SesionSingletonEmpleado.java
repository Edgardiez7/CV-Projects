/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.modelo;

/**
 *
 * @author miris
 */
public class SesionSingletonEmpleado {
    protected static Empleado empleado;
    
    protected SesionSingletonEmpleado(Empleado e){
        empleado=e;
    }
    
    public static Empleado getInstancia(){
        return empleado;
    }
    
    public static Empleado setInstancia(Empleado e){
        if (empleado == null){
            empleado=e;
        }
        return empleado;
    }
    
    public static void destroyInstancia(){
        empleado = null;
    }
}
