package controladores;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Gestor;
import database.GestorDB;


@WebServlet(name="LoginGestor", urlPatterns={"/LoginGestor"})
public class LoginGestor extends HttpServlet {
    private String CIF = "";
    private String password = "";
    private GestorDB modelo = new GestorDB();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{   
        // Cogemos el name
        this.CIF = request.getParameter("id-gestor");
        this.password = request.getParameter("password-gestor");
        HttpSession session = request.getSession();
        if(modelo.compruebaIdentificacionGestor(CIF, password)){
            Gestor gestor = GestorDB.obtenerDatosGestor(CIF, password);
            gestor.setLogin(true);            
            session.setAttribute("gestor", gestor);
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
        else{
            session.setAttribute("errorLogin", "Gestor no encontrado");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            session.invalidate();
        }
    }
}