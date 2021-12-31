<%@page import="modelo.Gestor"%>
<%@page import="modelo.Cliente"%>
<%@page session = "true" %>

<!DOCTYPE html>
<header id="header">

    <div id="caja-logo">
        <a id="logo" href="index.jsp"> <img id="logoImagen" src="img/trilogo.png" alt="Logo de la empresa"></a>
        <a href="index.jsp" id="nombre">EDUTRAVEL</a>
    </div>



    <a id="soporte" href="ayuda.jsp"><img class="imagenCabecera" src="img/apoyo.svg" alt="Simbolo de ayuda"> 
    </a>


    <div id="moneda">
        <img class="imagenCabecera" src="img/euro.svg" alt="Simbolo de euro">
    </div>

    <div id="idioma">
        <img id="dropdown" class="imagenCabecera" src="img/flags.svg" alt="Simbolo de bandera">
        <ul id="dropdown-content">
            <li><img class="icono" src="img/paises/china.svg" alt="Bandera de china"></li>
            <li><img class="icono" src="img/paises/croatia.svg" alt="Bandera de croacia"></li>
            <li><img class="icono" src="img/paises/france.svg" alt="Bandera de francia"></li>
            <li><img class="icono" src="img/paises/germany.svg" alt="Bandera de alemania"></li>
            <li><img class="icono" src="img/paises/india.svg" alt="Bandera de india"></li>
            <li><img class="icono" src="img/paises/italy.svg" alt="Bandera de italia"></li>
            <li><img class="icono" src="img/paises/japan.svg" alt="Bandera de japon"></li>
            <li><img class="icono" src="img/paises/portugal.svg" alt="Bandera de portugal"></li>
            <li><img class="icono" src="img/paises/rusia.svg" alt="Bandera de rusia"></li>
            <li><img class="icono" src="img/paises/spain.svg" alt="Bandera de spain"></li>
            <li><img class="icono" src="img/paises/turkey.svg" alt="Bandera de turquia"></li>
            <li><img class="icono" src="img/paises/united-kingdom.svg" alt="Bandera de UK"></li>	
        </ul>
    </div>

    <%
        Boolean login = false;
        String url = "login.jsp";
        String nombre = "Login";
        String foto = "display: block";

        if (session.getAttribute("cliente") != null) {
            Cliente cliente = (Cliente) session.getAttribute("cliente");
            nombre = cliente.getNombre();
            login = cliente.getLogin();
            if (login) {
                url = "mostrarHisCompra";
                foto = "display: none";

            }
        }
        if (session.getAttribute("gestor") != null) {
            Gestor gestor = (Gestor) session.getAttribute("gestor");
            nombre = gestor.getCIF();
            login = gestor.getLogin();
            if (login) {
                url = "mostrarHisAnuncio";
                foto = "display: none";

            }
        }

    %>
    <a id="login" href=<%=url%>>
        <span id="loginT"><%=nombre%></span>
        <img id="logo-login" src="img/login.svg" style="<%=foto%>" alt="Simbolo de login">
    </a>
    <%
        if (login) {
    %>
    <form action="logout" method="post" id="infoAnuncio">
        <div id="logOut">
            <input id="loginT" type="submit" value="logOut" name="anuncio">
            <img id="logo-login" src="img/login.svg" alt="Simbolo de login">
        </div>
    </form>
    <%
        }
    %>


</header>



