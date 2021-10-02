<!DOCTYPE html>
<html lang="es">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="icon" type="image/png" href="img/edutravel_trans.png">
        <link href="styles/header_footer.css" rel="stylesheet" type="text/css">
        <link href="styles/registro.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="scripts/validarCamposRegEmpresa.js"></script>  
        <title>Edutravel</title>
    </head>



    <body>

        <%@ include file="/header.jsp" %> 


        <span id="titulo-pagina">REGISTRO EMPRESA</span>

        <div id="registroPrincipal">
            <span id="titulo">Formulario de registro</span>
            <form action="registraGestor" method="post" onsubmit="return validarRegEmpresa();" id="nuevoRegistro">

                <div id="CIF">
                    <label for="CIF">CIF*</label>
                    <input required type="text" name="CIF" required>
                </div>

                <div id="Contrasena">
                    <label for="Contasena">Contraseña*</label>
                    <input required type="password" name="Contrasena" required>
                </div>

                <div id="RepeContasena">
                    <label for="RepeContasena">Repetir Contraseña*</label>
                    <input required type="password" name="RepeContrasena" required>
                </div>

                <div id="Cotizacion">
                    <label for="Cotizacion">Codigo de cuenta de cotizacion*</label>
                    <input required type="text" name="Cotizacion" required>
                </div>

                <div id="url">
                    <label for="URL">URL </label>
                    <input type="text" name="Url">
                </div>

                <div id="Mail">
                    <label for="email">E-mail*</label>
                    <input required type="email" name="Mail" required>
                </div>

                <div id="RepeMail">
                    <label for="email">Repetir E-mail*</label>
                    <input required type="email" name="RepeMail" required>
                </div>

                <p id="aclaracion">Las casillas marcadas con * son obligatorias</p>

                <input id="confirmarRegistro" type="submit" value="¡Enviar!" name="registroE">
                
            </form>

        </div>

        <!-- Pie de pagina-->

        <%@ include file="/footer.jsp" %>

    </body>

</html>