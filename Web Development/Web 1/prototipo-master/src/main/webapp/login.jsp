<!DOCTYPE html>
<html lang="es">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="icon" type="image/png" href="img/edutravel_trans.png">
        <link href="styles/header_footer.css" rel="stylesheet" type="text/css">
        <link href="styles/login.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="scripts/Login.js"></script>
        <title>Edutravel</title>

    </head>

    <body>

        <%@ include file="/header.jsp" %> 

        <!-- Contenido de la pagina principal -->
        <%
            if(session.getAttribute("errorLogin") != null){
                
            
        %>
        <script>alert("Usuario no encontrado")</script>
        <%
            }
        %>
        <span id="titulo-pagina">LOGIN</span>
        <div id="logins">
            <div class="log-in">
                <span class="titulo">Login como empresa</span>
                <form id="empresa" action="LoginGestor" onsubmit="return loginGestor();"method="post">
                    <label for="id-empresa">CIF</label>
                    <input type="text" placeholder="Introduzca su cif" id="id-empresa" name="id-gestor">

                    <label for="password">Contraseña</label>
                    <input type="password" placeholder="Introduzca su contraseña" id="password" name="password-gestor">

                    <input class="boton-login" type="submit" value="Login" name="Login">
                </form>
            </div>
            <div class="log-in">
                <span class="titulo">Login como cliente</span>
                <form id="usuario" action="LoginUsuario" onsubmit="return loginCliente();" method="post">
                    <label for="nick">DNI</label>
                    <input type="text" placeholder="Introduzca su usuario" id="nick" name="id-usuario">

                    <label for="password">Contraseña</label>
                    <input type="password" placeholder="Introduzca su contraseña" id="passwordC" name="password-usuario">

                    <input class="boton-login" type="submit" value="Login" name="Login">
                </form>
            </div>
        </div>
        <div id="registros">
            <div class="registro">
                <span class="titulo">Registro como empresa</span>
                <a href="registroEmpresa.jsp">
                    <input class="boton-registro" type="button" value="Go!" name="registro-empresa">
                </a>
            </div>
            <div class="registro">
                <span class="titulo">Registro como cliente</span>
                <a href="registroUsuario.jsp">
                    <input class="boton-registro" type="button" value="Go!" name="registro-cliente">
                </a>
            </div>
        </div>



        <!-- Pie de pagina-->
        <%@ include file="/footer.jsp" %>


    </body>

</html>
