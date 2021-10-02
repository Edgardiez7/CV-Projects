package database;

import modelo.Gestor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;



public class GestorDB {
    
    // Devuelve true si el par dni contraseña existe y false si no existiese
    public boolean compruebaIdentificacionGestor(String cif, String password){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        boolean existe = false;
        String queryCliente = "select * from gestor where cif = ? and password = ? ";
        try
        {
            ps = connection.prepareStatement(queryCliente, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cif);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                existe = true;
            }
            ps.close();
            pool.freeConnection(connection);
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return existe;
    }
    
        public static void insert(Gestor gestor) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO GESTOR (CIF, codCuenta, password, correo) VALUES (?, ?, ? ,?)";
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, gestor.getCIF());
            ps.setString(2, gestor.getCodCuenta());
            ps.setString(3, gestor.getContrasena());
            ps.setString(4, gestor.getCorreo());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
            }
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Gestor obtenerDatosGestor(String CIF, String password) {
        
        String codCuenta, correo;
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "SELECT * FROM GESTOR WHERE CIF = '"+CIF + "' AND password = '"+password+"'";
        Gestor gestor = null;
        try {

            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                codCuenta = rs.getString("CODCUENTA");
                correo = rs.getString("correo");

                gestor = new Gestor(CIF, codCuenta, password, correo);
                gestor.setLogin(true);

            }
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gestor;
    }

}

