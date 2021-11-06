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

		<!-- Contenido de la página (preguntas + respuestas)-->
		<nav id="contenido ayuda">
			<h1>Preguntas comunes</h1>
	</div>
	<div class="pregunta">
		<span>Buscar mis reservas.</span>
	</div>
	<div class="respuesta">
		<p>Compruebe la bandeja de entrada y la carpeta de spam de su gestor de correo electrónico. EDUTRAVEL
			le envió un email
			con los datos de la reserva en el momento de la compra.
			En caso de no encontrarlo, póngase en contacto con Atención al Cliente de EDUTRAVEL y le enviaremos
			la información
			requerida.
		</p>
	</div>
	<div class="background1">
		<div class="pregunta">
			<span>Modificar o cancelar mi reserva.</span>
		</div>
		<div class="respuesta">
			<p>Ya que la reserva está vinculada a la empresa que has seleccionado,
				EDUTRAVEL no interviene ni en la reserva ni en el pago, así que la información de tu reserva no
				puede ser verificada.
				Por lo que, si tienes alguna pregunta sobre cambios o cancelaciones de reservas, contacta con el
				servicio de atención al cliente de la empresa con la que hayas hecho la reserva. Si no puedes
				recordar con qué empresa has hecho la reserva, comprueba la información del email de confirmación de
				reserva.
				Si miras el destino del pago, podrás comprobar el destino de la reserva.</p>
		</div>
	</div>
	<div class="pregunta">
		<span>¿Los precios incluyen tasas e impuestos?</span>
	</div>
	<div class="respuesta">
		<p>Todos los precios de los productos mostrados en EDUTRAVEL incluyen tasas e impuestos aplicables;
			a excepción de tasas opcionales por equipaje facturado, selección de asiento, mascotas, etc.
			Algunos proveedores pueden cobrar una tasa adicional por servicio, equipaje y por usar ciertos
			métodos de pago.</p>
	</div>
	<div class="background1">
		<div class="pregunta">
			<span>¿Puedo reservar un viaje para otra persona?</span>
		</div>
		<div class="respuesta">
			<p>No hay ningún problema si quieres pagar el viaje de otra persona. No obstante, al introducir los
				detalles del viajero
				debes asegurarte de que el nombre que escribas sea el mismo que aparece en el documento de identidad
				del viajero.
				Un simple fallo en una letra puede significar que la persona no pueda viajar. Además, ten en cuenta
				que muchas empresas
				cobran un recargo adicional por modificar la información después de haber reservado el vuelo.
			</p>
		</div>
	</div>
	<div class="pregunta">
		<span>¿Cómo puedo combinar dos viajes?</span>
	</div>
	<div class="respuesta">
		<p>EDUTRAVEL no permite actualmente combinar dos viajes de forma automática, por lo que el viajero
			deberá realizar dos
			o más reservas diferentes si desea contratar más de un producto.
		</p>
	</div>
	<div class="background1">
		<div class="pregunta">
			<span>¿Qué hago para dejar de recibir emails?</span>
		</div>
		<div class="respuesta">
			<p>En su gestor de correo electrónico marque como spam cualquier correo enviado por EDUTRAVEL y de
				forma automática éstos serán
				redirigidos a la carpeta de "spam".</p>
		</div>
	</div>
	<div class="pregunta">
		<span>¿Cuál es la política de reembolsos de EDUTRAVEL?</span>
	</div>
	<div class="respuesta">
		<p> Dado que EDUTRAVEL es un motor de búsqueda y no un proveedor, no contamos con una política de
			reembolsos. De esto se encarga
			el proveedor con el que reserves. ¿No sabes a quién pedir más información? Comprueba el cargo
			realizado a tu tarjeta de crédito
			y/o el correo electrónico enviado tras la reserva para encontrar más información sobre el proveedor
			con el que has reservado.</p>
	</div>
	<div class="background1">
		<div class="pregunta">
			<span>Quiero organizar un viaje con varias personas, ¿puedo realizar reservas de vuelos para
				grupos?</span>
		</div>
		<div class="respuesta">
			<p> ¡Claro que sí! Puedes contratar servicios en EDUTRAVEL para el número de personas que desees
				siempre y cuando haya un número
				suficiente de plazas disponibles.</p>
		</div>
	</div>
	<div class="pregunta">
		<span>¿Qué tengo que hacer para que mi servicio aparezca en EDUTRAVEL?</span>
	</div>
	<div class="respuesta">
		<p> ¿Te gustaría asociar tu producto con nuestra web? Haga
			<a href="login.jsp">login</a> como empresa o <a href="login.jsp">regístrese</a> si aún no cuenta con una
			cuenta y
			añada su anuncio.
		</p> <!-- añandir urls login y registro-->
	</div>
	</div>
	</nav>

	<%@ include file="/footer.jsp" %>

	</div>
</body>

</html>
