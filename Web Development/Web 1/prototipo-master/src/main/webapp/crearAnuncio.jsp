<!DOCTYPE html>
<html lang="es">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="icon" type="image/png" href="img/edutravel_trans.png">
        <link href="styles/header_footer.css" rel="stylesheet" type="text/css">
        <link href="styles/crearAnuncio.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="scripts/validarCamposCrearAnuncio.js"></script>  
        <title>Edutravel</title>
    </head>



    <body>

        <%@ include file="/header.jsp" %> 


        <!-- Contenido de la pagina principal -->

        <span id="titulo-pagina">CREAR ANUNCIO</span>

        
        <div id="formulario">
            <span id="titulo">Nuevo anuncio</span>
            <form action="creaAnuncio" method="post" onsubmit="return validarCrearAnuncio();" id="infoAnuncio" name="infoAnuncio">
                <div>
                    <label for="tituloAnuncio">Titulo*</label>
                    <input required type="text" placeholder="Introduzca un titulo para su anuncio" id="tituloAnuncio"
                           name="tituloAnuncio" size="40">
                </div>

                <div>
                    <label for="ubicacion">Ubicacion*</label>
                    <input id="ubicacion" name="ubicacion" required type="text" placeholder="Introduzca la ubicacion de su anuncio"
                           size="35">
                </div>

                <div>
                    <label for="descripcion">Descripcion</label>
                </div>
                <div>
                    <textarea id="descripcion" rows="5" cols="40" type="text" placeholder="Introduzca una breve descripcion"
                              id="descripcion" name="descripcion"></textarea>
                </div>
                <div>

                    <label for="fechaIni">Fecha inicio de la oferta*</label>
                    <input required type="date" id="fechaIni" name="fechaIni">
                </div>
                <div>

                    <label for="fechaFin">Fecha fin de la oferta*</label>
                    <input required type="date" id="fechaFin" name="fechaFin">
                </div>
                <div>

                    <label for="precio">Precio*</label>
                    <input required type="number" min="0" max="500000" id="precio" name="precio">
                </div>

                <p>Seleccione el tipo de anuncio que esta creando:</p>

                <div>
                    <input type="radio" id="turismo" name="tipoTurismo" value="3" checked>
                    <label id="radio" for="turismo">Turismo</label>
                </div>

                <div>
                    <input type="radio" id="dewey" name="tipoTurismo" value="2">
                    <label id="radio" for="alojamiento">Alojamiento</label>
                </div>

                <div>
                    <input type="radio" id="desplazamiento" name="tipoTurismo" value="1">
                    <label id="radio" for="desplazamiento">Desplazamiento</label>
                </div>

                <p id="aclaracion">Las casillas marcadas con * son obligatorias</p>


                <input id="confirmarAnuncio" type="submit" value="Confirmar" name="anuncio">
            </form>
        </div>

        <%@ include file="/footer.jsp" %>

    </body>

</html>
