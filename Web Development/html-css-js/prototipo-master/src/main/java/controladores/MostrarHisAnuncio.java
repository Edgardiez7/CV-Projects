package controladores;


import database.HistoricoAnuncioDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Anunciado;


@WebServlet(name = "mostrarHisAnuncio", urlPatterns = {"/mostrarHisAnuncio"})
public class MostrarHisAnuncio extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Anunciado> listaHistorico;
        listaHistorico = HistoricoAnuncioDB.getAnuncios();
        request.setAttribute("historico", listaHistorico);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/historialAnuncios.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

}
