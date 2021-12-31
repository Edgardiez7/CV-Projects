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
public class Rol {
    private Date comienzoEnRol;
    private TipoRol rol;    
    
    public Rol(Date comienzoEnRol, TipoRol rol){
        this.comienzoEnRol = comienzoEnRol;
        this.rol = rol;
    }
    
    public TipoRol getTipoRol(){
        return rol;
    }
}