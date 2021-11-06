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
import database.GestorDB;
import modelo.Gestor;

/**
 *
 * @author miris
 */
@WebServlet(name = "RegistroGestor", urlPatterns = {"/registraGestor"})
public class RegistroGestor extends HttpServlet {

    private String CIF;
    private String codCuenta;
    private String contrasena;
    private String correo;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parameters from the request
        CIF = request.getParameter("CIF");
        contrasena = request.getParameter("Contrasena");
        codCuenta = request.getParameter("Cotizacion");
        correo = request.getParameter("Mail");

        Gestor gestor = new Gestor(CIF, codCuenta, contrasena, correo);

        // forward request and response to JSP page
        GestorDB.insert(gestor);
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
