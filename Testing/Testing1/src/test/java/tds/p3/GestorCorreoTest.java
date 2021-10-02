package tds.p3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


/**
 * 
 * @author edgdiez Edgar Diez Alonso
 * 
 */
public class GestorCorreoTest {

	GestorCorreo gestor;
	GestorCorreo gestorLista;
	GestorCorreo gestorLista1;
	GestorCorreo gestorLista2;

	private ArrayList<Correo> listaCorreos = new ArrayList<>();
	private ArrayList<Correo> listaCorreos1 = new ArrayList<>();
	private ArrayList<Correo> listaCorreos2 = new ArrayList<>();
	
	private ArrayList<Correo> listaCorreosComparable = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable1 = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable2 = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable3 = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable4 = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable5 = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable6 = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable7 = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable8 = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable9 = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable10 = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable11 = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable12 = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable13 = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable14 = new ArrayList<>();
	private ArrayList<Correo> listaCorreosComparable15 = new ArrayList<>();

	private Correo correo0 = new Correo("francisco_perez88@gmail.com", "arancha_garcia94@yahoo.es",
			LocalDate.of(2001, 1, 1), LocalTime.of(1, 1), "Averia en la calefaccion", "Llama al fontanero",
			Categoria.BORRADOR);
	private Correo correo1 = new Correo("alejandro_perez88@gmail.org.com", "merche_garcia94@yahoo.net.es",
			LocalDate.of(2020, 10, 3), LocalTime.of(9, 45), "Y volo", "Accidente en la m30", Categoria.BORRADOR);
	private Correo correo2 = new Correo("francisco2_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2020, 10, 3), LocalTime.of(9, 40), "Fiesta de pijamas2", "Esta noche2", Categoria.ENVIADO);
	private Correo correo3 = new Correo("francisco3_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2020, 10, 3), LocalTime.of(9, 45), "Fiesta de pijamas3", "Esta noche3", Categoria.PAPELERA);
	private Correo correo4 = new Correo("francisco4_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2020, 10, 3), LocalTime.of(9, 45), "Fiesta de pijamas4", "Esta noche4", Categoria.ENVIADO);
	private Correo correo5 = new Correo("francisco5_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2010, 10, 3), LocalTime.of(9, 40), "Fiesta de pijamas5", "Esta noche5", Categoria.RECIBIDO);
	private Correo correo6 = new Correo("francisco6_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2020, 10, 3), LocalTime.of(9, 40), "Fiesta de pijamas6", "Esta noche6", Categoria.RECIBIDO);
	private Correo correo7 = new Correo("francisco7_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2020, 10, 3), LocalTime.of(9, 45), "Fiesta de pijamas7", "Esta noche7", Categoria.PAPELERA);
	private Correo correo8 = new Correo("francisco8_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2015, 10, 3), LocalTime.of(9, 40), "Fiesta de pijamas8", "Esta noche8", Categoria.RECIBIDO);
	private Correo correo9 = new Correo("francisco8_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2015, 10, 3), LocalTime.of(9, 40), "Fiesta de pijamas9", "Esta noche9", Categoria.RECIBIDO);
	private Correo correo10 = new Correo("francisco8_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2010, 5, 3), LocalTime.of(9, 40), "Fiesta de pijamas10", "Esta noche10", Categoria.RECIBIDO);
	private Correo correo11 = new Correo("francisco8_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2011, 4, 3), LocalTime.of(9, 40), "Fiesta de pijamas11", "Esta noche11", Categoria.ENVIADO);
	private Correo correo12 = new Correo("francisco8_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2012, 3, 3), LocalTime.of(9, 40), "Fiesta de pijamas12", "Esta noche12", Categoria.RECIBIDO);
	private Correo correo13 = new Correo("francisco8_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2013, 10, 3), LocalTime.of(9, 40), "Fiesta de pijamas13", "Esta noche13", Categoria.PAPELERA);

	private Correo correo100 = new Correo("maria_perez88@gmail.org.com", "julio_garcia94@yahoo.net.es",
			LocalDate.of(2020, 12, 3), LocalTime.of(21, 07), "Caracolas", "Documental de la 2", Categoria.BORRADOR);
	private Correo correo101 = new Correo("francisco100_perez88@gmail.com", "arancha100_garcia94@yahoo.es",
			LocalDate.of(2001, 1, 1), LocalTime.of(1, 1), "Averia en la calefaccion", "Cambiado",
			Categoria.BORRADOR);
	private Correo correo102 = new Correo("francisco2_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2001, 1, 1), LocalTime.of(9, 1), "Fiesta de pijamas", "Esta noche", Categoria.BORRADOR);
	private Correo correo103 = new Correo("francisco2_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2001, 1, 1), LocalTime.of(1, 0), "Fiesta de pijamas", "Esta noche", Categoria.BORRADOR);
	private Correo correo104 = new Correo("francisco2_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2001, 1, 1), LocalTime.of(1, 1), "Fiesta de pijamas", "Esta noche", Categoria.BORRADOR);
	private Correo correo105 = new Correo("francisco2_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2020, 10, 3), LocalTime.of(9, 50), "Fiesta de pijamas", "Esta noche", Categoria.BORRADOR);
	private Correo correo106 = new Correo("francisco2_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2020, 10, 3), LocalTime.of(9, 40), "Fiesta de pijamas", "Esta noche", Categoria.BORRADOR);
	private Correo correo107 = new Correo("francisco2_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2020, 10, 3), LocalTime.of(9, 45), "Fiesta de pijamas", "Esta noche", Categoria.BORRADOR);
	private Correo correo108 = new Correo("francisco2_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2020, 10, 3), LocalTime.of(9, 40), "Fiesta de pijamas", "Esta noche", Categoria.BORRADOR);
	private Correo correo109 = new Correo("francisco2_perez88@gmail.com", "arancha2_garcia94@yahoo.es",
			LocalDate.of(2020, 10, 3), LocalTime.of(9, 45), "Fiesta de pijamas", "Esta noche", Categoria.BORRADOR);
	
	@BeforeEach
	void setUp() {
		listaCorreos.add(correo0);
		listaCorreos.add(correo1);

		listaCorreos1.add(correo0);
		listaCorreos1.add(correo1);
		listaCorreos1.add(correo2);
		listaCorreos1.add(correo3);
		listaCorreos1.add(correo4);
		listaCorreos1.add(correo5);
		listaCorreos1.add(correo6);
		listaCorreos1.add(correo7);
		listaCorreos1.add(correo8);
		
		listaCorreos2.add(correo0);
		listaCorreos2.add(correo1);
		listaCorreos2.add(correo2);
		listaCorreos2.add(correo3);
		listaCorreos2.add(correo4);
		listaCorreos2.add(correo5);
		listaCorreos2.add(correo6);
		listaCorreos2.add(correo7);
		listaCorreos2.add(correo8);
		listaCorreos2.add(correo9);
		listaCorreos2.add(correo10);
		listaCorreos2.add(correo11);
		listaCorreos2.add(correo12);
		listaCorreos2.add(correo13);

		gestor = new GestorCorreo();
		gestorLista = new GestorCorreo(listaCorreos);
		gestorLista1 = new GestorCorreo(listaCorreos1);
		gestorLista2 = new GestorCorreo(listaCorreos2);

		listaCorreosComparable.add(correo0);
		listaCorreosComparable.add(correo1);
		
		listaCorreosComparable1.add(correo0);

		listaCorreosComparable2.add(correo0);
		listaCorreosComparable2.add(correo1);
		listaCorreosComparable2.add(correo100);
		
		listaCorreosComparable3.add(correo0);
		listaCorreosComparable3.add(correo102);
		
		listaCorreosComparable4.add(correo103);
		listaCorreosComparable4.add(correo0);
		
		listaCorreosComparable5.add(correo0);
		listaCorreosComparable5.add(correo104);
		
		listaCorreosComparable6.add(correo1);
		
		listaCorreosComparable7.add(correo105);
		listaCorreosComparable7.add(correo1);
		
		listaCorreosComparable8.add(correo1);
		listaCorreosComparable8.add(correo106);
		
		listaCorreosComparable9.add(correo1);
		listaCorreosComparable9.add(correo107);
		
		listaCorreosComparable10.add(correo0);
		listaCorreosComparable10.add(correo108);
		listaCorreosComparable10.add(correo1);
		
		listaCorreosComparable11.add(correo0);
		listaCorreosComparable11.add(correo1);
		listaCorreosComparable11.add(correo109);
		
		listaCorreosComparable12.add(correo0);
		listaCorreosComparable12.add(correo1);
		
		listaCorreosComparable13.add(correo2);
		listaCorreosComparable13.add(correo6);
		listaCorreosComparable13.add(correo1);
		listaCorreosComparable13.add(correo3);
		listaCorreosComparable13.add(correo4);
		listaCorreosComparable13.add(correo7);
		
		listaCorreosComparable14.add(correo11);
		listaCorreosComparable14.add(correo2);
		listaCorreosComparable14.add(correo4);
		
		listaCorreosComparable15.add(correo2);
		listaCorreosComparable15.add(correo4);
	}

	@AfterEach
	void clear() {
		listaCorreos.clear();
		listaCorreos1.clear();
		listaCorreos2.clear();

		listaCorreosComparable.clear();
		listaCorreosComparable1.clear();
		listaCorreosComparable2.clear();
		listaCorreosComparable3.clear();
		listaCorreosComparable4.clear();
		listaCorreosComparable5.clear();
		listaCorreosComparable6.clear();
		listaCorreosComparable7.clear();
		listaCorreosComparable8.clear();
		listaCorreosComparable9.clear();
		listaCorreosComparable10.clear();
		listaCorreosComparable11.clear();
		listaCorreosComparable12.clear();
		listaCorreosComparable13.clear();
		listaCorreosComparable14.clear();
		listaCorreosComparable15.clear();

	}

	
	@Test
	@Tag("BlackBox")
	void constructorGestorCorreoDefectoTest() {
		assertNotNull(gestor);
		ArrayList<Correo> listaCorreosInicial = gestor.getListaCorreos();
		assertNotNull(listaCorreosInicial);
		assertTrue(listaCorreosInicial.isEmpty());
	}

	@Test
	@Tag("BlackBox")
	void constructorGestorCorreoListaTest() {
		assertNotNull(gestorLista);
		ArrayList<Correo> listaCorreosInicial = gestorLista.getListaCorreos();
		assertNotNull(listaCorreosInicial);
		assertEquals(listaCorreosInicial.size(), 2);
		assertSame(listaCorreosInicial.get(0), correo0);
		assertSame(listaCorreosInicial.get(1), correo1);
		assertTrue(equalsListas(listaCorreosInicial, listaCorreosComparable));
	}

	@Test
	@Tag("BlackBox")
	void constructorGestorCorreoListaNoValidoTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			GestorCorreo gestor = new GestorCorreo(null);
		});
	}

	@Test
	@Tag("BlackBox")
	void añadirCorreoAGestorVacioTest() {
		gestor.añadirCorreo(correo0);
		ArrayList<Correo> listaCorreosInicial = gestor.getListaCorreos();
		assertNotNull(listaCorreosInicial);
		assertEquals(listaCorreosInicial.size(), 1);
		assertSame(listaCorreosInicial.get(0), correo0);
		assertTrue(equalsListas(listaCorreosInicial, listaCorreosComparable1));
	}

	@Test
	@Tag("BlackBox")
	void añadirCorreoAGestorConListaCortaTest() {
		gestorLista.añadirCorreo(correo100);
		ArrayList<Correo> listaCorreosInicial = gestorLista.getListaCorreos();
		assertSame(listaCorreosInicial.get(2), correo100);
		assertEquals(listaCorreosInicial.size(), 3);
		assertTrue(equalsListas(listaCorreosInicial, listaCorreosComparable2));
		
	}

	@Test
	@Tag("BlackBox")
	void añadirCorreoAGestorVacioNoValidoTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.añadirCorreo(null);
		});
	}

	@Test
	@Tag("BlackBox")
	void añadirCorreoAGestorNoValidoRepetidoTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestorLista.añadirCorreo(correo101);
		});
	}

	@Test
	@Tag("BlackBox")
	void getNumeroCorreosTest() {
		assertEquals(gestor.getNumeroCorreos(), 0);
	}

	@Test
	@Tag("BlackBox")
	void getNumeroCorreosListaTest() {
		assertEquals(gestorLista.getNumeroCorreos(), 2);
	}

	@Test
	@Tag("BlackBox")
	void getCorreosMasAntiguosVacioTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosMasAntiguos();
		});
	}

	@Test
	@Tag("BlackBox")
	void getCorreosMasAntiguosTest() {
		ArrayList<Correo> correosAntiguos = gestorLista.getCorreosMasAntiguos();
		assertEquals(correosAntiguos.size(), 1);
		assertSame(correosAntiguos.get(0), correo0);
		assertEquals(correosAntiguos.get(0).getAsunto(), "Averia en la calefaccion");
	}

	@Test
	@Tag("BlackBox")
	void getCorreosMasAntiguosAgregadoUnCorreoMasRecienteTest() {
		gestorLista.añadirCorreo(correo102);
		ArrayList<Correo> correosAntiguos = gestorLista.getCorreosMasAntiguos();
		assertEquals(correosAntiguos.size(), 2);
		assertSame(correosAntiguos.get(0), correo0);
		assertSame(correosAntiguos.get(1), correo102);
		assertTrue(equalsListas(correosAntiguos, listaCorreosComparable3));
		
	}

	@Test
	@Tag("BlackBox")
	void getCorreosMasAntiguosAgregadoUnCorreoMasAntiguoTest() {
		gestorLista.añadirCorreo(correo103);
		ArrayList<Correo> correosAntiguos = gestorLista.getCorreosMasAntiguos();
		assertEquals(correosAntiguos.size(), 2);
		assertSame(correosAntiguos.get(0), correo103);
		assertSame(correosAntiguos.get(1), correo0);
		assertArrayEquals(correosAntiguos.toArray(), listaCorreosComparable4.toArray());

	}

	@Test
	@Tag("BlackBox")
	void getCorreosMasAntiguosAgregadoUnCorreoMismaAntiguedadTest() {
		gestorLista.añadirCorreo(correo104);
		ArrayList<Correo> correosAntiguos = gestorLista.getCorreosMasAntiguos();
		assertEquals(correosAntiguos.size(), 2);
		assertSame(correosAntiguos.get(0), correo0);
		assertSame(correosAntiguos.get(1), correo104);
		assertTrue(equalsListas(correosAntiguos, listaCorreosComparable5));
	}

	@Test 
	@Tag("BlackBox")
	void getCorreosMasRecientesNoValidoTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosMasRecientes();
		});
	}

	@Test
	@Tag("BlackBox")
	void getCorreosMasRecientesTest() {
		ArrayList<Correo> correosRecientes = gestorLista.getCorreosMasRecientes();
		assertEquals(correosRecientes.size(), 1);
		assertSame(correosRecientes.get(0), correo1);
		assertTrue(equalsListas(correosRecientes, listaCorreosComparable6));
	}

	@Test
	@Tag("BlackBox")
	void getCorreosMasRecientesAgregadoUnCorreoMasRecienteTest() {
		gestorLista.añadirCorreo(correo105);
		ArrayList<Correo> correosRecientes = gestorLista.getCorreosMasRecientes();
		assertEquals(correosRecientes.size(), 2);
		assertSame(correosRecientes.get(0), correo105);
		assertSame(correosRecientes.get(1), correo1);
		assertTrue(equalsListas(correosRecientes, listaCorreosComparable7));
	}

	@Test
	@Tag("BlackBox")
	void getCorreosMasRecientesAgregadoUnCorreoMasAntiguoTest() {
		gestorLista.añadirCorreo(correo106);
		ArrayList<Correo> correosRecientes = gestorLista.getCorreosMasRecientes();
		assertEquals(correosRecientes.size(), 2);
		assertSame(correosRecientes.get(0), correo1);
		assertSame(correosRecientes.get(1), correo106);
		assertTrue(equalsListas(correosRecientes, listaCorreosComparable8));
	}

	@Test
	@Tag("BlackBox")
	void getCorreosMasRecientesAgregadoUnCorreoMismaAntiguedadTest() {
		gestorLista.añadirCorreo(correo107);
		ArrayList<Correo> correosRecientes = gestorLista.getCorreosMasRecientes();
		assertEquals(correosRecientes.size(), 2);
		assertSame(correosRecientes.get(0), correo107);
		assertSame(correosRecientes.get(1), correo1);
		assertTrue(equalsListas(correosRecientes, listaCorreosComparable9));
	}

	@Test
	@Tag("BlackBox")
	void getListaOrdenadaCorreosNoValidoTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getListaOrdenadaCorreos();
		});
	}

	@Test
	@Tag("BlackBox")
	void getListaOrdenadaCorreosAgregadoUnCorreoNiRecienteNiAntiguoTest() {
		gestorLista.añadirCorreo(correo108);
		ArrayList<Correo> correosOrdenCronologico = gestorLista.getListaOrdenadaCorreos();
		assertEquals(correosOrdenCronologico.size(), 3);
		assertSame(correosOrdenCronologico.get(0), correo0);
		assertSame(correosOrdenCronologico.get(1), correo108);
		assertSame(correosOrdenCronologico.get(2), correo1);
		assertTrue(equalsListas(correosOrdenCronologico, listaCorreosComparable10));
	}
	
	@Test
	@Tag("BlackBox")
	void getListaOrdenadaCorreosAgregadoUnCorreoRecienteTest() {
		gestorLista.añadirCorreo(correo109);
		ArrayList<Correo> correosOrdenCronologico = gestorLista.getListaOrdenadaCorreos();
		assertEquals(correosOrdenCronologico.size(), 3);
		assertSame(correosOrdenCronologico.get(0), correo0);
		assertSame(correosOrdenCronologico.get(1), correo1);
		assertSame(correosOrdenCronologico.get(2), correo109);
		assertTrue(equalsListas(correosOrdenCronologico, listaCorreosComparable11));
	}

	@Test
	@Tag("BlackBox")
	void getListaOrdenadaCorreosPorCategoriaNoValidoTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getListaOrdenadaPorCategoria();
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getListaOrdenadaCorreosPorCategoriaTest() {
		ArrayList<Correo> correosPorCategoria = gestorLista.getListaOrdenadaPorCategoria();
		assertSame(correosPorCategoria.get(0), correo0);
		assertSame(correosPorCategoria.get(1), correo1);
		assertTrue(equalsListas(correosPorCategoria, listaCorreosComparable12));
	}

	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFechaTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaFecha(LocalDate.of(2011, 1, 1));
		});
	}

	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFecha1Test() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaFecha(null);
		});
	}

	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFecha2Test() {
		assertThrows(IllegalStateException.class, () -> {
			gestorLista1.getCorreosDeUnaFecha(LocalDate.of(2011, 1, 1));
		});
	}

	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFecha3Test() {
		ArrayList<Correo> correosDeUnaFecha = gestorLista1.getCorreosDeUnaFecha(LocalDate.of(2020, 10, 3));
		assertEquals(correosDeUnaFecha.size(), 6);
		assertSame(correosDeUnaFecha.get(0), correo2);
		assertSame(correosDeUnaFecha.get(1), correo6);
		assertSame(correosDeUnaFecha.get(2), correo1);
		assertSame(correosDeUnaFecha.get(3), correo3);
		assertSame(correosDeUnaFecha.get(4), correo4);
		assertSame(correosDeUnaFecha.get(5), correo7);
		assertTrue(equalsListas(correosDeUnaFecha, listaCorreosComparable13));
	}

	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasNoValidoTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.getCorreosEntreDosFechas(LocalDate.of(2021, 1, 1), LocalDate.of(2020, 1, 1));
		});
	}

	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasNoValidoVacioPrimerParametroTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechas(null, LocalDate.of(2020, 1, 1));
		});
	}

	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasNoValidoVacioSegundoParametroTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechas(LocalDate.of(2020, 1, 1), null);
		});
	}

	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasNoValidoVaciosAmbosParametrosTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechas(null, null);
		});
	}

	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasTest() {
		ArrayList<Correo> correosEntreDosFechas = gestorLista1.getCorreosEntreDosFechas(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 12, 15));
		assertEquals(correosEntreDosFechas.size(), 6);
		assertSame(correosEntreDosFechas.get(0), correo2);
		assertSame(correosEntreDosFechas.get(1), correo6);
		assertSame(correosEntreDosFechas.get(2), correo1);
		assertSame(correosEntreDosFechas.get(3), correo3);
		assertSame(correosEntreDosFechas.get(4), correo4);
		assertSame(correosEntreDosFechas.get(5), correo7);
		assertTrue(equalsListas(correosEntreDosFechas, listaCorreosComparable13));
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasMismaFechaFinalQueInicialTest() {
		ArrayList<Correo> correosEntreDosFechas = gestorLista.getCorreosEntreDosFechas(LocalDate.of(2001, 1, 1), LocalDate.of(2001, 1, 1));
		assertEquals(correosEntreDosFechas.size(), 1);
		assertSame(correosEntreDosFechas.get(0), correo0);
		assertTrue(equalsListas(correosEntreDosFechas, listaCorreosComparable1));
	}

	@Test
	@Tag("BlackBox")
	void getCorreosCategoriaBorradorNoValidoTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaCategoria(Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosCategoriaEnviadoNoValidoTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaCategoria(Categoria.ENVIADO);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosCategoriaPapeleraNoValidoTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaCategoria(Categoria.PAPELERA);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosCategoriaRecibidoNoValidoTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaCategoria(Categoria.RECIBIDO);
		});
	}

	@Test
	@Tag("BlackBox")
	void getCorreosCategoriaBorradorTest() {
		ArrayList<Correo> correosDeUnaCategoria = gestorLista2.getCorreosDeUnaCategoria(Categoria.BORRADOR);
		assertNotNull(correosDeUnaCategoria);
		assertEquals(correosDeUnaCategoria.size(), 2);
		assertSame(correosDeUnaCategoria.get(0),correo0);
		assertSame(correosDeUnaCategoria.get(1),correo1);
		assertTrue(equalsListas(correosDeUnaCategoria, listaCorreosComparable));
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosCategoriaEnviadoTest() {
		ArrayList<Correo> correosDeUnaCategoria = gestorLista2.getCorreosDeUnaCategoria(Categoria.ENVIADO);
		assertNotNull(correosDeUnaCategoria);
		assertEquals(correosDeUnaCategoria.size(), 3);
		assertSame(correosDeUnaCategoria.get(0),correo11);
		assertSame(correosDeUnaCategoria.get(1),correo2);
		assertSame(correosDeUnaCategoria.get(2),correo4);
		assertTrue(equalsListas(correosDeUnaCategoria, listaCorreosComparable14));
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasDeUnaCategoriaNoValidoNoHayCorreosTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(2011,4,3), LocalDate.of(2020, 10, 3), Categoria.RECIBIDO);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasDeUnaCategoriaNoValidoFechaInicioVacioTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestorLista2.getCorreosEntreDosFechasDeUnaCategoria(null,LocalDate.of(2020, 10, 3), Categoria.RECIBIDO);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasDeUnaCategoriaNoValidoFechaFinalVacioTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestorLista2.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(2011,4,3), null, Categoria.RECIBIDO);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasDeUnaCategoriaNoValidoCategoriaVaciaTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestorLista2.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(2011,4,3), LocalDate.of(2020, 10, 3), null);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasDeUnaCategoriaNoValidoFechaFinalMenorQueInicialTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			gestorLista2.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(2020, 10, 3), LocalDate.of(2011,4,3), Categoria.ENVIADO);
		});
	}

	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasDeUnaCategoriaTest() {
		ArrayList<Correo> correosEntreDosFechasDeUnaCategoria = 
				gestorLista2.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(2011,4,3),LocalDate.of(2020, 10, 3), Categoria.ENVIADO);
		assertNotNull(correosEntreDosFechasDeUnaCategoria);
		assertEquals(correosEntreDosFechasDeUnaCategoria.size(), 3);
		assertSame(correosEntreDosFechasDeUnaCategoria.get(0),correo11);
		assertSame(correosEntreDosFechasDeUnaCategoria.get(1),correo2);
		assertSame(correosEntreDosFechasDeUnaCategoria.get(2),correo4);
		assertTrue(equalsListas(correosEntreDosFechasDeUnaCategoria, listaCorreosComparable14));
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFechaDeUnaCategoriaNoValidoNoHayCorreosTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaFechaDeUnaCategoria(LocalDate.of(2011,4,3), Categoria.RECIBIDO);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFechaDeUnaCategoriaNoValidoFechaVacioTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestorLista1.getCorreosDeUnaFechaDeUnaCategoria(null, Categoria.RECIBIDO);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFechaDeUnaCategoriaNoValidoFechaCategoriaVaciaTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestorLista2.getCorreosDeUnaFechaDeUnaCategoria(LocalDate.of(2011,4,3), null);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFechaDeUnaCategoriaTest() {
		ArrayList<Correo> correosDeUnaFechaDeUnaCategoria = 
				gestorLista2.getCorreosDeUnaFechaDeUnaCategoria(LocalDate.of(2020, 10, 3), Categoria.ENVIADO);
		assertNotNull(correosDeUnaFechaDeUnaCategoria);
		assertEquals(correosDeUnaFechaDeUnaCategoria.size(), 2);
		assertSame(correosDeUnaFechaDeUnaCategoria.get(0),correo2);
		assertSame(correosDeUnaFechaDeUnaCategoria.get(1),correo4);
		assertTrue(equalsListas(correosDeUnaFechaDeUnaCategoria, listaCorreosComparable15));
	}
	
	@Test
	@Tag("WhiteBox")
	void constructorGestorCorreoListaNoValidoHayCorreosRepetidosTest() {
		ArrayList<Correo> lisCorreos = new ArrayList<>();
		lisCorreos.add(correo0);
		lisCorreos.add(correo0);
		assertThrows(IllegalStateException.class, () -> {
			GestorCorreo gestor = new GestorCorreo(lisCorreos);
		});
	}

	@Test
	@Tag("WhiteBox")
	void getCorreosDeUnaFechaPosteriorTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaFecha(LocalDate.of(3000, 1, 1));
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosEntreDosFechasAlgunaFechaEsPosteriorALaActualTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechas(LocalDate.of(2020, 1, 1), LocalDate.of(3000, 1, 1));
		});
	}
	

	@Test
	@Tag("WhiteBox")
	void getCorreosEntreDosFechasAlgunaFechaEsPosteriorALaActual2Test() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechas(LocalDate.of(3000, 1, 1), LocalDate.of(3000, 1, 1));
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosEntreDosFechasNoHayCorreos2Test() {
		assertThrows(IllegalStateException.class, () -> {
			gestorLista2.getCorreosEntreDosFechas(LocalDate.of(1000, 1, 1), LocalDate.of(1001, 1, 1));
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosCategoriaVaciaTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaCategoria(null);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosCategoriaNoHayCorreosTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestorLista.getCorreosDeUnaCategoria(Categoria.ENVIADO);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosEntreDosFechasNoValidoFechaPosteriorALaActualTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(2020, 1, 1), LocalDate.of(3000, 1, 1), Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosEntreDosFechasNoValidoFechaPosteriorALaActual2Test() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(3000, 1, 1), LocalDate.of(3000, 1, 1), Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosDeUnaFechaDeUnaCategoriaNoValidoFechaPosteriorALaActualTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaFechaDeUnaCategoria(LocalDate.of(3000,4,3), Categoria.RECIBIDO);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosDeUnaFechaDeUnaCategoriaNoHayCorreosTest() {
		assertThrows(IllegalStateException.class, () -> {
			gestorLista2.getCorreosDeUnaFechaDeUnaCategoria(LocalDate.of(1000,4,3), Categoria.RECIBIDO);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosMasAntiguosCogerTodosTest() {
		ArrayList<Correo> correos = new ArrayList<>();
		correos.add(correo0);
		GestorCorreo gc = new GestorCorreo(correos);
		ArrayList<Correo> correosAntiguos = gc.getCorreosMasAntiguos();
		assertEquals(correosAntiguos.size(), 1);
		assertSame(correosAntiguos.get(0), correo0);
		assertEquals(correosAntiguos.get(0).getAsunto(), "Averia en la calefaccion");
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosMasRecientesCogerTodosTest() {
		ArrayList<Correo> correos = new ArrayList<>();
		correos.add(correo0);
		GestorCorreo gc = new GestorCorreo(correos);
		ArrayList<Correo> correosRecientes = gc.getCorreosMasRecientes();
		assertEquals(correosRecientes.size(), 1);
		assertSame(correosRecientes.get(0), correo0);
		assertEquals(correosRecientes.get(0).getAsunto(), "Averia en la calefaccion");
	}
	
	private boolean equalsListas(ArrayList<Correo> listaCorreos, ArrayList<Correo> listaCorreosEsperados) {
		if(listaCorreos.size() == listaCorreosEsperados.size()) {
			for(int i = 0; i < listaCorreos.size(); i++) if (listaCorreos.get(i).equals(listaCorreosEsperados)) return false;
			return true;
		}else {
			return false;
		}
	}
}
