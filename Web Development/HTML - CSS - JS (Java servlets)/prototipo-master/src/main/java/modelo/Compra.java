/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author miris
 */
public class Compra {

    private int ID;
    private int IDAnuncio;
    private Date fechaCompra;
    private String DNI;
    private String nombre;

    public Compra(int newID, String nombre, int newIDAnuncio, Date newFechaCompra, String newDNI) {
        this.setID(newID);
        this.setIDAnuncio(newIDAnuncio);
        this.setFechaCompra(newFechaCompra);
        this.setDNI(newDNI);
        this.nombre=nombre;
    }

    private void setID(int newID) {
        this.ID = newID;
    }

    private void setIDAnuncio(int newIDAnuncio) {
        this.IDAnuncio = newIDAnuncio;
    }

    private void setDNI(String newDNI) {
        this.DNI = newDNI;
    }

    private void setFechaCompra(Date newFechaCompra) {
        this.fechaCompra = newFechaCompra;
    }

    public int getID() {
        return ID;
    }

    public int getIDAnuncio() {
        return IDAnuncio;
    }

    public String getDNI() {
        return DNI;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }
    
    public String getNombre() {
        return nombre;
    }

}
