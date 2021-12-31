/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.modelo;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

/**
 *
 * @author miris
 */
public class PedidoPCs {

    private Cliente cliente;
    private int cantidadSolicitada;
    private Date fechaPedido; 
    private EstadoVentaPCs estadoVenta;
    private ConfiguracionPC config;    
    private int idPedido;

    public PedidoPCs(String file) throws ParseException{
        
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(file));
        JsonObject jsonobject = reader.readObject();    
                
        this.cliente= new Cliente(file);
        this.cantidadSolicitada = jsonobject.getInt("CANTIDADSOLICITADA");
        String dateStr = jsonobject.getString("FECHAPEDIDO");
        this.fechaPedido = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        this.estadoVenta = EstadoVentaPCs.valueOf(jsonobject.getString("ESTADOVENTA"));
        this.config= new ConfiguracionPC(file);
        this.idPedido = jsonobject.getInt("IDPEDIDO");        
    }
    
    @Override
    public String toString(){
        return(cliente.toString() + "\nCantidad Solicitada: " + cantidadSolicitada + "\n"
                + "Estado Venta: " + estadoVenta.toString() + "\n"+ config.toString());
    }
    
    public int getCantidad(){
        return cantidadSolicitada;
    }
    
    public int getID(){
        return idPedido;
    }
    
    public ConfiguracionPC getConfiguracion(){
        return config;
    }
}
