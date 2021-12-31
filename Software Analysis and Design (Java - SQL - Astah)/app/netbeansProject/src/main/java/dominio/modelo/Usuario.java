/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.modelo;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

/**
 *
 * @author gonzalo
 */
public class Usuario {    
    
    private String nombre;
    private String nifCif;
    private String direccionPostal;
    private String direccionElectronica;
    private String telefono;
    
    public Usuario(String file){
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(file));
        JsonObject jsonobject = reader.readObject();
        
        this.nombre= jsonobject.getString("NOMBRE");
        this.nifCif = jsonobject.getString("NIFCIF");
        this.direccionPostal= jsonobject.getString("DIRECCIONPOSTAL");
        this.direccionElectronica= jsonobject.getString("DIRECCIONELECTRONICA");
        this.telefono= jsonobject.getString("TELEFONO");
    }
    @Override
    public String toString(){
        return("Nombre: " + nombre + "\nNifCif: " + nifCif+ "\nDireccion Postal: " 
                + direccionPostal+ "\nDireccion Electronica: " + direccionElectronica + "\nTelefono: " + telefono);
    }   
    
    public String getDNI(){
        return nifCif;
    }
    
}
