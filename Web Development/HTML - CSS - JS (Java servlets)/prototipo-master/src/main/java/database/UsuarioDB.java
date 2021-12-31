package database;

import modelo.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class UsuarioDB {
    
    // Devuelve true si el par dni contraseña existe y false si no existiese
    public boolean compruebaIdentificacionUsuario(String dni, String password){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        boolean existe = false;
        String queryCliente = "select * from cliente where dni = ? and password = ? ";
        try
        {
            ps = connection.prepareStatement(queryCliente, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dni);
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
    
    public static void insert(Cliente cliente) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO CLIENTE (DNI, password, nombre, apellidos, correo) VALUES (?, ?, ? ,? , ?)";

        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getDNI());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellidos());
            ps.setString(2, cliente.getContrasena());
            ps.setString(5, cliente.getMail());

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
    
    public static Cliente obtenerDatosCliente(String dni, String password) {
        
        String nombre, apellidos, correo;
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "SELECT * FROM CLIENTE WHERE dni = '"+dni + "' AND password = '"+password+"'";
        Cliente cliente = null;
        try {

            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                correo = rs.getString("correo");

                cliente = new Cliente(dni, nombre, apellidos, password, correo);
                cliente.setLogin(true);

            }
            ps.close();
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }
}