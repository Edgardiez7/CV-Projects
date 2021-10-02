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
import modelo.Anuncio;
import modelo.Gestor;
import modelo.HistoricoAnuncio;

/**
 *
 * @author miris
 */
@WebServlet(name = "CrearAnuncio", urlPatterns = {"/creaAnuncio"})
public class CrearAnuncio extends HttpServlet {

    private String titulo;
    private String descripcion;
    private int precio;
    private Date fechaIni;
    private Date fechaFin;
    private String maps;
    private String destino;
    private int tipo;
    private int numPersonas;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parameters from the request
        titulo = request.getParameter("tituloAnuncio");
        descripcion = request.getParameter("descripcion");
        precio = Integer.parseInt(request.getParameter("precio"));
        String DateIni = request.getParameter("fechaIni");
        String DateFin = request.getParameter("fechaFin");
        fechaIni = Date.valueOf(DateIni);
        fechaFin = Date.valueOf(DateFin);
        maps = "";
        destino = request.getParameter("ubicacion");
        tipo = Integer.parseInt(request.getParameter("tipoTurismo"));

        Anuncio anuncio = new Anuncio(titulo, descripcion, precio, fechaIni, fechaFin, maps, destino, tipo);
        int id = AnuncioDB.insert(anuncio);
        anuncio.setId(id);

        // Guardamos al cliente en la sesion
        HttpSession session = request.getSession();
        session.setAttribute("anuncio", anuncio);
        Gestor gestor = (Gestor) session.getAttribute("gestor");
        String CIF = gestor.getCIF();
                
        HistoricoAnuncio historico = new HistoricoAnuncio(id, CIF);
        int idHistorico = HistoricoAnuncioDB.insert(historico);
        
        historico.setId(idHistorico);
        
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher("/mostrarHisAnuncio");
        dispatcher.forward(request, response);
        // forward request and response to JSP page

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
