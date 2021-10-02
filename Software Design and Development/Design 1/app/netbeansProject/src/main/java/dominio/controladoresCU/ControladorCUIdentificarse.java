/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.controladoresCU;
    
import persistencia.DAOs.EmpleadoDAO;
import dominio.modelo.Empleado;
import dominio.modelo.SesionSingletonEmpleado;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author miris
 */
public class ControladorCUIdentificarse {

    public void compruebaCredenciales(String dni, String contrasena) throws IOException, ParseException {
        String json = EmpleadoDAO.consultarEmpleado(dni, contrasena);
        if(json != null && !json.equals("") && !json.equals("{}")){
            Empleado e = new Empleado (json);
            SesionSingletonEmpleado.setInstancia(e);
        }            
    }   
}
