/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Cliente;
import database.UsuarioDB;

/**
 *
 * @author miris
 */
@WebServlet(name = "RegistroCliente", urlPatterns = {"/registraCliente"})
public class RegistroCliente extends HttpServlet {

    private String nombre;
    private String apellidos;
    private String contrasena;
    private String mail;
    private String DNI;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parameters from the request
        nombre = request.getParameter("Nombre");
        apellidos = request.getParameter("Apellidos");
        contrasena = request.getParameter("Contrasena");
        mail = request.getParameter("Mail");
        DNI = request.getParameter("DNI");

        Cliente cliente = new Cliente(DNI, nombre, apellidos, contrasena, mail);

        // forward request and response to JSP page
        UsuarioDB.insert(cliente);
        String url = "/index.jsp";
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
