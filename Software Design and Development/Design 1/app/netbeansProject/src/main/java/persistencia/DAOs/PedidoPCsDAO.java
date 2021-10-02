/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.DAOs;

import dominio.modelo.PedidoPCs;
import dominio.modelo.SesionComandos;
import java.sql.Date;

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
public class PedidoPCsDAO {

    private static String queryPedidosSolicitados = "SELECT * FROM PEDIDOPC p WHERE p.ESTADO=1";
    private static String queryInfoPedido = "SELECT * FROM PEDIDOPC p, EMPRESA e, USUARIO u, ESTADOVENTAPCS es, CONFIGURACIONPC c, CPU cp WHERE p.CONFIGURACIONSOLICITADA=c.IDCONFIGURACION AND c.TIPOCPU=cp.IDTIPOCPU AND es.IDESTADOVENTA=p.ESTADO AND p.ENCARGADOPOR=e.CIF AND e.CIF=u.NIFCIF AND e.ESCLIENTE=true AND p.IDPEDIDO=?";
    private static String queryCantidadPCs = "SELECT * FROM PC WHERE IDCONFIGURACION = ? AND RESERVADO = FALSE";
    private static String queryConfigPedido = "SELECT * FROM PEDIDOPC WHERE IDPEDIDO = ?";
    private static String queryUnPC = "SELECT * FROM PC WHERE IDCONFIGURACION = ? AND RESERVADO = FALSE ORDER BY IDETIQUETA ASC";
    private static String queryComponentes = "SELECT * FROM PEDIDOPC p, CONFIGURACIONPC c, COMPONENTESENCONFIGURACION co WHERE p.CONFIGURACIONSOLICITADA=c.IDCONFIGURACION AND co.IDCONFIGURACION = c.IDCONFIGURACION AND p.IDPEDIDO = ?";
    private static String queryComponentesDePC = "SELECT * FROM CONFIGURACIONPC c, PC p, COMPONENTESENCONFIGURACION cc WHERE p.IDCONFIGURACION=c.IDCONFIGURACION AND c.IDCONFIGURACION=cc.IDCONFIGURACION AND cc.IDDESCRIPCION = ?";
    private static String queryEtiquetaPc = "SELECT * FROM PC pc WHERE pc.IDETIQUETA = ?";
    private static String queryEtiquetaComponente = "SELECT * FROM COMPONENTE WHERE IDETIQUETA = ? AND ETIQUETAPC is NULL";
    private static String queryNumComponentes = "SELECT * FROM COMPONENTESENCONFIGURACION WHERE IDCONFIGURACION = ?";
    private static String compruebaNumPCsEnPedido = "SELECT * FROM PC WHERE IDPEDIDO = ?";
    private static String compruebaPedidoCompletado = "UPDATE PEDIDOPC SET ESTADO = 3  WHERE IDPEDIDO = ?";

    public static String consultarInfoPedido(int idPedido) {
        ResultSet rs;
        JSONObject json = new JSONObject();

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            PreparedStatement ps = conexion.getStatement(queryInfoPedido);
            ps.setString(1, idPedido + "");
            rs = ps.executeQuery();

            if (rs.next()) {
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
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return json.toString();
    }

    public static ArrayList<Integer> consultarPedidosSolicitados() {
        ResultSet rs;
        ArrayList<Integer> lista = new ArrayList<>();

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            PreparedStatement ps = conexion.getStatement(queryPedidosSolicitados);

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(rs.getInt("idPedido"));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return lista;
    }

    public static int consultarConfigPedido(PedidoPCs pedido) {
        ResultSet rs;
        int config = -1;
        int idPedido = pedido.getID();

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            PreparedStatement ps = conexion.getStatement(queryConfigPedido);
            ps.setInt(1, idPedido);
            rs = ps.executeQuery();

            while (rs.next()) {
                config = rs.getInt("CONFIGURACIONSOLICITADA");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return config;
    }

    public static int consultarCantidadPCs(PedidoPCs pedido) {
        int configuracion = consultarConfigPedido(pedido);
        ResultSet rs;
        int contador = 0;

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            PreparedStatement ps = conexion.getStatement(queryCantidadPCs);
            ps.setInt(1, configuracion);
            rs = ps.executeQuery();

            while (rs.next()) {
                contador++;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return contador;
    }

    public static void marcarPCsComoReservado(PedidoPCs pedido, int numReservas) {
        ResultSet rs;
        int configuracion = consultarConfigPedido(pedido);
        int idPedido = pedido.getID();

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();

            PreparedStatement ps = conexion.getStatement(queryUnPC);
            ps.setInt(1, configuracion);
            rs = ps.executeQuery();
            ArrayList<Integer> arrayEtiquetas = new ArrayList<>();
            while (rs.next() && numReservas > 0) {
                arrayEtiquetas.add(rs.getInt("IDETIQUETA"));
                numReservas--;
            }
            for (Integer idPC : arrayEtiquetas) {
                SesionComandos.addComandos("UPDATE PC SET RESERVADO = true, IDPEDIDO = " + idPedido + " WHERE IDCONFIGURACION = " + configuracion + " AND RESERVADO = false AND IDETIQUETA = " + idPC);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Integer> procesarComponentes(PedidoPCs pedido) {

        ResultSet rs;
        ArrayList<Integer> lista = new ArrayList<>();

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            PreparedStatement ps = conexion.getStatement(queryComponentes);
            ps.setInt(1, pedido.getID());
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(rs.getInt("IDDESCRIPCION"));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return lista;
    }

    public static Integer cantidadComponentesEnPC(Integer idcomponente) {

        ResultSet rs;
        int contador = 0;

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            PreparedStatement ps = conexion.getStatement(queryComponentesDePC);
            ps.setInt(1, idcomponente);
            rs = ps.executeQuery();

            while (rs.next()) {
                contador++;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return contador;
    }

    public static boolean existePcEtiqueta(int etiqueta) {
        ResultSet rs;

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();

            PreparedStatement ps = conexion.getStatement(queryEtiquetaPc);

            ps.setInt(1, etiqueta);
            rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        return true;
    }

    public static void ejecutarTodo() {
        ArrayList<String> querys = SesionComandos.getComandos();
        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            for (String query : querys) {
                PreparedStatement update = conexion.getStatement(query);
                update.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean existeComponenteEtiqueta(int etiqueta) {
        ResultSet rs;

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();

            PreparedStatement ps = conexion.getStatement(queryEtiquetaComponente);

            ps.setInt(1, etiqueta);
            rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        return false;
    }

    public static int compruebaNumComponentes(int idConf) {
        ResultSet rs;
        int contador = 0;

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();

            PreparedStatement ps = conexion.getStatement(queryNumComponentes);

            ps.setInt(1, idConf);
            rs = ps.executeQuery();
            while (rs.next()) {
                contador++;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        return contador;
    }

    public static int compruebaNumPCsEnPedido(int idPedido) {
        ResultSet rs;
        int contador = 0;

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();

            PreparedStatement ps = conexion.getStatement(compruebaNumPCsEnPedido);

            ps.setInt(1, idPedido);
            rs = ps.executeQuery();
            while (rs.next()) {
                contador++;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        return contador;
    }

    public static void pedidoCompletado(int idPedido) {
        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            PreparedStatement update = conexion.getStatement(compruebaPedidoCompletado);
            update.setInt(1, idPedido);

            update.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
