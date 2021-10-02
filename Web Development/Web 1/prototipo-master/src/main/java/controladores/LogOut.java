package controladores;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author miris
 */
@WebServlet(name = "logout", urlPatterns = {"/logout"})
public class LogOut extends HttpServlet {

    private int ID;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher("/index.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
