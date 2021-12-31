package tds.p3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


/**
 * 
 * @author edgdiez Edgar Diez Alonso
 * 
 */


class PruebasSecuenciaTest {

	private Correo correo0 = new Correo("francisco_perez88@gmail.com", "arancha_garcia94@yahoo.es",
			LocalDate.of(2001, 1, 1), LocalTime.of(1, 1), "Averia en la calefaccion", "Llama al fontanero",
			Categoria.BORRADOR);
	private Correo correo1 = new Correo("alejandro_perez88@gmail.org.com", "merche_garcia94@yahoo.net.es",
			LocalDate.of(2020, 10, 3), LocalTime.of(9, 45), "Y volo", "Accidente en la m30", Categoria.BORRADOR);
	private Correo correo2 = new Correo("francisco2_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2020, 10, 3), LocalTime.of(9, 40), "Fiesta de pijamas2", "Esta noche2", Categoria.ENVIADO);
	
	@Test
	@Tag("Secuencia")
	void añadirCorreoAGestorYObtenerListaOrdenadaTest() {
		GestorCorreo gestor = new GestorCorreo();
		gestor.añadirCorreo(correo0);
		gestor.añadirCorreo(correo1);
		ArrayList<Correo> listaCorreosOrdenada = gestor.getListaOrdenadaCorreos();
		assertNotNull(listaCorreosOrdenada);
		assertEquals(listaCorreosOrdenada.size(), 2);
		assertSame(listaCorreosOrdenada.get(0), correo0);
		assertEquals(listaCorreosOrdenada.get(1).getAsunto(), "Y volo");
	}
	
	@Test
	@Tag("Secuencia")
	void añadirCorreoAGestorYObtenerDeUnaFecha() {
		GestorCorreo gestor = new GestorCorreo();
		gestor.añadirCorreo(correo0);
		gestor.añadirCorreo(correo1);
		gestor.añadirCorreo(correo2);
		ArrayList<Correo> correosDeUnaFecha = gestor.getCorreosDeUnaFecha(LocalDate.of(2001, 1, 1));
		assertNotNull(correosDeUnaFecha);
		assertEquals(correosDeUnaFecha.size(), 1);
		assertSame(correosDeUnaFecha.get(0), correo0);
		assertEquals(correosDeUnaFecha.get(0).getAsunto(), "Averia en la calefaccion");
	}
	
	@Test
	@Tag("Secuencia")
	void añadirCorreoAGestorYObtenerDeUnaFechaYCategoria() {
		GestorCorreo gestor = new GestorCorreo();
		gestor.añadirCorreo(correo0);
		gestor.añadirCorreo(correo1);
		gestor.añadirCorreo(correo2);
		ArrayList<Correo> correosDeUnaFecha = gestor.getCorreosDeUnaFechaDeUnaCategoria(LocalDate.of(2001, 1, 1), Categoria.BORRADOR);
		assertNotNull(correosDeUnaFecha);
		assertEquals(correosDeUnaFecha.size(), 1);
		assertSame(correosDeUnaFecha.get(0), correo0);
		assertEquals(correosDeUnaFecha.get(0).getAsunto(), "Averia en la calefaccion");
	}
	
	@Test
	@Tag("Secuencia")
	void añadirCorreoAGestorYObtenerCorreosEntreFechasYCategoria() {
		GestorCorreo gestor = new GestorCorreo();
		gestor.añadirCorreo(correo0);
		gestor.añadirCorreo(correo1);
		gestor.añadirCorreo(correo2);
		ArrayList<Correo> correosDeUnaFecha = gestor.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(2001, 1, 1), LocalDate.of(2001, 1, 1),Categoria.BORRADOR);
		assertNotNull(correosDeUnaFecha);
		assertEquals(correosDeUnaFecha.size(), 1);
		assertSame(correosDeUnaFecha.get(0), correo0);
		assertEquals(correosDeUnaFecha.get(0).getAsunto(), "Averia en la calefaccion");
	}
	
	@Test
	@Tag("Secuencia")
	void añadirCorreoAGestorObtenerCorreosOrdenadosPorCategoria() {
		GestorCorreo gestor = new GestorCorreo();
		gestor.añadirCorreo(correo0);
		gestor.añadirCorreo(correo1);
		gestor.añadirCorreo(correo2);
		ArrayList<Correo> correosDeUnaFecha = gestor.getListaOrdenadaPorCategoria();
		assertNotNull(correosDeUnaFecha);
		assertEquals(correosDeUnaFecha.size(), 3);
		assertSame(correosDeUnaFecha.get(0), correo2);
		assertEquals(correosDeUnaFecha.get(0).getAsunto(), "Fiesta de pijamas2");
	}
}
