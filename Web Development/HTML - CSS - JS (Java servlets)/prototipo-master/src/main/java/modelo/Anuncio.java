/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author manu_
 */
public class Anuncio {
    private int id;
    private String titulo;
    private String descripcion;
    private int precio;
    private Date fechaIni;
    private Date fechaFin;
    private String enlace;
    private String destino;
    private int tipo;
    
    public Anuncio(String titulo, String descripcion, int precio, Date fechaIni, Date fechaFin, String enlace,
            String destino, int tipo){
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.precio=precio;
        this.fechaIni=fechaIni;
        this.fechaFin=fechaFin;
        this.enlace=enlace;
        this.destino=destino;
        this.tipo=tipo;
    }
    
        public Anuncio(int id, String titulo, String descripcion, int precio, Date fechaIni, Date fechaFin, String enlace,
            String destino, int tipo){
        this.id = id;
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.precio=precio;
        this.fechaIni=fechaIni;
        this.fechaFin=fechaFin;
        this.enlace=enlace;
        this.destino=destino;
        this.tipo=tipo;
        
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
