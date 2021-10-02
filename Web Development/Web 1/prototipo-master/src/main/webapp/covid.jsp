<!DOCTYPE html>
<html lang="es">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="icon" type="image/png" href="img/edutravel_trans.png">
	<link href="styles/header_footer.css" rel="stylesheet" type="text/css">
	<link href="styles/covid.css" rel="stylesheet" type="text/css">
	<title>Edutravel</title>
</head>

<body>
	<div id="container">
		<%@ include file="/header.jsp" %> 

        <!-- Contenido de la página (apartados covid)-->
        <nav id="contenido_ayuda">
            <div class = "titulo">
                <a href="reclamaciones.jsp">
                    <span class="subtitulo">Política de Reclamaciones</span>
                    <img src="img/reclamaciones.png" width="200" height="200" alt="Formulario de reclamaciones">
                </a>
            </div>
            <div class = "titulo">
                <a href="https://www.mscbs.gob.es/profesionales/saludPublica/ccayes/alertasActual/nCov/situacionActual.htm" URL target="_blank">
                    <span class="subtitulo">Situación actual</span>
                    <img src="img/grafica.png" width="200" height="200" alt="Gráfica covid">
                </a>
            </div>
            <div class = "titulo">
                <a href="https://www.mscbs.gob.es/profesionales/cargarNotas.do" URL target="_blank">
                    <span class="subtitulo">Avisos por Covid</span>
                    <img src="img/avisos.jpg" width="200" height="200" alt="Avisos por covid">
                </a>
            </div>
            <div class = "titulo">
                <a href="https://espanol.cdc.gov/coronavirus/2019-ncov/travelers/index.jsp" URL target="_blank">
                    <span class="subtitulo">Viajar con Covid</span>
                    <img src="img/viajar.jpg" width="200" height="200" alt="Formulario de reclamaciones">
                </a>
            </div>
            <div class = "titulo">
                <a href="https://www.mscbs.gob.es/profesionales/saludPublica/ccayes/alertasActual/nCov/estrategia/medidasPrevCCAA.htm" URL target="_blank">
                    <span class="subtitulo">Restricciones de Movilidad</span>
                    <img src="img/movilidad.jpg" width="200" height="200" alt="Formulario de reclamaciones">
                </a>
            </div>
        </nav>

		<%@ include file="/footer.jsp" %>

	</div>
</body>

</html>
