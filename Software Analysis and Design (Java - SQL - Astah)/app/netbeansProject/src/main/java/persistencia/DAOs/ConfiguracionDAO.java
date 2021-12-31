/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.DAOs;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;
import persistencia.dbacces.DBConnection;

/**
 *
 * @author edgarpopos
 */
public class ConfiguracionDAO {

    private static String queryConfiguraciones = "SELECT * FROM CONFIGURACIONPC c, CPU cp WHERE c.TIPOCPU=cp.IDTIPOCPU";
    private static String queryPedidosAsociados = "SELECT * FROM PEDIDOPC p, EMPRESA e, USUARIO u, ESTADOVENTAPCS es, CONFIGURACIONPC c, CPU cp WHERE p.CONFIGURACIONSOLICITADA=c.IDCONFIGURACION AND c.TIPOCPU=cp.IDTIPOCPU AND es.IDESTADOVENTA=p.ESTADO AND p.ENCARGADOPOR=e.CIF AND e.CIF=u.NIFCIF AND p.ESTADO=2 AND c.IDCONFIGURACION= ? ORDER BY p.FECHAPEDIDO ASC";
    //private static String queryInfoPedido = "SELECT * FROM PEDIDOPC p, EMPRESA e, USUARIO u, ESTADOVENTAPCS es, CONFIGURACIONPC c, CPU cp WHERE p.CONFIGURACIONSOLICITADA=c.IDCONFIGURACION AND c.TIPOCPU=cp.IDTIPOCPU AND es.IDESTADOVENTA=p.ESTADO AND p.ENCARGADOPOR=e.CIF AND e.CIF=u.NIFCIF AND e.ESCLIENTE=true AND p.IDPEDIDO=?";

    public static ArrayList<String> consultarConfigs() {
        ResultSet rs;
        ArrayList<String> arrayC = new ArrayList<>();

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            PreparedStatement ps = conexion.getStatement(queryConfiguraciones);
            rs = ps.executeQuery();

            while (rs.next()) {
                JSONObject json = new JSONObject();
                int idConfig = rs.getInt("IDCONFIGURACION");
                json.put("IDCONFIGURACION", idConfig);
                String tipoCPU = rs.getString("NOMBRETIPOCPU");
                json.put("NOMBRETIPOCPU", tipoCPU);
                String velocidadCPU = rs.getString("VELOCIDADCPU");
                json.put("VELOCIDADCPU", velocidadCPU);
                int capacidadRAM = rs.getInt("CAPACIDADRAM");
                json.put("CAPACIDADRAM", capacidadRAM);
                int capacidadDD = rs.getInt("CAPACIDADDD");
                json.put("CAPACIDADDD", capacidadDD);
                String velocidadTarjetaGrafica = rs.getString("VELOCIDADTARJETAGRAFICA");
                json.put("VELOCIDADTARJETAGRAFICA", velocidadTarjetaGrafica);
                String memoriaTarjetaGrafica = rs.getString("MEMORIATARJETAGRAFICA");
                json.put("MEMORIATARJETAGRAFICA", memoriaTarjetaGrafica);
                arrayC.add(json.toString());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return arrayC;
    }

    public static ArrayList<String> consultarPedidosAsociados(int idConf) {
        ResultSet rs;
        ArrayList<String> arrayC = new ArrayList<>();

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            PreparedStatement ps = conexion.getStatement(queryPedidosAsociados);
            ps.setString(1, idConf + "");
            rs = ps.executeQuery();

            while (rs.next()) {
                JSONObject json = new JSONObject();
                int idConfig = rs.getInt("IDCONFIGURACION");
                json.put("IDCONFIGURACION", idConfig);
                int ID = rs.getInt("IDPEDIDO");
                json.put("IDPEDIDO", ID);
                int cantidadSolicitada = rs.getInt("CANTIDADSOLICITADA");
                json.put("CANTIDADSOLICITADA", cantidadSolicitada);
                Date fechaInicio = rs.getDate("FECHAPEDIDO");
                json.put("FECHAPEDIDO", fechaInicio.toString());
                String estadoVenta = rs.getString("NOMBREESTADOVENTA");
                json.put("ESTADOVENTA", estadoVenta);
                String nifcif = rs.getString("NIFCIF");
                json.put("NIFCIF", nifcif);
                String nombre = rs.getString("NOMBRE");
                json.put("NOMBRE", nombre);
                String direccionPostal = rs.getString("DIRECCIONPOSTAL");
                json.put("DIRECCIONPOSTAL", direccionPostal);
                String direccionElectronica = rs.getString("DIRECCIONELECTRONICA");
                json.put("DIRECCIONELECTRONICA", direccionElectronica);
                String telefono = rs.getString("TELEFONO");
                json.put("TELEFONO", telefono);
                String tipoCPU = rs.getString("NOMBRETIPOCPU");
                json.put("NOMBRETIPOCPU", tipoCPU);
                String velocidadCPU = rs.getString("VELOCIDADCPU");
                json.put("VELOCIDADCPU", velocidadCPU);
                int capacidadRAM = rs.getInt("CAPACIDADRAM");
                json.put("CAPACIDADRAM", capacidadRAM);
                int capacidadDD = rs.getInt("CAPACIDADDD");
                json.put("CAPACIDADDD", capacidadDD);
                String velocidadTarjetaGrafica = rs.getString("VELOCIDADTARJETAGRAFICA");
                json.put("VELOCIDADTARJETAGRAFICA", velocidadTarjetaGrafica);
                String memoriaTarjetaGrafica = rs.getString("MEMORIATARJETAGRAFICA");
                json.put("MEMORIATARJETAGRAFICA", memoriaTarjetaGrafica);

                arrayC.add(json.toString());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return arrayC;
    }

}
