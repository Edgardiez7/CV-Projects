/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.modelo;

import java.util.Date;


/**
 *
 * @author miris
 */
public class Disponibilidad {    
    private Date comienzo;
    private Date finalPrevisto;
    private TipoDeDisponibilidad disponibilidad;
    
    public Disponibilidad (Date comienzo, Date finalPrevisto,TipoDeDisponibilidad disponibilidad){
        this.comienzo=comienzo;
        this.finalPrevisto=finalPrevisto;
        this.disponibilidad=disponibilidad;
    }
    
    
    public TipoDeDisponibilidad getTipoDisponibilidad(){
        return disponibilidad;
    }
}
