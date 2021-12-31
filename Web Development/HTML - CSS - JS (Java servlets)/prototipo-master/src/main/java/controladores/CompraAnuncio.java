package controladores;

import database.HistoricoCompraDB;
import java.io.*;
import java.time.LocalDate;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.HistoricoCompra;
import util.GestorCorreo;

/**
 *
 * @author miris
 */
@WebServlet(name = "CompraAnuncio", urlPatterns = {"/compra"})
public class CompraAnuncio extends HttpServlet {

    private int ID;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        ID = Integer.parseInt(request.getParameter("IDAnuncio"));
        String DNI = cliente.getDNI();

        HistoricoCompra historico = new HistoricoCompra(ID, DNI);
        int idHistorico = HistoricoCompraDB.insert(historico);
        historico.setId(idHistorico);
        String titulo = HistoricoCompraDB.buscarTitulo(ID);   
        GestorCorreo gc = new GestorCorreo();
        gc.sendMail(titulo, LocalDate.now(),cliente.getMail());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/compraRealizada.jsp");
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
