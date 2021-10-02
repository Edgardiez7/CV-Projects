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
public class Anunciado {

    private int ID;
    private int IDAnuncio;
    private Date fechaPublicacion;
    private String CIF;
    private String nombre;

    public Anunciado(int newID, String nombre, int newIDAnuncio, Date newFechaPublicacion, String newCIF) {
        this.setID(newID);
        this.setIDAnuncio(newIDAnuncio);
        this.setFechaPublicacion(newFechaPublicacion);
        this.setCIF(newCIF);
        this.nombre = nombre;
    }

    private void setID(int newID) {
        this.ID = newID;
    }

    private void setIDAnuncio(int newIDAnuncio) {
        this.IDAnuncio = newIDAnuncio;
    }

    private void setCIF(String newCIF) {
        this.CIF = newCIF;
    }

    private void setFechaPublicacion(Date newFechaPublicacion) {
        this.fechaPublicacion = newFechaPublicacion;
    }

    public int getID() {
        return ID;
    }

    public int getIDAnuncio() {
        return IDAnuncio;
    }

    public String getCIF() {
        return CIF;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }
    
    public String getNombre() {
        return nombre;
    }

}
