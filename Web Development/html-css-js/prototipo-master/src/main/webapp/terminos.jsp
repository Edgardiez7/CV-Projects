<!DOCTYPE html>
<html lang="es">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="icon" type="image/png" href="img/edutravel_trans.png">
        <link href="styles/header_footer.css" rel="stylesheet" type="text/css">
        <link href="styles/elementosfooter.css" rel="stylesheet" type="text/css">
        <title>Edutravel</title>
    </head>

    <body>
        <div id="container">

            <%@ include file="/header.jsp" %> 

            <!-- Contenido de la pagina (terminos y condiciones / legales)-->

            <nav id="terminos-y-licencias">
                <h1>Términos y licencias</h1>

                <a  class="pregunta" href="condiciones.jsp">
                    <h3>Términos y condiciones</h3>
                </a>
                <div class="respuesta"> Esta página web es propiedad y está operado por Edurotravel. Estos Términos establecen los términos y condiciones bajo los cuales tu puedes usar nuestra página web y servicios ofrecidos por nosotros. Esta página web ofrece a los visitantes una forma sencilla y amigable de contratar unas vacaciones estupendas. Al acceder o usar la página web de nuestro servicio, usted aprueba que haya leído, entendido y aceptado estar sujeto a estos Términos.</div>
                <div>
                    <a class="saber" href="condiciones.jsp">Saber más...</a>
                </div>

                <div class="background1">
                    <a class="pregunta" href="legal.jsp">
                        <h3>Información legal</h3>
                    </a>
                    <div class ="respuesta"><p>Directores generales: Édgar Díez Alonso, Manuel Méndez Calvo, Pablo Verdejo Santana y Miranda Silva del Valle.</p>

                        <p>Número de identificación de empresa: EDU.7854.721.388 TR</p>

                        <p>Inscrito en el Registro Mercantil de Valladolid, Spain.</p>

                        <p>Sede de la empresa: Valladolid, Spain.</p>

                        <p>Número de identificación fiscal (VAT): EDU.7854.721.388 TR.</p>
                    </div>
                    <div>
                        <a class="saber" href="legal.jsp">Saber más...</a>
                    </div>
                </div>

                <a  class="pregunta" href="privacidad.jsp">
                    <h3>Avisos de privacidad</h3>
                </a>
                <div class="respuesta"><p>Edurotravel esta registrada en Irlanda con el numero de registro 104547 y su domicilio social se encuentra en la oficina de Edurotravel Dublin, Airside Business Park, Valladolid, Co. Spain.</p>
                    <p>Podemos contratar a terceros para que brinden servicios en nuestro nombre. Si reserva un servicio ofrecido por un tercero (por ejemplo, seguro de viaje, alquiler de automovil, reserva de habitacion), el tercero se identificara en los terminos y condiciones de reserva aplicables. El tercero sera un "controlador de datos" de sus datos personales con respecto al servicio que esta prestando. Debe acceder a las politicas / declaraciones de privacidad de esos proveedores directamente. </p>
                </div>
                <div>
                    <a class="saber" href="privacidad.jsp">Saber más...</a>
                </div>


            </nav>
            <!-- Pie de página-->

            <%@ include file="/footer.jsp" %>

        </div>
    </body>

</html>
