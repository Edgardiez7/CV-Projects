<!DOCTYPE html>
<html lang="es">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="icon" type="image/png" href="img/edutravel_trans.png">
        <link href="styles/header_footer.css" rel="stylesheet" type="text/css">
        <link href="styles/informacion.css" rel="stylesheet" type="text/css">
        <title>Edutravel</title>
    </head>



    <body>

        <%@ include file="/header.jsp" %> 


        <!-- Cuerpo de la pagina-->
        <span id="titulo-anuncio">Titulo del anuncio</span>
        <span id="titulo-pagina">Informacion del Pedido</span>


        <div id="Edificio">
            <img src="img/SagradaFamilia.jpg" width="216px" height="300px" alt="Tipos de turismo">
        </div>

        <div id="Lugar">
            <a href="https://sagradafamilia.org/es/" URL target="_blank">Enlace a la Web </a>
        </div>

        <div id="Mapa">
            <iframe
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d5985.063352475102!2d2.173016578022466!3d41.405975914140846!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x12a4a2db868f209b%3A0xdd709ffba518881c!2sSagrada%20Familia%2C%20Barcelona!5e0!3m2!1ses!2ses!4v1616410574504!5m2!1ses!2ses"
                width="400" height="305" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
        </div>

        <a href="compraRealizada.jsp"><button type="button">Simulacion de compra realizada</button></a>


        <%@ include file="/footer.jsp" %>


    </body>

</html>