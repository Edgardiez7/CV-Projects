<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Anuncio"%>
<%@page import="controladores.CompraAnuncio"%>
<%@page session = "true" %>
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
                <span id="titulo-pagina">TURISMO</span>
                <div id="centro">
                    <form action="aplicarFiltroTurismo?tipo=3" method="post" id="formulario-superior" name="formulario-superior">
                        <div class="centrar">
                            <label class="distanciar" for="destino">Destino</label>
                            <input type="text" id="destino" name="destino">
                        </div>
                        
                        <div class="centrar">
                            <input type="submit" value="Buscar" name="filtro">
                        </div>
                    </form>
                </div>

                <div id="bloque-central">

                    <div id="centro-izda">
                        <span>FILTRAR POR</span>
                        <form>
                            <label class="titulo-busqueda">Tipo turismo</label>
                            <div id="turismos">
                                <label><input type="checkbox" value="deportivo">Deportivo</label>
                                <label><input type="checkbox" value="cultural">Cultural</label>
                                <label><input type="checkbox" value="ocio">Ocio</label>
                                <label><input type="checkbox" value="gastronomico">Gastronomico</label>
                            </div>
                            <label class="titulo-busqueda" for="rango">Precio</label>
                            <input type="range" id="rango" name="rango" min="0" max="150">
                        </form>
                    </div>
                    <div id="centro-dcha">
                        <table class="default">
                            <tr>
                                <th>Titulo

                                </th>
                                <th>Destino

                                </th>
                                <th>Descripcion

                                </th>
                                <th>Precio

                                </th>

                            </tr>
                            <%  ArrayList<Anuncio> listaAnuncios;
                                listaAnuncios = (ArrayList<Anuncio>) request.getAttribute("anuncio");

                            %>




                            <% for (Anuncio anuncio : listaAnuncios) {


                            %>


                            <tr>

                                <td><%= anuncio.getTitulo()%></td>

                                <td><%= anuncio.getDestino()%> </td>

                                <td><%= anuncio.getDescripcion()%></td>

                                <td><%= anuncio.getPrecio()%></td>

                                <%
                                    String boton = "";
                                    String mensaje = "";
                                    if (session.getAttribute("cliente") == null) {
                                        boton = "disabled";
                                        mensaje = "Debes iniciar sesion como cliente";
                                    }
                                %>

                                <td><form action="compra" method="post"> <input type="hidden" name="IDAnuncio" value="<%= anuncio.getId()%>" > <input  value="¡Comprar!"  type="submit" title="<%=mensaje%>" name="Comprar" <%=boton%>> </form></td>

                            </tr>


                            <%
                                }
                            %>
                        </table>
                    </div>
                </div>

            </nav>
            <!-- Pie de pagina-->

            <%@ include file="/footer.jsp" %>


        </div>
    </body>

</html>
