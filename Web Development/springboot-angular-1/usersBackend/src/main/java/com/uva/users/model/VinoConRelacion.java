package com.uva.users.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "VinoConRelacion")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NamedQuery(name = "VinoConRelacion.findByDenominacionYBodega", query = "SELECT v FROM VinoConRelacion v WHERE v.denominacion= ?1 AND v.bodegaId.nombre= ?2")
public class VinoConRelacion {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(max = 50)
    @Column(name = "nombre_comercial")
    private String nombreComercial;

    private String denominacion;
    private String categoria;

    @Column(nullable = false)
    private Float precio;

    @JoinColumn(name = "Bodega_Id", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Bodega bodegaId;

    VinoConRelacion() {
    }

    VinoConRelacion(String nombreComercial, String denominacion, String categoria, Float precio, Bodega bodegaId) {
        this.nombreComercial = nombreComercial;
        this.denominacion = denominacion;
        this.categoria = categoria;
        this.precio = precio;
        this.bodegaId = bodegaId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Bodega getBodegaId() {
        return this.bodegaId;
    }

    public void setBodegaId(Bodega bodegaId) {
        this.bodegaId = bodegaId;
    }

    public void updateParams(VinoConRelacion body) {
        if(body.getNombreComercial() != null) this.nombreComercial = body.getNombreComercial();
        if(body.getDenominacion() != null) this.denominacion = body.getDenominacion();
        if(body.getCategoria() != null) this.categoria = body.getCategoria();
        if(body.getPrecio() != null) this.precio = body.getPrecio();
        if(body.getBodegaId() != null) this.bodegaId = body.getBodegaId();
    }

}
