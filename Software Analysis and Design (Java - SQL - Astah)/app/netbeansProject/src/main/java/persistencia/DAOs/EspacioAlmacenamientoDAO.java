/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;
import persistencia.dbacces.DBConnection;

/**
 *
 * @author miris
 */
public class EspacioAlmacenamientoDAO {

    private static String queryEspaciosCpu = "SELECT * FROM CPU c, CONFIGURACIONPC cp, PC p, ESPACIOALMACENAMIENTO e WHERE c.IDTIPOCPU = cp.TIPOCPU AND cp.IDCONFIGURACION = p.IDCONFIGURACION AND p.UBICACION = e.IDUBICACION AND e.OCUPADO = false AND c.NOMBRETIPOCPU = ?";
    private static String queryCpu = "SELECT * from CPU where NOMBRETIPOCPU = ?";

    public static ArrayList<String> consultarEspacioPc(String cpu) {

        ResultSet rs;
        ArrayList<String> lista = new ArrayList<>();
        int seccion, zona, estanteria;
        String ocupado;
        JSONObject json = new JSONObject();

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            PreparedStatement ps = conexion.getStatement(queryEspaciosCpu);
            ps.setString(1, cpu);
            rs = ps.executeQuery();

            while (rs.next()) {
                seccion = rs.getInt("Seccion");
                zona = rs.getInt("Zona");
                estanteria = rs.getInt("Estanteria");
                ocupado = rs.getString("Ocupado");
                json.put("SECCION", seccion);
                json.put("ZONA", zona);
                json.put("ESTANTERIA", estanteria);
                json.put("OCUPADO", ocupado);

                lista.add(json.toString());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return lista;
    }

    /**
     * Comprueba que el tipo de CPU que hemos introducido se encuentra en la
     * base de datos
     */
    public static boolean comprobarTipoCpu(String cpu) {

        boolean error = false;

        ResultSet rs;

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            PreparedStatement ps = conexion.getStatement(queryCpu);

            ps.setString(1, cpu);
            rs = ps.executeQuery();

            if (!rs.next()) {
                error = true;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return error;
    }
}
