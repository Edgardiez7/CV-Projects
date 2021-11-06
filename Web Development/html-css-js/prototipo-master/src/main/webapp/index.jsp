<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="icon" type="image/png" href="img/edutravel_trans.png">
        <link href="styles/menu.css" rel="stylesheet" type="text/css">
        <link href="styles/header_footer.css" rel="stylesheet" type="text/css">
        <title>Edutravel</title>
    </head>

    <body>

        <%@ include file="/header.jsp" %> 

        <!-- Contenido de la pagina principal -->

        <div id="menu">

            <a id="principal" href="mostrarAnuncio?tipo=3">
                <div class="titulo">Turismo</div>
                <img class="imagenprincipal" src="img/turismo.png" alt="Tipos de turismo">
            </a>

            <a id="desplazamiento" href="mostrarAnuncio?tipo=1">
                <div class="titulo">Desplazamiento</div>
                <img class="imagensecundaria" src="img/transporte.jpg" alt="Medios de transporte">
            </a>

            
            <a id="alojamiento" href="mostrarAnuncio?tipo=2">
                <div class="titulo">Alojamiento</div>
                <img class="imagensecundaria" src="img/alojamientos.jpg" alt="Tipos de alojamiento turístico">
            </a>

        </div>

        <%@ include file="/footer.jsp" %>

    </body>

</html>