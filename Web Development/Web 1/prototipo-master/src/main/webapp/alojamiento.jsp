<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Anuncio"%>
<%@page session = "true" %>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="icon" type="image/png" href="img/edutravel_trans.png">
        <link href="styles/header_footer.css" rel="stylesheet" type="text/css">
        <link href="styles/alojamiento.css" rel="stylesheet" type="text/css">
        <title>Edutravel</title>
    </head>

    <body>
        <%@ include file="/header.jsp" %> 

        <nav id="menu-centro">

            <span id="titulo-pagina">ALOJAMIENTO</span>


            <div id="centro">
                <form action="aplicarFiltroTurismo?tipo=2" method="post" id="formulario-superior" name="formulario-superior">
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
                        <label class="titulo-busqueda">Tipo alojamiento</label>
                        <div id="alojamientos">
                            <label class="seleccionable"><input type="checkbox" value="rural">Casa rural</label>
                            <label class="seleccionable"><input type="checkbox" value="hotel">Hotel</label>
                            <label class="seleccionable"><input type="checkbox" value="albergue">Albergue</label>
                        </div>

                        <label class="titulo-busqueda" for="rango">Precio por noche</label>
                        <div id="barra">
                            <input type="range" id="rango" name="rango" min="0" max="150">
                        </div>

                        <label class="titulo-busqueda" for="rango">Valoraciones</label>

                        <div id="estrellas">

                            <label class="seleccionable"><input type="radio" id="cinco" name="valoracion">5 <img src="img/star.svg" width="20"
                                                                                                                 height="20">
                                <img src="img/star.svg" width="20"
                                     height="20">
                                <img src="img/star.svg" width="20"
                                     height="20">
                                <img src="img/star.svg" width="20"
                                     height="20">
                                <img src="img/star.svg" width="20"
                                     height="20"></label>
                            <label class="seleccionable"><input type="radio" id="cuatro" name="valoracion">4 <img src="img/star.svg" width="20"
                                                                                                                  height="20">
                                <img src="img/star.svg" width="20"
                                     height="20">
                                <img src="img/star.svg" width="20"
                                     height="20">
                                <img src="img/star.svg" width="20"
                                     height="20">
                            </label>
                            <label class="seleccionable"><input type="radio" id="tres" name="valoracion">3 <img src="img/star.svg" width="20"
                                                                                                                height="20">
                                <img src="img/star.svg" width="20"
                                     height="20">
                                <img src="img/star.svg" width="20"
                                     height="20"></label>
                            <label class="seleccionable"><input type="radio" id="dos" name="valoracion">2 <img src="img/star.svg" width="20"
                                                                                                               height="20">
                                <img src="img/star.svg" width="20"
                                     height="20"></label>
                            <label class="seleccionable"><input type="radio" id="uno" name="valoracion">1 <img src="img/star.svg" width="20"
                                                                                                               height="20"></label>
                        </div>

                    </form>
                </div>

                <div id="centro-dcha">
                    <table class="default">
                        <tr>
                            <th>Titulo</th>

                            <th>Destino</th>

                            <th>Descripcion</th>

                            <th>Precio</th>
                        </tr>
                        <%  ArrayList<Anuncio> listaAnuncios;
                            listaAnuncios = (ArrayList<Anuncio>) request.getAttribute("anuncio");
                        %>
                        <% for (Anuncio anuncio : listaAnuncios) {


                        %>


                        <tr>
                            <td><%= anuncio.getTitulo()%></td>

                            <td><%= anuncio.getDestino()%></td>

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

        <%@ include file="/footer.jsp" %>

    </body>

</html>

