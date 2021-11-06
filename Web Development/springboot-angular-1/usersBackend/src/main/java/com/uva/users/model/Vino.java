package com.uva.users.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Vino")
public class Vino {
    @Id
    @GeneratedValue
    private Integer Id;

    @Size(max = 50)
    @Column(name = "nombre_comercial")
    private String nombreComercial;

    @Size(max = 30)
    private String denominacion;

    @Size(max = 30)
    private String categoria;

    @Column(nullable=false)
    private Float precio;
    
    private Integer bodega_id;

    Vino(){

    }

    Vino(String nombreComercial, String denominacion, String categoria, Float precio, Integer bodega){
        this.nombreComercial = nombreComercial;
        this.denominacion = denominacion;
        this.categoria = categoria;
        this.precio = precio;
        this.bodega_id = bodega;
    }

    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getNombreComercial() {
        return this.nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getDenominacion() {
        return this.denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Float getPrecio() {
        return this.precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getBodega_id() {
        return this.bodega_id;
    }

    public void setBodega_id(Integer bodega_id) {
		this.bodega_id = bodega_id;
	}

    public void updateParams(Vino body) {
        if(body.getNombreComercial() != null) this.nombreComercial = body.getNombreComercial();
        if(body.getDenominacion() != null) this.denominacion = body.getDenominacion();
        if(body.getCategoria() != null) this.categoria = body.getCategoria();
        if(body.getPrecio() != null) this.precio = body.getPrecio();
        if(body.getBodega_id() != null) this.bodega_id = body.getBodega_id();
    }
}
