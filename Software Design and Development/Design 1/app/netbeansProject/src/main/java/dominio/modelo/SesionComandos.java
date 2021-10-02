/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.modelo;

import java.util.ArrayList;

/**
 *
 * @author miris
 */
public class SesionComandos {
    protected static ArrayList<String> comandos;
    
    protected SesionComandos(){
        comandos = new ArrayList<>();
    }
    
    public static void addComandos(String c){
        if (comandos==null){
            SesionComandos comandos = new SesionComandos();
        }
        comandos.add(c);
    }
    
    public static ArrayList<String> getComandos(){
        return comandos;
    }
    
    public static void destroyComandos(){
        comandos=null;
    }
}
