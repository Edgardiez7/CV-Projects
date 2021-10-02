/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.DAOs;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistencia.dbacces.DBConnection;

/**
 *
 * @author miris
 */
public class EmpleadoDAO {

    private static String queryVinculo = "SELECT * FROM VINCULACIONCONLAEMPRESA v, TIPODEVINCULACION t WHERE v.VINCULO=t.IDTIPO AND EMPLEADO = ? ORDER BY v.INICIO DESC FETCH FIRST ROW ONLY";
    private static String queryEmpleado = "SELECT * from EMPLEADO e, Usuario u where u.NIFCIF=e.NIF AND NIF = ?";
    private static String queryRol = "SELECT * FROM ROLESENEMPRESA r, TIPODEROL t WHERE r.ROL = t.IDTIPO AND r.EMPLEADO = ? ORDER BY r.COMIENZOENROL DESC";
    private static String queryVinculoTodos = "SELECT * FROM VINCULACIONCONLAEMPRESA v, TIPODEVINCULACION t WHERE v.VINCULO=t.IDTIPO AND EMPLEADO = ? ORDER BY v.INICIO DESC";
    private static String queryDisponibilidadTodos = "SELECT * FROM DISPONIBILIDADEMPLEADO d, TIPODEDISPONIBILIDAD t WHERE d.DISPONIBILIDAD=t.IDTIPO AND EMPLEADO = ? ORDER BY d.COMIENZO DESC";
    private static String queryDisponibilidad = "SELECT * FROM DISPONIBILIDADEMPLEADO d, TIPODEDISPONIBILIDAD t WHERE d.DISPONIBILIDAD=t.IDTIPO AND EMPLEADO = ? ORDER BY d.COMIENZO DESC FETCH FIRST ROW ONLY";

 
    public static String consultarEmpleado(String dni, String contrasena) throws IOException, ParseException {

        Short vinculo;
        Short disponibilidad;
        String contrasenaBD = null;
        ResultSet rs;
        JSONObject json = new JSONObject();

        try {
            DBConnection conexion = DBConnection.getInstance();
            conexion.openConnection();
            PreparedStatement ps = conexion.getStatement(queryEmpleado);

            ps.setString(1, dni);
            rs = ps.executeQuery();

            if (rs.next()) {
                contrasenaBD = rs.getString("Password");
            }
            if (contrasena.equals(contrasenaBD)) {
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
                Date fechaInicio = rs.getDate("FECHAINICIO");
                json.put("FECHAINICIO", fechaInicio.toString());

                ps = conexion.getStatement(queryVinculo);
                ps.setString(1, dni);
                rs = ps.executeQuery();
                if (rs.next()) {
                    vinculo = rs.getShort("Vinculo");
                    if (vinculo == 1) {

                        ps = conexion.getStatement(queryVinculoTodos);
                        ps.setString(1, dni);
                        rs = ps.executeQuery();

                        JSONArray jsonArrayFechaV = new JSONArray();
                        JSONArray jsonArrayNombreV = new JSONArray();

                        while (rs.next()) {
                            Date inicio = rs.getDate("INICIO");
                            jsonArrayFechaV.put(inicio.toString());
                            String nombreTipo = rs.getString("NOMBRETIPO");
                            jsonArrayNombreV.put(nombreTipo);
                        }

                        json.put("VINCULACIONESFECHA", jsonArrayFechaV);
                        json.put("VINCULACIONESNOMBRE", jsonArrayNombreV);

                        ps = conexion.getStatement(queryDisponibilidad);
                        ps.setString(1, dni);
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            disponibilidad = rs.getShort("Disponibilidad");
                            if (disponibilidad == 3) {

                                ps = conexion.getStatement(queryDisponibilidadTodos);
                                ps.setString(1, dni);
                                rs = ps.executeQuery();
                                JSONArray jsonArrayComienzoD = new JSONArray();
                                JSONArray jsonArrayFinalD = new JSONArray();
                                JSONArray jsonArrayNombreD = new JSONArray();

                                while (rs.next()) {
                                    Date inicio2 = rs.getDate("COMIENZO");
                                    jsonArrayComienzoD.put(inicio2.toString());
                                    Date finalPrevisto = rs.getDate("FINALPREVISTO");
                                    if (finalPrevisto != null) {
                                        jsonArrayFinalD.put(finalPrevisto.toString());
                                    } else {
                                        jsonArrayFinalD.put("0000-00-00");
                                    }
                                    String nombreTipo = rs.getString("NOMBRETIPO");
                                    jsonArrayNombreD.put(nombreTipo);
                                }

                                json.put("DISPONIBILIDADESINICIO", jsonArrayComienzoD);
                                json.put("DISPONIBILIDADESFINAL", jsonArrayFinalD);
                                json.put("DISPONIBILIDADESNOMBRE", jsonArrayNombreD);

                                ps = conexion.getStatement(queryRol);
                                ps.setString(1, dni);
                                rs = ps.executeQuery();

                                JSONArray jsonArrayComienzoR = new JSONArray();
                                JSONArray jsonArrayNombreR = new JSONArray();

                                while (rs.next()) {
                                    Date inicio3 = rs.getDate("COMIENZOENROL");
                                    jsonArrayComienzoR.put(inicio3.toString());
                                    String nombreTipoR = rs.getString("NOMBRETIPO");
                                    jsonArrayNombreR.put(nombreTipoR);
                                }

                                json.put("ROLESFECHA", jsonArrayComienzoR);
                                json.put("ROLESNOMBRE", jsonArrayNombreR);

                            }
                        }
                    }
                }

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return json.toString();
    }

}
