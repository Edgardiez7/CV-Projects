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
import modelo.Anunciado;
import modelo.HistoricoAnuncio;

/**
 *
 * @author manu_
 */
public class HistoricoAnuncioDB {
    
    public static int insert(HistoricoAnuncio historico){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        

        String query = "INSERT INTO HISTORICO_ANUNCIOS (IDANUNCIO, FECHAPUBLICACION, CIF) VALUES ( ? ,CURRENT_DATE, ? )";

        try {
            ps = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, historico.getIdAnuncio());
            ps.setString(2, historico.getCif());
            
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

    public static ArrayList<Anunciado> getAnuncios() {

        int id;
        int IDAnuncio;
        Date fechaPublicacion;
        String CIF;
        String nombre;

        ArrayList<Anunciado> anunciados = new ArrayList();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "SELECT * FROM HISTORICO_ANUNCIOS h, ANUNCIO a WHERE h.IDANUNCIO = a.ID";

        try {

            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("ID");
                IDAnuncio = rs.getInt("IDANUNCIO");
                nombre = rs.getString("TITULO");
                fechaPublicacion = rs.getDate("FECHAPUBLICACION");
                CIF = rs.getString("CIF");
                anunciados.add(new Anunciado(id, nombre,IDAnuncio, fechaPublicacion, CIF));
            }
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anunciados;
    }
}
