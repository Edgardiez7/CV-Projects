/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import database.AnuncioDB;
import database.HistoricoAnuncioDB;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.Date;
import java.util.ArrayList;
import modelo.Anuncio;
import modelo.Gestor;
import modelo.HistoricoAnuncio;

/**
 *
 * @author miris
 */
@WebServlet(name = "aplicarFiltroTurismo", urlPatterns = {"/aplicarFiltroTurismo"})
public class FiltroDestino extends HttpServlet {

    private String destino;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parameters from the request
        destino = request.getParameter("destino");
        int tipo = Integer.parseInt(request.getParameter("tipo"));

        ArrayList<Anuncio> listaAnunciosFiltrados;
        listaAnunciosFiltrados = AnuncioDB.getAnunciosFiltrados(destino, tipo);
        request.setAttribute("anuncio", listaAnunciosFiltrados);

        switch (tipo) {
            case 1: {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/desplazamiento.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case 2: {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/alojamiento.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case 3: {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/turismo.jsp");
                dispatcher.forward(request, response);
                break;
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
