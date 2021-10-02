package controladores;

import database.AnuncioDB;
import database.HistoricoAnuncioDB;
import database.HistoricoCompraDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Anunciado;
import modelo.Anuncio;
import modelo.Compra;

@WebServlet(name = "mostrarHisCompra", urlPatterns = {"/mostrarHisCompra"})
public class MostrarHisCompra extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Compra> listaHisCompra;
        listaHisCompra = HistoricoCompraDB.getCompras();
        request.setAttribute("historico", listaHisCompra);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/historialCompras.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    // Metodos para recoger los anuncios de la base de datos
    /*public static ArrayList<Compra> mostrarComprasHistorico() {
        return HistoricoCompraDB.getCompras();
    }*/
}
