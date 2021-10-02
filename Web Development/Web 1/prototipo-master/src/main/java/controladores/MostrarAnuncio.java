package controladores;

import database.AnuncioDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Anuncio;

@WebServlet(name = "mostrarAnuncio", urlPatterns = {"/mostrarAnuncio"})
public class MostrarAnuncio extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int tipo = Integer.parseInt(request.getParameter("tipo"));
        ArrayList<Anuncio> listaAnuncios;
        listaAnuncios = AnuncioDB.getAnuncios(tipo);
        request.setAttribute("anuncio", listaAnuncios);

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

    // Metodos para recoger los anuncios de la base de datos
    /*public static ArrayList<Anuncio> mostrarAnuncios(int tipo) {
        return AnuncioDB.getAnuncios(tipo);
    }*/
}
