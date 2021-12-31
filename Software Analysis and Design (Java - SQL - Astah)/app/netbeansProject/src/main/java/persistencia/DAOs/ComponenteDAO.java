/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.DAOs;

import dominio.modelo.PedidoPCs;
import dominio.modelo.SesionComandos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import persistencia.dbacces.DBConnection;

/**
 *
 * @author miris
 */
public class ComponenteDAO {

    private static String queryDisponibilidad = "SELECT * FROM COMPONENTE c WHERE c.IDDESCRIPCION = ? AND c.RESERVADO=false AND c.ETIQUETAPC IS NULL";
    private static String queryUnPC = "SELECT * FROM PC WHERE IDCONFIGURACION = ? AND RESERVADO = FALSE ORDER BY IDETIQUETA ASC FETCH FIRST ROW ONLY";

    public static Integer disponibilidadComponente(Integer componente) {

        ResultSet rs;
        int contador = 0;

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            PreparedStatement ps = conexion.getStatement(queryDisponibilidad);
            ps.setInt(1, componente);
            rs = ps.executeQuery();

            while (rs.next()) {
                contador++;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return contador;
    }

    public static void marcarComponentesComoReservado(PedidoPCs pedido, ArrayList<Integer> listaIDsComponentes, ArrayList<Integer> componentesEnPC) throws SQLException {
        ResultSet rs;
        int configuracion = PedidoPCsDAO.consultarConfigPedido(pedido);
        //int idPedido = pedido.getID();
        int idPC = -1;

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            for (int j = 0; j < listaIDsComponentes.size(); j++) {
                for (int i = 0; i < componentesEnPC.get(j); i++) {

                    PreparedStatement ps = conexion.getStatement(queryUnPC);
                    ps.setInt(1, configuracion);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        idPC = rs.getInt("IDETIQUETA");
                    }                    
                    SesionComandos.addComandos("UPDATE COMANDO SET RESERVADO = true WHERE RESERVADO = false AND IDETIQUETA = " + idPC);             
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

}
