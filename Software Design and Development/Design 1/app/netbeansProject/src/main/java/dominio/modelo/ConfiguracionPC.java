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
 * @author miris
 */
public class ConfiguracionPC {
    private int idConf;
    private CPU tipoCPU;
    private double velocidadCPU;
    private int capacidadRAM;
    private int capacidadDD;
    private double velocidadTarjetaGrafica;
    private double memoriaTarjetaGrafica;

    public ConfiguracionPC(String file) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(file));
        JsonObject jsonobject = reader.readObject();    
                
        this.idConf=jsonobject.getInt("IDCONFIGURACION");
        this.tipoCPU = CPU.valueOf(jsonobject.getString("NOMBRETIPOCPU"));
        this.velocidadCPU = Double.parseDouble(jsonobject.getString("VELOCIDADCPU"));
        this.capacidadRAM = jsonobject.getInt("CAPACIDADRAM");
        this.capacidadDD = jsonobject.getInt("CAPACIDADDD");
        this.velocidadTarjetaGrafica = Double.parseDouble(jsonobject.getString("VELOCIDADTARJETAGRAFICA"));
        this.memoriaTarjetaGrafica = Double.parseDouble(jsonobject.getString("MEMORIATARJETAGRAFICA"));  
    }
    
    @Override
    public String toString(){
        return ("CPU: " + tipoCPU+"\nVelocidad CPU: " + velocidadCPU+"\nCapacidad RAM: "
                + capacidadRAM+"\nCapacidad DD " + capacidadDD+
                "\nVelocidad TGrafica: " + velocidadTarjetaGrafica + "\nMemoria TGrafica: " + 
                memoriaTarjetaGrafica);
    }
    
    public int getIdConf(){
        return idConf;
    }
    
}
