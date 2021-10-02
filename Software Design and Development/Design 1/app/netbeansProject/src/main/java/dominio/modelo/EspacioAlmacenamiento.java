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
public class EspacioAlmacenamiento {
    
    private int seccion;
    private int zona;
    private int estanteria;
    private boolean ocupado;
    
    public EspacioAlmacenamiento(String file){
       JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(file));
        JsonObject jsonobject = reader.readObject();    
                
        this.seccion = jsonobject.getInt("SECCION");
        this.zona = jsonobject.getInt("ZONA");
        this.estanteria = jsonobject.getInt("ESTANTERIA");
        this.ocupado = Boolean.parseBoolean(jsonobject.getString("OCUPADO"));
        
    }
    
    @Override
    public String toString(){
        return String.valueOf("Seccion: "+seccion+", Zona: "+zona+", Estanteria: "+estanteria);
    }
    
    
}
