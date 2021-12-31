<!DOCTYPE html>
<html lang="es">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="icon" type="image/png" href="img/edutravel_trans.png">
    <link href="styles/header_footer.css" rel="stylesheet" type="text/css">
    <link href="styles/compraRealizada.css" rel="stylesheet" type="text/css">
    <title>Edutravel</title>
</head>

<body>
    <div id="container">
        <%@ include file="/header.jsp" %> 

        <!-- Contenido de la pagina principal -->

        <nav id="menu-centro">
            <span id="titulo-pagina">CONFIRMACION DE LA COMPRA</span>
            <div id="confirmacion">
                <form id="confirmacion">
                    <p class="parrafo">¡Compra realizada!</p>
                    <p class="parrafo">Revise su correo electronico, proximamente recibira un correo con la informacion
                        de
                        su compra.</p>

                    <div id="pop-up">
                        <table id="textmov">
                            <tr>
                                <td>
                                    <font face="Impact">
                                        <marquee behavior="alternate" direction="up" width="160%">
                                            <marquee bgcolor=lightgrey direction="right" behavior="alternate">
                                                <p> ¿Quiere realizar otra compra? </p>
                                            </marquee>
                                        </marquee>
                                    </font>
                                </td>
                            </tr>
                        </table>
                        <p id="titulo">¡Revise otras de nuestras ofertas!</p>
                        <div id="menu">

                            <a id="turismo" href="mostrarAnuncio?tipo=3">
                                <div class="titulo">Turismo</div>
                            </a>

                            <a id="desplazamiento" href="mostrarAnuncio?tipo=1">
                                <div class="titulo">Desplazamiento</div>
                            </a>

                            <a id="alojamiento" href="mostrarAnuncio?tipo=2">
                                <div class="titulo">Alojamiento</div>
                            </a>


                        </div>
                    </div>

                </form>
            </div>


        </nav>

        <%@ include file="/footer.jsp" %>

</body>

</html>