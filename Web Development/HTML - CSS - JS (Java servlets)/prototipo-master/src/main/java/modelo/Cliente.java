/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import database.ConnectionPool;

/**
 *
 * @author miris
 */
public final class Cliente implements Serializable{
    ConnectionPool pool = ConnectionPool.getInstance();

    private String nombre;
    private String apellidos;
    private String contrasena;
    private String mail;
    private String DNI;
    private boolean login;

    public Cliente() {
        nombre = "";
        apellidos = "";
        contrasena = "";
        mail = "";
        DNI = "";
        login = false;
    }

    public Cliente (String newDNI, String newNombre, String newApellidos, String newContrasena, String newMail) {
        setNombre(newNombre);
        setApellidos(newApellidos);
        setContrasena(newContrasena);
        setMail(newMail);
        setDNI(newDNI);
        setLogin(false);
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getMail() {
        return mail;
    }

    public String getDNI() {
        return DNI;
    }

    public boolean getLogin(){
        return login;
    }
    
    public void setNombre(String newNombre) {
        nombre = newNombre;
    }

    public void setApellidos(String newApellidos) {
        apellidos = newApellidos;
    }

    public void setContrasena(String newContrasena) {
        contrasena = newContrasena;
    }

    public void setMail(String newMail) {
        mail = newMail;
    }
    
    public void setDNI(String newDNI) {
        DNI = newDNI;
    }
    
    public void setLogin(boolean login) {
        this.login = login;
    }
}
