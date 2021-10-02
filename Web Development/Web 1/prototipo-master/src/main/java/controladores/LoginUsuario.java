package controladores;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Cliente;
import database.UsuarioDB;

@WebServlet(name="LoginUsuario", urlPatterns={"/LoginUsuario"})
public class LoginUsuario extends HttpServlet {
    private String dni = "";
    private String password = "";
    private UsuarioDB modelo = new UsuarioDB();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{   
        // Cogemos el name
        this.dni = request.getParameter("id-usuario");
        this.password = request.getParameter("password-usuario");
        HttpSession session = request.getSession();
        if(modelo.compruebaIdentificacionUsuario(dni, password)){
            Cliente cliente = UsuarioDB.obtenerDatosCliente(dni, password);
            cliente.setLogin(true);
            session.setAttribute("cliente", cliente);
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
        else{
            session.setAttribute("errorLogin", "Usuario no encontrado");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            session.invalidate();
        }
    }
}