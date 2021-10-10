package com.uva.users.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "bodega")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Bodega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "CIF")
    private String cif;

    @Basic(optional = false)
    @Column(name = "DIRECCION")
    private String direccion;

    @OneToMany(mappedBy = "bodegaId", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<VinoConRelacion> vinoCollection;

    Bodega(){
    }

    Bodega(String nombre, String cif, String direccion){
        this.nombre=nombre;
        this.cif=cif;
        this.direccion=direccion;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return this.cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<VinoConRelacion> getVinoCollection() {
        return this.vinoCollection;
    }

    public void setVinoCollection(List<VinoConRelacion> vinoCollection) {
        this.vinoCollection = vinoCollection;
    }

    public void updateParams(Bodega body) {
        if(body.getNombre() != null) this.nombre = body.getNombre();
        if(body.getCif() != null) this.cif = body.getCif();
        if(body.getDireccion() != null) this.direccion = body.getDireccion();        
    }

}
