<%@page import="modelo.Compra"%>
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
        <title>Edutravel</title>
    </head>

    <body>


        <div id="container">

            <%@ include file="/header.jsp" %> 
            <nav id="menu-centro">
                <%  Cliente cliente = null;
                    if (session.getAttribute("cliente") != null) {
                        cliente = (Cliente) session.getAttribute("cliente");
                        nombre = cliente.getNombre();
                    }
                %>
                <span id="titulo-pagina">Historial de compras de <%= nombre%> </span>

                <table class="default">
                    <tr>
                        <th>ID</th>

                        <th>Título del anuncio</th>

                        <th>Fecha de compra</th>

                    </tr>
                    <%
                        ArrayList<Compra> listaCompras;
                        listaCompras = (ArrayList<Compra>) request.getAttribute("historico");

                    %>
                    <% for (Compra compra : listaCompras) {
                            if (compra.getDNI().equals(cliente.getDNI())) {


                    %>


                    <tr>
                        <td><%= compra.getID()%></td>

                        <td><%= compra.getNombre()%></td>

                        <td><%= compra.getFechaCompra()%></td>


                    </tr>


                    <%
                            }
                        }
                    %>
                </table>
            </nav>

        </div>
        <!-- Pie de pagina-->

        <%@ include file="/footer.jsp" %>


    </body>

</html>
