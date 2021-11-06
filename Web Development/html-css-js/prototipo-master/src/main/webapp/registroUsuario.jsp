<!DOCTYPE html>
<html lang="es">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="icon" type="image/png" href="img/edutravel_trans.png">
        <link href="styles/header_footer.css" rel="stylesheet" type="text/css">
        <link href="styles/registro.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="scripts/validarCamposRegUsuario.js"></script>        
        <title>Edutravel</title>
    </head>



    <body>

        <%@ include file="/header.jsp" %>       


        <span id="titulo-pagina">REGISTRO USUARIO</span>

        <div id="registroPrincipal">
            <span id="titulo">Formulario de registro</span>
            <form action="registraCliente" method="post" onsubmit="return validarRegUsuario();" id="nuevoRegistro" name="formulario"> 

                <div id="DNId">
                    <label for="DNI">DNI*</label>
                    <input type="text" name="DNI" required>
                </div>

                <div id="Nombre">
                    <label for="Nombre">Nombre*</label>
                    <input type="text" name="Nombre" required>
                </div>

                <div id="Apellidos">
                    <label for="Apellidos">Apellidos*</label>
                    <input required type="text" name="Apellidos">
                </div>

                <div id="Contasenad">
                    <label for="Contasena">Contraseña*</label>
                    <input type="password" name="Contrasena" required>
                </div>

                <div id="RepeContasenad">
                    <label for="RepeContasena">Repetir Contraseña*</label>
                    <input type="password" name="RepeContrasena" required>
                </div>

                <div id="Maild">
                    <label for="email">E-mail*</label>
                    <input required type="email" name="Mail">
                </div>

                <div id="RepeMaild">
                    <label for="email">Repetir E-mail*</label>
                    <input required type="email" name="RepeMail">
                </div>

                <p id="aclaracion">Las casillas marcadas con * son obligatorias</p>

                <input id="confirmarRegistro" type="submit" value="¡Enviar!" name="registroU">
            </form>
        </div>



    <!-- Pie de pagina-->

        <%@ include file="/footer.jsp" %>

    </body>

</html>
