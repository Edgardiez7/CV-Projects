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
import modelo.Compra;
import modelo.HistoricoCompra;

/**
 *
 * @author manu_
 */
public class HistoricoCompraDB {
    
    public static int insert(HistoricoCompra historico){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        // get parameters from the request
        
        String query = "INSERT INTO HISTORICO_COMPRAS (IDANUNCIO, FECHACOMPRA, DNI) VALUES ( ? ,CURRENT_DATE, ? )";

        try {
            ps = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, historico.getIdAnuncio());
            ps.setString(2, historico.getDni());
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
    
    public static ArrayList<Compra> getCompras() {

        int id ;
        int IDAnuncio;
        Date fechaCompra;
        String DNI;
        String nombre;
        
        
        ArrayList <Compra> compras =new ArrayList();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        
        
        String query = "SELECT * FROM HISTORICO_COMPRAS h, ANUNCIO a WHERE h.IDANUNCIO = a.ID";

        try {

            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                id=rs.getInt("ID");
                IDAnuncio=rs.getInt("IDANUNCIO");
                fechaCompra=rs.getDate("FECHACOMPRA");
                DNI=rs.getString("DNI");
                nombre = rs.getString("TITULO");
                
                compras.add(new Compra(id, nombre, IDAnuncio, fechaCompra, DNI));
               
            }
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compras;
    }
    
    public static String buscarTitulo(int id) {

        String titulo = "";  
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;       
        
        
        String query = "SELECT * FROM ANUNCIO a WHERE a.ID = ?";

        try {

            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                titulo = rs.getString("TITULO");               
            }
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titulo;
    }
}
