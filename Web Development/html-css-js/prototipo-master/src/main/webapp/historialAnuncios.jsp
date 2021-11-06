<%@page import="modelo.Anunciado"%>
<%@page import="modelo.Gestor"%>
<%@page import="modelo.Cliente"%>
<%@page import="java.util.ArrayList"%>



<!DOCTYPE html>
<html lang="es">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="icon" type="image/png" href="img/edutravel_trans.png">
        <link href="styles/header_footer.css" rel="stylesheet" type="text/css">
        <link href="styles/turismo.css" rel="stylesheet" type="text/css">
        <link href="styles/historial.css" rel="stylesheet" type="text/css">


        <title>Edutravel</title>
    </head>

    <body>


        <div id="container">

            <%@ include file="/header.jsp" %> 
            <nav id="menu-centro">
                <%  Gestor gestor = null;
                    if (session.getAttribute("gestor") != null) {
                        gestor = (Gestor) session.getAttribute("gestor");
                        nombre = gestor.getCIF();
                    }
                %>
                <span id="titulo-pagina">Historial de anuncios de <%= nombre%> </span>

                <table class="default">
                    <tr>
                        <th>ID</th>

                        <th>Título del anuncio</th>

                        <th>Fecha de publicacion</th>

                    </tr>
                    <%
                        ArrayList<Anunciado> listaAnunciados;
                        listaAnunciados = (ArrayList<Anunciado>) request.getAttribute("historico");

                    %>
                    <% for (Anunciado anunciado : listaAnunciados) {
                            if (anunciado.getCIF().equals(gestor.getCIF())) {


                    %>


                    <tr>
                        <td><%= anunciado.getID()%></td>

                        <td><%= anunciado.getNombre()%></td>

                        <td><%= anunciado.getFechaPublicacion()%></td>


                    </tr>


                    <%
                            }
                        }
                    %>
                </table>
                <div class="botoncito">
                    <a href="crearAnuncio.jsp"><button type="button">Crear nuevo anuncio</button>
                    </a>
                </div>
            </nav>

        </div>
        <!-- Pie de pagina-->

        <%@ include file="/footer.jsp" %>


    </body>

</html>
