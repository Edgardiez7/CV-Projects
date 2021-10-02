/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author miris
 */
public class Gestor implements Serializable {

    private String CIF;
    private String codCuenta;
    private String contrasena;
    private String correo;
    private Boolean login;

    public Gestor() {
        CIF = "";
        codCuenta = "";
        contrasena = "";
        correo = "";
        login = false;

    }

    public Gestor(String newCIF, String newCodCuenta, String newContrasena, String newCorreo) {
        setCIF(newCIF);
        setCodCuenta(newCodCuenta);
        setContrasena(newContrasena);
        setCorreo(newCorreo);
        setLogin(false);

    }

    public String getCIF() {
        return CIF;
    }

    public String getCodCuenta() {
        return codCuenta;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getCorreo() {
        return correo;
    }
    
    public Boolean getLogin(){
        return login;
    }

    public void setCIF(String newCIF) {
        CIF = newCIF;
    }

    public void setCodCuenta(String newCodCuenta) {
        codCuenta = newCodCuenta;
    }

    public void setContrasena(String newContrasena) {
        contrasena = newContrasena;
    }

    public void setCorreo(String newCorreo) {
        correo = newCorreo;
    }

    public void setLogin(boolean newLogin) {
        login = newLogin;
    }
}
