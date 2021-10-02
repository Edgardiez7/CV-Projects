/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Anuncio;

/**
 *
 * @author manu_
 */
public class AnuncioDB {
    
    public static ArrayList<Anuncio> getAnuncios(int tipo) {

        int id ;
        String titulo;
        String descripcion;
        int precio;
        Date fechaIni;
        Date fechaFin;
        String enlace;
        String destino;
        int numPersonas;
        
        ArrayList <Anuncio> anuncios =new ArrayList();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        

        String query = "SELECT * FROM ANUNCIO WHERE TIPO = ? ";

        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, tipo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                id=rs.getInt("ID");
                titulo=rs.getString("TITULO");
                descripcion=rs.getString("DESCRIPCION");
                precio=rs.getInt("PRECIO");
                fechaIni=rs.getDate("FECHAINI");
                fechaFin=rs.getDate("FECHAFIN");
                enlace=rs.getString("MAPS");
                destino=rs.getString("DESTINO");
                tipo=rs.getInt("TIPO");
                
                anuncios.add(new Anuncio(id, titulo, descripcion, precio, fechaIni, fechaFin, enlace, destino, tipo));
               
            }
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anuncios;
    }
    
    public static int insert(Anuncio anuncio) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO ANUNCIO (titulo, descripcion, precio, fechaIni, fechaFin, maps, destino, tipo, numPersonas) VALUES (?, ?, ? ,? , ?, ?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, anuncio.getTitulo());
            ps.setString(2, anuncio.getDescripcion());
            ps.setInt(3, anuncio.getPrecio());
            ps.setDate(4, anuncio.getFechaIni());
            ps.setDate(5, anuncio.getFechaFin());
            ps.setString(6, anuncio.getEnlace());
            ps.setString(7, anuncio.getDestino());
            ps.setInt(8, anuncio.getTipo());

            int res = 0;
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                res = rs.getInt(1);
            }

            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        
        
    }
    
    public static ArrayList<Anuncio> getAnunciosFiltrados(String destino, int tipo) {
        int id ;
        String titulo;
        String descripcion;
        int precio;
        Date fechaIni;
        Date fechaFin;
        String enlace;
        int numPersonas;
        
        ArrayList <Anuncio> anuncios =new ArrayList();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "SELECT * FROM ANUNCIO WHERE DESTINO = ? AND TIPO = ?";

        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, destino);
            ps.setInt(2, tipo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                id=rs.getInt("ID");
                titulo=rs.getString("TITULO");
                descripcion=rs.getString("DESCRIPCION");
                precio=rs.getInt("PRECIO");
                fechaIni=rs.getDate("FECHAINI");
                fechaFin=rs.getDate("FECHAFIN");
                enlace=rs.getString("MAPS");
                destino=rs.getString("DESTINO");
                tipo=rs.getInt("TIPO");
                numPersonas=rs.getInt("NUMPERSONAS");
                
                anuncios.add(new Anuncio(id, titulo, descripcion, precio, fechaIni, fechaFin, enlace, destino, tipo));
               
            }
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anuncios;
    }
    
}
