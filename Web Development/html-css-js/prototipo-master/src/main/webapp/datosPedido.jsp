<!DOCTYPE html>
<html lang="es">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="icon" type="image/png" href="img/edutravel_trans.png">
        <link href="styles/header_footer.css" rel="stylesheet" type="text/css">
        <link href="styles/datosPedido.css" rel="stylesheet" type="text/css">
        <title>Edutravel</title>
    </head>

 

    <body>
        <%@ include file="/header.jsp" %> 
          
 <!-- Cuerpo de página-->    

    <span id="titulo-pagina">Información del Pedido</span>
     
    <div id="Datos"> <!-- Asumimos que el usuario está logeado--> 

        <div id="Superior">
            <div class="Personas">
            <label for="Personas">Número de Personas</label>
            <input type="text" name="Personas" required>
            </div>

            <div class="Habitaciones">
            <label for="Habitaciones">Número de Habitaciones</label>
            <input type="text" name="Habitaciones" > 
            </div>
        </div>
        
        <div id="Inferior">
            <div class="FechasEntrada">
            <label for="FechaEntrada">Fecha de entrada</label>
            <input id="date" type="date" value="2021-03-20">
            </div>

            <div class="FechaSalida">
            <label for="FechaSalida">Fecha de Salida</label>
            <input id="date" type="date" value="2021-03-21">
            </div>
        </div>
    </div>

    <%@ include file="/footer.jsp" %>

    </body>
</html>