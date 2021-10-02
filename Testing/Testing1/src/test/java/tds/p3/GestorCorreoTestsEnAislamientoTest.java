package tds.p3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


/**
 * 
 * @author edgdiez Edgar Diez Alonso
 * 
 */
@Tag("Isolation")
@ExtendWith(EasyMockExtension.class)
public class GestorCorreoTestsEnAislamientoTest {

	@TestSubject
	GestorCorreo gestor;
	
	
	@Mock
	Correo correo1;
	
	@Mock
	Correo correo2;
	
	@Mock
	Correo correo3;
	
	ArrayList<Correo> listaCorreos = new ArrayList<>();
	
	@BeforeEach
	void setup() {
		listaCorreos.add(correo1);
		listaCorreos.add(correo2);
	}
	
	@AfterEach
	void clear() {
		listaCorreos.clear();
	}
	
	@Test
	@Tag("BlackBox")
	void constructorGestorCorreoListaTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		assertNotNull(gestor);
		ArrayList<Correo> listaCorreosInicial = gestor.getListaCorreos();
		assertNotNull(listaCorreosInicial);
		assertEquals(listaCorreosInicial.size(), 2);
		EasyMock.verify(correo1);
		EasyMock.verify(correo2);
		
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
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo1.equals(correo3)).andReturn(false);
		EasyMock.expect(correo2.equals(correo3)).andReturn(false);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		EasyMock.replay(correo3);
		gestor = new GestorCorreo(listaCorreos);
		gestor.añadirCorreo(correo3);
		ArrayList<Correo> listaCorreosInicial = gestor.getListaCorreos();
		assertNotNull(listaCorreosInicial);
		assertEquals(listaCorreosInicial.size(), 3);
		EasyMock.verify(correo1);
		EasyMock.verify(correo2);
		EasyMock.verify(correo3);
	}
	
	@Test
	@Tag("BlackBox")
	void añadirCorreoAGestorVacioNoValidoTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.añadirCorreo(null);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void añadirCorreoAGestorNoValidoRepetidoTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false).times(2);
		EasyMock.expect(correo2.equals(correo2)).andReturn(true);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		assertThrows(IllegalStateException.class, () -> {
			gestor.añadirCorreo(correo2);
			EasyMock.verify(correo1);
			EasyMock.verify(correo2);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getNumeroCorreosTest() {
		gestor = new GestorCorreo();
		assertEquals(gestor.getNumeroCorreos(), 0);
	}
	
	@Test
	@Tag("BlackBox")
	void getNumeroCorreosListaTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		assertEquals(gestor.getNumeroCorreos(), 2);
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosMasAntiguosTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getFecha()).andReturn(LocalDate.of(2019, 12, 31)).times(2);
		EasyMock.expect(correo2.getFecha()).andReturn(LocalDate.of(2020, 1, 1)).times(1);		
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		ArrayList<Correo> correosAntiguos = gestor.getCorreosMasAntiguos();
		assertNotNull(correosAntiguos);
		assertEquals(correosAntiguos.size(), 1);
		EasyMock.verify(correo1);
		EasyMock.verify(correo2);
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosMasAntiguosVacioTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosMasAntiguos();
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosMasRecientesTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getFecha()).andReturn(LocalDate.of(2019, 12, 1)).times(1);
		EasyMock.expect(correo2.getFecha()).andReturn(LocalDate.of(2020, 1, 1)).times(2);		
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		ArrayList<Correo> correosRecientes = gestor.getCorreosMasRecientes();
		assertNotNull(correosRecientes);
		assertEquals(correosRecientes.size(), 1);
		EasyMock.verify(correo1);
		EasyMock.verify(correo2);
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosMasRecientesNoValidoTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosMasRecientes();
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getListaOrdenadaCorreosNoValidoTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getListaOrdenadaCorreos();
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getListaOrdenadaCorreosTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		ArrayList<Correo> correosOrdenCronologico = gestor.getListaOrdenadaCorreos();
		assertNotNull(correosOrdenCronologico);
		assertEquals(correosOrdenCronologico.size(), 2);
		EasyMock.verify(correo1);
		EasyMock.verify(correo2);
	}
	
	@Test
	@Tag("BlackBox")
	void getListaOrdenadaCorreosPorCategoriaTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getCategoria()).andReturn(Categoria.ENVIADO).times(1);
		EasyMock.expect(correo2.getCategoria()).andReturn(Categoria.BORRADOR).times(1);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		ArrayList<Correo> correosPorCategoria = gestor.getListaOrdenadaPorCategoria();
		assertNotNull(correosPorCategoria);
		assertEquals(correosPorCategoria.size(),2);
		EasyMock.verify(correo1);
		EasyMock.verify(correo2);
	}
	
	@Test
	@Tag("BlackBox")
	void getListaOrdenadaCorreosPorCategoriaNoValidoTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getListaOrdenadaPorCategoria();
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFechaValidaTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getFecha()).andReturn(LocalDate.of(2011, 1, 2));
		EasyMock.expect(correo2.getFecha()).andReturn(LocalDate.of(2020, 10, 3));
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		ArrayList<Correo> correosDeUnaFecha = gestor.getCorreosDeUnaFecha(LocalDate.of(2020, 10, 3));
		assertNotNull(correosDeUnaFecha);
		assertEquals(correosDeUnaFecha.size(), 1);
		EasyMock.verify(correo1);
		EasyMock.verify(correo2);
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFechaVacioTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaFecha(null);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFechaNoValidaNoExisteFechaTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getFecha()).andReturn(LocalDate.of(2011, 1, 2));
		EasyMock.expect(correo2.getFecha()).andReturn(LocalDate.of(2011, 2, 2));
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaFecha(LocalDate.of(2011, 1, 1));
			EasyMock.verify(correo1);
			EasyMock.verify(correo2);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getFecha()).andReturn(LocalDate.of(2011, 1, 2)).times(2);
		EasyMock.expect(correo2.getFecha()).andReturn(LocalDate.of(2020, 1, 1)).times(4);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		ArrayList<Correo> correosEntreDosFechas = gestor.getCorreosEntreDosFechas(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1));
		assertNotNull(correosEntreDosFechas);
		assertEquals(correosEntreDosFechas.size(), 1);
		EasyMock.verify(correo1);
		EasyMock.verify(correo2);
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasNoValidoVacioPrimerParametroTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechas(null, LocalDate.of(2020, 1, 1));
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasNoValidoVacioSegundoParametroTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechas(LocalDate.of(2020, 1, 1), null);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasNoValidoPrimeraFechaMayorQueSegundaTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.getCorreosEntreDosFechas(LocalDate.of(2020, 1, 2), LocalDate.of(2020, 1, 1));
			EasyMock.verify(correo1);
			EasyMock.verify(correo2);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasNoValidoPrimeraUnaFechaPosteriorALaActualTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechas(LocalDate.of(2020, 1, 1), LocalDate.of(2021, 1, 1));
			EasyMock.verify(correo1);
			EasyMock.verify(correo2);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosCategoriaBorradorTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getCategoria()).andReturn(Categoria.ENVIADO).times(1);
		EasyMock.expect(correo2.getCategoria()).andReturn(Categoria.BORRADOR).times(1);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		ArrayList<Correo> correosDeUnaCategoria = gestor.getCorreosDeUnaCategoria(Categoria.BORRADOR);
		assertNotNull(correosDeUnaCategoria);
		assertEquals(correosDeUnaCategoria.size(), 1);
		EasyMock.verify(correo1);
		EasyMock.verify(correo2);
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosCategoriaNoValidoVacioTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaCategoria(null);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosCategoriaBorradorNoValidoTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaCategoria(Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasDeUnaCategoriaTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getCategoria()).andReturn(Categoria.ENVIADO).times(1);
		EasyMock.expect(correo2.getCategoria()).andReturn(Categoria.ENVIADO).times(1);
		EasyMock.expect(correo1.getFecha()).andReturn(LocalDate.of(2011, 4, 4)).times(2);
		EasyMock.expect(correo2.getFecha()).andReturn(LocalDate.of(2020, 10, 4)).times(3);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		ArrayList<Correo> correosEntreDosFechasDeUnaCategoria = 
				gestor.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(2011,4,3),LocalDate.of(2020, 10, 3), Categoria.ENVIADO);
		assertNotNull(correosEntreDosFechasDeUnaCategoria);
		assertEquals(correosEntreDosFechasDeUnaCategoria.size(), 1);
		EasyMock.verify(correo1);
		EasyMock.verify(correo2);
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasDeUnaCategoriaNoValidoFechaInicioVacioTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechasDeUnaCategoria(null,LocalDate.of(2020, 10, 3), Categoria.RECIBIDO);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasDeUnaCategoriaNoValidoFechaFinalVacioTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(2011,4,3), null, Categoria.RECIBIDO);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasDeUnaCategoriaNoValidoCategoriaVaciaTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(2011,4,3), LocalDate.of(2020, 10, 3), null);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasDeUnaCategoriaNoValidoFechaFinalMenorQueInicialTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getCategoria()).andReturn(Categoria.ENVIADO).times(1);
		EasyMock.expect(correo2.getCategoria()).andReturn(Categoria.BORRADOR).times(1);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		assertThrows(IllegalArgumentException.class, () -> {
			gestor.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(2020, 10, 3), LocalDate.of(2011,4,3), Categoria.ENVIADO);
			EasyMock.verify(correo1);
			EasyMock.verify(correo2);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosEntreDosFechasDeUnaCategoriaNoValidoNoHayCorreosTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getCategoria()).andReturn(Categoria.ENVIADO).times(1);
		EasyMock.expect(correo2.getCategoria()).andReturn(Categoria.BORRADOR).times(1);
		EasyMock.expect(correo1.getFecha()).andReturn(LocalDate.of(2011, 4, 2)).times(3);
		EasyMock.expect(correo2.getFecha()).andReturn(LocalDate.of(2020, 10, 4)).times(4);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(2011,4,3), LocalDate.of(2020, 10, 3), Categoria.RECIBIDO);
			EasyMock.verify(correo1);
			EasyMock.verify(correo2);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFechaDeUnaCategoriaTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getCategoria()).andReturn(Categoria.ENVIADO).times(1);
		EasyMock.expect(correo2.getCategoria()).andReturn(Categoria.ENVIADO).times(1);
		EasyMock.expect(correo1.getFecha()).andReturn(LocalDate.of(2020, 10, 2)).times(1);
		EasyMock.expect(correo2.getFecha()).andReturn(LocalDate.of(2020, 10, 3)).times(1);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		ArrayList<Correo> correosDeUnaFechaDeUnaCategoria = 
				gestor.getCorreosDeUnaFechaDeUnaCategoria(LocalDate.of(2020, 10, 3), Categoria.ENVIADO);
		assertNotNull(correosDeUnaFechaDeUnaCategoria);
		assertEquals(correosDeUnaFechaDeUnaCategoria.size(), 1);
		EasyMock.verify(correo1);
		EasyMock.verify(correo2);
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFechaDeUnaCategoriaNoValidoFechaVacioTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaFechaDeUnaCategoria(null, Categoria.RECIBIDO);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFechaDeUnaCategoriaNoValidoFechaCategoriaVaciaTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaFechaDeUnaCategoria(LocalDate.of(2011,4,3), null);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void getCorreosDeUnaFechaDeUnaCategoriaNoValidoNoHayCorreosTest() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getCategoria()).andReturn(Categoria.ENVIADO).times(1);
		EasyMock.expect(correo2.getCategoria()).andReturn(Categoria.ENVIADO).times(1);
		EasyMock.expect(correo1.getFecha()).andReturn(LocalDate.of(2011, 4, 4)).times(2);
		EasyMock.expect(correo2.getFecha()).andReturn(LocalDate.of(2020, 10, 4)).times(3);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaFechaDeUnaCategoria(LocalDate.of(2011,4,4), Categoria.RECIBIDO);
			EasyMock.verify(correo1);
			EasyMock.verify(correo2);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void constructorGestorCorreoListaNoValidoCorreosRepetidosTest() {
		ArrayList<Correo> listaCorreosConRepetidos = new ArrayList<>();
		EasyMock.expect(correo1.equals(correo1)).andReturn(true);
		EasyMock.replay(correo1);
		listaCorreosConRepetidos.add(correo1);
		listaCorreosConRepetidos.add(correo1);
		assertThrows(IllegalStateException.class, () -> {
			GestorCorreo gestor = new GestorCorreo(listaCorreosConRepetidos);
			EasyMock.verify(correo1);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosDeUnaFechaPosteriorALaActualTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaFecha(LocalDate.of(3000, 1, 1));
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosEntreDosFechasAlgunaPosteriorAlaActualTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechas(LocalDate.of(2020, 1, 1), LocalDate.of(3000, 1, 1));
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosEntreDosFechasAlgunaPosteriorAlaActual2Test() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechas(LocalDate.of(3000, 1, 1), LocalDate.of(4000, 1, 1));
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosEntreDosFechasAlgunaPosteriorAlaActual3Test() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechas(LocalDate.of(3000, 1, 1), LocalDate.of(3000, 1, 1));
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosEntreDosFechasVaciaTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechas(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1));
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosEntreDosFechasVacia2Test() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getFecha()).andReturn(LocalDate.of(2011, 1, 2)).times(3);
		EasyMock.expect(correo2.getFecha()).andReturn(LocalDate.of(2020, 1, 1)).times(4);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechas(LocalDate.of(2010, 1, 1), LocalDate.of(2011, 1, 1));
			EasyMock.verify(correo1);
			EasyMock.verify(correo2);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosEntreDosFechasDeUnaCategoriaAlgunaFechaPosteriorALaActualTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(3000, 1, 1), LocalDate.of(3000, 1, 1), Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosEntreDosFechasDeUnaCategoriaAlgunaFechaPosteriorALaActual2Test() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosEntreDosFechasDeUnaCategoria(LocalDate.of(2000, 1, 1), LocalDate.of(3000, 1, 1), Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosDeUnaFechaDeUnaCategoriaNoValidoFechaPosteriorALaActualTest() {
		gestor = new GestorCorreo();
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaFechaDeUnaCategoria(LocalDate.of(3000, 1, 1), Categoria.RECIBIDO);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosDeUnaFechaDeUnaCategoriaNoValidoNoHayCorreos2Test() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getCategoria()).andReturn(Categoria.ENVIADO).times(1);
		EasyMock.expect(correo2.getCategoria()).andReturn(Categoria.ENVIADO).times(1);
		EasyMock.expect(correo1.getFecha()).andReturn(LocalDate.of(2011, 4, 4)).times(2);
		EasyMock.expect(correo2.getFecha()).andReturn(LocalDate.of(2020, 10, 4)).times(3);
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		assertThrows(IllegalStateException.class, () -> {
			gestor.getCorreosDeUnaFechaDeUnaCategoria(LocalDate.of(2011,4,5), Categoria.ENVIADO);
			EasyMock.verify(correo1);
			EasyMock.verify(correo2);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosMasRecientes2Test() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getFecha()).andReturn(LocalDate.of(2020, 1, 1)).times(1);
		EasyMock.expect(correo2.getFecha()).andReturn(LocalDate.of(2020, 1, 1)).times(2);		
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		ArrayList<Correo> correosRecientes = gestor.getCorreosMasRecientes();
		assertNotNull(correosRecientes);
		assertEquals(correosRecientes.size(), 2);
		EasyMock.verify(correo1);
		EasyMock.verify(correo2);
	}
	
	@Test
	@Tag("WhiteBox")
	void getCorreosMasAntiguos2Test() {
		EasyMock.expect(correo1.equals(correo2)).andReturn(false);
		EasyMock.expect(correo2.compareTo(correo1)).andReturn(1);
		EasyMock.expect(correo1.getFecha()).andReturn(LocalDate.of(2020, 1, 1)).times(2);
		EasyMock.expect(correo2.getFecha()).andReturn(LocalDate.of(2020, 1, 1)).times(1);		
		EasyMock.replay(correo1);
		EasyMock.replay(correo2);
		gestor = new GestorCorreo(listaCorreos);
		ArrayList<Correo> correosAntiguos = gestor.getCorreosMasAntiguos();
		assertNotNull(correosAntiguos);
		assertEquals(correosAntiguos.size(), 2);
		EasyMock.verify(correo1);
		EasyMock.verify(correo2);
	}
}
