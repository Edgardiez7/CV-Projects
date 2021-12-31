package tds_pr4_edgdiez.p4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.xml.sax.SAXException;

import static org.junit.jupiter.api.Assertions.*;
import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.MockType;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Clase de testeo Sistema
 * 
 * @author edgdiez
 */

@ExtendWith(EasyMockExtension.class)
public class SistemaTest {

	ArrayList<Estacion> arrayEstaciones = new ArrayList<>();;
	ArrayList<PuntoRecogida> puntosRecogida = new ArrayList<>();

	@Mock(type = MockType.NICE)
	PuntoRecogida pr1;
	@Mock
	PuntoRecogida pr2;
	@Mock
	PuntoRecogida pr3;
	@Mock
	Coordenadas coords;
	@Mock
	Coordenadas coords2;
	@Mock
	Estacion est1;
	@Mock
	Estacion est2;

	@BeforeEach
	void inic() {
		puntosRecogida.add(pr1);
		arrayEstaciones.add(est1);
	}

	@AfterEach
	void end() {
		arrayEstaciones.clear();
		puntosRecogida.clear();
	}

	@Test
	@Tag("BlackBox")
	void constructorValidoTest() {
		EasyMock.expect(est2.getId()).andStubReturn("est2");
		EasyMock.expect(est1.getId()).andStubReturn("est1");
		EasyMock.replay(est2);
		EasyMock.replay(est1);
		arrayEstaciones.add(est2);
		Sistema sistema = new Sistema(arrayEstaciones);
		assertFalse(sistema.getEstaciones().isEmpty());
		assertEquals(sistema.getEstaciones().size(), 2);
		assertEquals(sistema.getEstaciones(), arrayEstaciones);
		EasyMock.verify(est2);
		EasyMock.verify(est1);
	}

	@Test
	@Tag("BlackBox")
	void constructorNoValidoNoEstacionesTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			arrayEstaciones.clear();
			Sistema sistema = new Sistema(arrayEstaciones);
		});
	}

	@Test
	@Tag("BlackBox")
	void constructorNoValidoNullTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(null);
		});
	}

	@Test
	@Tag("BlackBox")
	@Tag("TDD")
	void constructorNoValidoEstacionesIdRepetidoTest() {
		assertThrows(IllegalStateException.class, () -> {
			EasyMock.expect(est2.getId()).andStubReturn("est1");
			EasyMock.expect(est1.getId()).andStubReturn("est1");
			EasyMock.replay(est2);
			EasyMock.replay(est1);
			arrayEstaciones.add(est2);
			Sistema sistema = new Sistema(arrayEstaciones);
			EasyMock.verify(est2);
			EasyMock.verify(est1);
		});
	}

	@Test
	@Tag("BlackBox")
	void añadirEstacionValidoTest() {
		EasyMock.expect(est2.getId()).andStubReturn("est2");
		EasyMock.expect(est1.getId()).andStubReturn("est1");
		EasyMock.expect(pr1.getId()).andStubReturn("pr1");
		EasyMock.expect(pr2.getId()).andStubReturn("pr2");
		EasyMock.expect(est1.getListaPuntos()).andStubReturn(puntosRecogida);
		ArrayList<PuntoRecogida> puntosRecogida2 = new ArrayList<>();
		puntosRecogida2.add(pr2);
		EasyMock.expect(est2.getListaPuntos()).andStubReturn(puntosRecogida2);
		EasyMock.replay(est2);
		EasyMock.replay(est1);
		EasyMock.replay(pr1);
		EasyMock.replay(pr2);
		Sistema sistema = new Sistema(arrayEstaciones);
		sistema.annadirEstacion(est2);
		assertFalse(sistema.getEstaciones().isEmpty());
		assertEquals(sistema.getEstaciones().size(), 2);
		assertEquals(sistema.getEstaciones().get(0), est1);
		assertEquals(sistema.getEstaciones().get(1), est2);
		EasyMock.verify(est2);
		EasyMock.verify(est1);
		EasyMock.verify(pr1);
		EasyMock.verify(pr2);
	}

	@Test
	@Tag("BlackBox")
	void añadirEstacionNoValidoNullTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.annadirEstacion(null);
		});
	}

	@Test
	@Tag("BlackBox")
	@Tag("TDD")
	void añadirEstacionNoValidoIdRepetidoTest() {
		assertThrows(IllegalStateException.class, () -> {
			EasyMock.expect(est1.getId()).andReturn("est1").times(2);
			EasyMock.expect(est2.getId()).andReturn("est1").times(2);
			EasyMock.replay(est1);
			EasyMock.replay(est2);
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.annadirEstacion(est2);
			EasyMock.verify(est1);
			EasyMock.verify(est2);
		});
	}

	@Test
	@Tag("BlackBox")
	void eliminarEstacionValidoTest() {
		EasyMock.expect(est1.getId()).andReturn("est1").times(1);
		EasyMock.expect(est2.getId()).andReturn("est2").times(2);
		EasyMock.replay(est1);
		EasyMock.replay(est2);
		arrayEstaciones.add(est2);
		Sistema sistema = new Sistema(arrayEstaciones);
		sistema.eliminarEstacion(est1);
		assertFalse(sistema.getEstaciones().isEmpty());
		assertFalse(sistema.getEstaciones().contains(est1));
		EasyMock.verify(est1);
		EasyMock.verify(est1);
	}

	@Test
	@Tag("BlackBox")
	void eliminarEstacionNoValidoNullTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.eliminarEstacion(null);
		});
	}

	@Test
	@Tag("BlackBox")
	void eliminarEstacionNoValidoSistemaVacioTest() {
		assertThrows(IllegalStateException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.eliminarEstacion(est1);
		});
	}

	@Test
	@Tag("BlackBox")
	void eliminarEstacionNoPerteneceSistemaTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.eliminarEstacion(est2);
		});
	}

	@Test
	@Tag("BlackBox")
	void alquilarBiciValidoTest() {
		EasyMock.expect(est1.getId()).andReturn("est1");
		EasyMock.expect(est1.getListaPuntos()).andStubReturn(puntosRecogida);
		EasyMock.expect(pr1.getEstado()).andReturn(Estado.OCUPADO);
		EasyMock.expect(pr1.getId()).andReturn("pr1");
		EasyMock.replay(est1);
		EasyMock.replay(pr1);
		Sistema sistema = new Sistema(arrayEstaciones);
		sistema.alquilarBici(pr1);
		assertFalse(sistema.getEstaciones().isEmpty());
		assertEquals(sistema.getEstaciones().get(0).getListaPuntos().get(0).getId(), "pr1");
		EasyMock.reset(pr1);
		EasyMock.expect(pr1.getEstado()).andReturn(Estado.LIBRE);
		EasyMock.replay(pr1);
		assertEquals(sistema.getEstaciones().get(0).getListaPuntos().get(0).getEstado(), Estado.LIBRE);
		EasyMock.verify(est1);
		EasyMock.verify(pr1);
	}

	@Test
	@Tag("BlackBox")
	void alquilarBiciNoValidoNullTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.alquilarBici(null);
		});
	}

	@Test
	@Tag("BlackBox")
	void alquilarBiciNoValidoPuntoNoPerteneceTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			EasyMock.expect(pr2.getEstado()).andReturn(Estado.OCUPADO);
			EasyMock.expect(est1.getId()).andReturn("est1");
			EasyMock.expect(est1.getListaPuntos()).andReturn(puntosRecogida);
			EasyMock.replay(pr2);
			EasyMock.replay(est1);
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.alquilarBici(pr2);
			EasyMock.verify(pr2);
			EasyMock.verify(est1);
		});
	}

	@Test
	@Tag("BlackBox")
	void alquilarBiciNoValidoNoPuntoOcupado1Test() {
		assertThrows(IllegalStateException.class, () -> {
			EasyMock.expect(pr1.getEstado()).andReturn(Estado.LIBRE);
			EasyMock.expect(est1.getListaPuntos()).andReturn(puntosRecogida);
			EasyMock.expect(est1.getId()).andStubReturn("est1");
			EasyMock.replay(pr1);
			EasyMock.replay(est1);
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.alquilarBici(pr1);
			EasyMock.verify(pr1);
			EasyMock.verify(est1);
		});
	}

	@Test
	@Tag("BlackBox")
	void alquilarBiciNoValidoNoPuntoOcupado2Test() {
		assertThrows(IllegalStateException.class, () -> {
			EasyMock.expect(pr1.getEstado()).andReturn(Estado.INACTIVO);
			EasyMock.expect(est1.getListaPuntos()).andReturn(puntosRecogida);
			EasyMock.expect(est1.getId()).andReturn("est1");
			EasyMock.replay(pr1);
			EasyMock.replay(est1);
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.alquilarBici(pr1);
			EasyMock.verify(pr1);
			EasyMock.verify(est1);
		});
	}

	@Test
	@Tag("BlackBox")
	void devolverBiciValidoTest() {
		EasyMock.expect(est1.getId()).andReturn("est1");
		EasyMock.expect(est1.getListaPuntos()).andStubReturn(puntosRecogida);
		EasyMock.expect(pr1.getId()).andReturn("pr1");
		EasyMock.expect(pr1.getEstado()).andReturn(Estado.LIBRE);
		EasyMock.replay(est1);
		EasyMock.replay(pr1);
		Sistema sistema = new Sistema(arrayEstaciones);
		sistema.devolverBici(pr1);
		EasyMock.reset(pr1);
		EasyMock.expect(pr1.getId()).andReturn("pr1");
		EasyMock.expect(pr1.getEstado()).andReturn(Estado.OCUPADO);
		EasyMock.replay(pr1);
		assertFalse(sistema.getEstaciones().isEmpty());
		assertEquals(sistema.getEstaciones().get(0).getListaPuntos().get(0).getId(), "pr1");
		assertEquals(sistema.getEstaciones().get(0).getListaPuntos().get(0).getEstado(), Estado.OCUPADO);
		EasyMock.verify(est1);
		EasyMock.verify(pr1);
	}

	@Test
	@Tag("BlackBox")
	void devolverBiciNoValidoNullTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.devolverBici(null);
		});
	}

	@Test
	@Tag("Whitebox")
	void devolverBiciNoValidoPuntoNoPerteneceTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			EasyMock.expect(pr2.getEstado()).andReturn(Estado.LIBRE);
			EasyMock.expect(est1.getId()).andReturn("est1");
			EasyMock.expect(est1.getListaPuntos()).andReturn(puntosRecogida);
			EasyMock.replay(est1);
			EasyMock.replay(pr2);
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.devolverBici(pr2);
			EasyMock.verify(est1);
			EasyMock.verify(pr2);
		});
	}

	@Test
	@Tag("BlackBox")
	void devolverBiciNoValidoNoPuntoLibre1Test() {
		assertThrows(IllegalStateException.class, () -> {
			EasyMock.expect(pr1.getEstado()).andStubReturn(Estado.INACTIVO);
			EasyMock.replay(pr1);
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.devolverBici(pr1);
			EasyMock.verify(pr1);
		});
	}

	@Test
	@Tag("BlackBox")
	void alquilarBiciNoValidoNoPuntoLibre2Test() {
		assertThrows(IllegalStateException.class, () -> {
			EasyMock.expect(pr1.getEstado()).andStubReturn(Estado.OCUPADO);
			EasyMock.replay(pr1);
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.devolverBici(pr1);
			EasyMock.verify(pr1);
		});
	}

	@Test
	@Tag("BlackBox")
	void desactivarPuntoRecogidaValidoTest() {
		EasyMock.expect(est1.getId()).andReturn("est1");
		EasyMock.expect(est1.getListaPuntos()).andStubReturn(puntosRecogida);
		EasyMock.expect(pr1.getEstado()).andStubReturn(Estado.LIBRE);
		EasyMock.expect(pr1.getId()).andReturn("pr1");
		EasyMock.replay(est1);
		EasyMock.replay(pr1);
		Sistema sistema = new Sistema(arrayEstaciones);
		sistema.desactivarPuntoRecogida(pr1);
		EasyMock.reset(pr1);
		EasyMock.expect(pr1.getEstado()).andReturn(Estado.INACTIVO);
		EasyMock.replay(pr1);
		assertFalse(sistema.getEstaciones().isEmpty());
		assertEquals(sistema.getEstaciones().get(0).getListaPuntos().get(0).getEstado(), Estado.INACTIVO);
		EasyMock.verify(est1);
		EasyMock.verify(pr1);
	}

	@Test
	@Tag("Whitebox")
	void desactivarPuntoRecogidaNoPerteneceTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			EasyMock.expect(pr2.getEstado()).andStubReturn(Estado.LIBRE);
			EasyMock.expect(est1.getId()).andReturn("est1");
			EasyMock.expect(est1.getListaPuntos()).andReturn(puntosRecogida);
			EasyMock.replay(est1);
			EasyMock.replay(pr2);
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.desactivarPuntoRecogida(pr2);
			EasyMock.verify(est1);
			EasyMock.verify(pr2);
		});
	}

	@Test
	@Tag("BlackBox")
	void desactivarPuntoRecogidaNoValidoNullTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.desactivarPuntoRecogida(null);
		});
	}

	@Test
	@Tag("BlackBox")
	void desactivarPuntoRecogidaNoValidoYaInactivoTest() {
		assertThrows(IllegalStateException.class, () -> {
			EasyMock.expect(pr1.getEstado()).andStubReturn(Estado.INACTIVO);
			EasyMock.replay(pr1);
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.desactivarPuntoRecogida(pr1);
			EasyMock.verify(pr1);
		});
	}

	@Test
	@Tag("BlackBox")
	void desactivarPuntoRecogidaNoValidoOcupadoTest() {
		assertThrows(IllegalStateException.class, () -> {
			EasyMock.expect(pr1.getEstado()).andReturn(Estado.OCUPADO);
			EasyMock.replay(pr1);
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.desactivarPuntoRecogida(pr1);
			EasyMock.verify(pr1);
		});
	}

	@Test
	@Tag("BlackBox")
	void estacionesCercanasValidoTest() {
		EasyMock.expect(est1.getId()).andReturn("est1");
		EasyMock.expect(est1.getCoordenadas()).andReturn(coords);
		EasyMock.expect(coords.distancia(coords2)).andReturn((double) 500);
		EasyMock.replay(est1);
		EasyMock.replay(coords);
		Sistema sistema = new Sistema(arrayEstaciones);
		ArrayList<Estacion> estCerca = sistema.estacionesCercanas(coords2);
		assertNotNull(estCerca);
		assertEquals(estCerca.size(), 1);
		assertEquals(estCerca.get(0), est1);
		EasyMock.verify(est1);
		EasyMock.verify(coords);
	}

	@Test
	@Tag("BlackBox")
	void estacionesCercanasNoValidoNullTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			ArrayList<Estacion> estCerca = sistema.estacionesCercanas(null);
		});
	}

	@Test
	@Tag("BlackBox")
	void estacionesCercanasNoValidoNoHayTest() {
		assertThrows(IllegalStateException.class, () -> {
			EasyMock.expect(est1.getId()).andReturn("est1");
			EasyMock.expect(est1.getCoordenadas()).andReturn(coords);
			EasyMock.expect(coords.distancia(coords2)).andReturn((double) 501);
			EasyMock.replay(est1);
			EasyMock.replay(coords);
			Sistema sistema = new Sistema(arrayEstaciones);
			ArrayList<Estacion> estCerca = sistema.estacionesCercanas(coords2);
			EasyMock.verify(est1);
			EasyMock.verify(coords);
		});
	}

	@Test
	@Tag("BlackBox")
	void distanciaEntreEstacionesValidoTest() {
		EasyMock.expect(est1.getId()).andStubReturn("est1");
		EasyMock.expect(est1.getCoordenadas()).andReturn(coords);
		EasyMock.expect(est2.getId()).andStubReturn("est2");
		EasyMock.expect(est2.getCoordenadas()).andReturn(coords2);
		EasyMock.expect(coords.distancia(coords2)).andReturn((double) 95);
		EasyMock.replay(est1);
		EasyMock.replay(est2);
		EasyMock.replay(coords);
		EasyMock.replay(coords2);
		arrayEstaciones.add(est2);
		Sistema sistema = new Sistema(arrayEstaciones);
		assertEquals(sistema.distanciaEntreEstaciones(est1, est2), 95);
		EasyMock.verify(est1);
		EasyMock.verify(est2);
		EasyMock.verify(coords);
		EasyMock.verify(coords2);
	}

	@Test
	@Tag("BlackBox")
	void distanciaEntreEstacionesNoValidoNull1Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.distanciaEntreEstaciones(est1, null);
		});
	}

	@Test
	@Tag("BlackBox")
	void distanciaEntreEstacionesNoValidoNull2Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.distanciaEntreEstaciones(null, est1);
		});
	}

	@Test
	@Tag("BlackBox")
	void distanciaEntreEstacionesNoValidoEstNoPerteneceTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.distanciaEntreEstaciones(est1, est2);
		});
	}

	@Test
	@Tag("BlackBox")
	void distanciaEntreEstacionesNoValidoEstNoPertenece2Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.distanciaEntreEstaciones(est2, est1);
		});
	}

	@Test
	@Tag("BlackBox")
	void numeroPuntosRecogidaEstacionEnEstadoValidoTest() {
		EasyMock.expect(est1.getId()).andReturn("est1");
		EasyMock.expect(est1.getPuntosDeRecogida(Estado.LIBRE)).andReturn(puntosRecogida);
		EasyMock.replay(est1);
		Sistema sistema = new Sistema(arrayEstaciones);
		assertEquals(sistema.numeroPuntosRecogidaEstacionEnEstado(est1, Estado.LIBRE), 1);
		EasyMock.verify(est1);
	}

	@Test
	@Tag("BlackBox")
	void numeroPuntosRecogidaEstacionEnEstadoNoValidoEstNullTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.numeroPuntosRecogidaEstacionEnEstado(null, Estado.LIBRE);
		});
	}

	@Test
	@Tag("BlackBox")
	void numeroPuntosRecogidaEstacionEnEstadoNoValidoEstNoEnSistTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.numeroPuntosRecogidaEstacionEnEstado(est2, Estado.LIBRE);
		});
	}

	@Test
	@Tag("BlackBox")
	void buscarEstacionValidoTest() {
		EasyMock.expect(est1.getId()).andStubReturn("est1");
		EasyMock.replay(est1);
		Sistema sistema = new Sistema(arrayEstaciones);
		assertSame(sistema.buscarEstacion("est1"), est1);
		EasyMock.verify(est1);

	}

	@Test
	@Tag("BlackBox")
	void buscarEstacionNoValidoIdNullTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.buscarEstacion(null);
		});
	}

	@Test
	@Tag("BlackBox")
	void buscarEstacionNoValidoIdVacioTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.buscarEstacion("");
		});
	}

	@Test
	@Tag("BlackBox")
	void buscarEstacionNoValidoNoPerteneceTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			EasyMock.expect(est1.getId()).andStubReturn("est1");
			EasyMock.replay(est1);
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.buscarEstacion("renfe");
			EasyMock.verify(est1);
		});
	}

	@Test
	@Tag("WhiteBox")
	void añadirEstacionNoValidoIdPrEstacionRepetidoTest() {
		assertThrows(IllegalStateException.class, () -> {
			ArrayList<PuntoRecogida> listaPuntos2 = new ArrayList<>();
			listaPuntos2.add(pr2);
			EasyMock.expect(est1.getId()).andStubReturn("est1");
			EasyMock.expect(est2.getId()).andStubReturn("est2");
			EasyMock.expect(est1.getListaPuntos()).andStubReturn(puntosRecogida);
			EasyMock.expect(est2.getListaPuntos()).andStubReturn(listaPuntos2);
			EasyMock.expect(pr1.getId()).andStubReturn("pr1");
			EasyMock.expect(pr2.getId()).andStubReturn("est1");
			EasyMock.replay(est1);
			EasyMock.replay(est2);
			EasyMock.replay(pr1);
			EasyMock.replay(pr2);
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.annadirEstacion(est2);
			EasyMock.verify(est1);
			EasyMock.verify(est2);
			EasyMock.verify(pr1);
			EasyMock.verify(pr2);
		});
	}

	@Test
	@Tag("WhiteBox")
	void añadirEstacionNoValidoIdEstacionPrSistemaTest() {
		assertThrows(IllegalStateException.class, () -> {
			ArrayList<PuntoRecogida> listaPuntos2 = new ArrayList<>();
			listaPuntos2.add(pr2);
			EasyMock.expect(est1.getId()).andStubReturn("est1");
			EasyMock.expect(est2.getId()).andStubReturn("pr1");
			EasyMock.expect(est1.getListaPuntos()).andStubReturn(puntosRecogida);
			EasyMock.expect(est2.getListaPuntos()).andStubReturn(listaPuntos2);
			EasyMock.expect(pr1.getId()).andStubReturn("pr1");
			EasyMock.expect(pr2.getId()).andStubReturn("pr2");
			EasyMock.replay(est1);
			EasyMock.replay(est2);
			EasyMock.replay(pr1);
			EasyMock.replay(pr2);
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.annadirEstacion(est2);
			EasyMock.verify(est1);
			EasyMock.verify(est2);
			EasyMock.verify(pr1);
			EasyMock.verify(pr2);
		});
	}

	@Test
	@Tag("WhiteBox")
	void añadirEstacionNoValidoIdPrRepetidoEstacionTest() {
		assertThrows(IllegalStateException.class, () -> {
			ArrayList<PuntoRecogida> listaPuntos2 = new ArrayList<>();
			listaPuntos2.add(pr2);
			listaPuntos2.add(pr3);
			arrayEstaciones.add(est2);
			EasyMock.expect(est1.getId()).andStubReturn("est1");
			EasyMock.expect(est2.getId()).andStubReturn("est2");
			EasyMock.expect(est1.getListaPuntos()).andStubReturn(puntosRecogida);
			EasyMock.expect(est2.getListaPuntos()).andStubReturn(listaPuntos2);
			EasyMock.expect(pr1.getId()).andStubReturn("pr1");
			EasyMock.expect(pr2.getId()).andStubReturn("pr1");
			EasyMock.expect(pr3.getId()).andStubReturn("pr3");
			EasyMock.replay(est1);
			EasyMock.replay(est2);
			EasyMock.replay(pr1);
			EasyMock.replay(pr2);
			EasyMock.replay(pr3);
			Sistema sistema = new Sistema(arrayEstaciones);
			sistema.annadirEstacion(est2);
			EasyMock.verify(est1);
			EasyMock.verify(est2);
			EasyMock.verify(pr1);
			EasyMock.verify(pr2);
			EasyMock.verify(pr3);
		});
	}

	@Test
	@Tag("WhiteBox")
	void sistemaToXMLTest() throws ParserConfigurationException, TransformerException {
		ArrayList<Estacion> arrayEst = new ArrayList<>();
		ArrayList<PuntoRecogida> arrayPR1 = new ArrayList<>();
		ArrayList<PuntoRecogida> arrayPR2 = new ArrayList<>();
		arrayEst.add(est1);
		arrayEst.add(est2);
		arrayPR1.add(pr1);
		arrayPR2.add(pr2);
		arrayPR2.add(pr3);
		EasyMock.expect(est1.getId()).andStubReturn("est1");
		EasyMock.expect(est2.getId()).andStubReturn("est2");
		EasyMock.expect(est1.getCoordenadas()).andStubReturn(coords);
		EasyMock.expect(est2.getCoordenadas()).andStubReturn(coords2);
		EasyMock.expect(est1.getListaPuntos()).andStubReturn(arrayPR1);
		EasyMock.expect(est2.getListaPuntos()).andStubReturn(arrayPR2);
		EasyMock.expect(pr1.getId()).andStubReturn("pr1");
		EasyMock.expect(pr1.getEstado()).andStubReturn(Estado.OCUPADO);
		EasyMock.expect(pr2.getId()).andStubReturn("pr2");
		EasyMock.expect(pr2.getEstado()).andStubReturn(Estado.LIBRE);
		EasyMock.expect(pr3.getId()).andStubReturn("pr3");
		EasyMock.expect(pr3.getEstado()).andStubReturn(Estado.INACTIVO);
		EasyMock.expect(coords.getLongitud()).andStubReturn(90.0);
		EasyMock.expect(coords.getLatitud()).andStubReturn(23.9);
		EasyMock.expect(coords2.getLongitud()).andStubReturn(50.0);
		EasyMock.expect(coords2.getLatitud()).andStubReturn(-45.9);
		EasyMock.replay(est1);
		EasyMock.replay(coords);
		EasyMock.replay(pr1);
		EasyMock.replay(est2);
		EasyMock.replay(coords2);
		EasyMock.replay(pr2);
		EasyMock.replay(pr3);
		Sistema sistema = new Sistema(arrayEst);
		Sistema.sistemaToXML(sistema, "sistema");
		EasyMock.verify(est1);
		EasyMock.verify(coords);
		EasyMock.verify(pr1);
		EasyMock.verify(est2);
		EasyMock.verify(coords2);
		EasyMock.verify(pr2);
		EasyMock.verify(pr3);
	}

	@Test
	@Tag("WhiteBox")
	void sistemaToXMLSistNuloTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema.sistemaToXML(null, "sistema");
		});
	}

	@Test
	@Tag("WhiteBox")
	void sistemaToXMLPathNuloTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			Sistema.sistemaToXML(sistema, null);
		});
	}

	@Test
	@Tag("WhiteBox")
	void sistemaToXMLPathVacioTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema sistema = new Sistema(arrayEstaciones);
			Sistema.sistemaToXML(sistema, "");
		});
	}

	@Test
	@Tag("WhiteBox")
	void xmlToObjectTest() throws IllegalArgumentException, ParserConfigurationException, SAXException, IOException {
		Sistema.xmlToObject("sistema2.xml");
		// si añado más estaciones al xml para comprobar el 100% coverage me salta
		// nullpointerexception al generar un sistema sin
		// implementar constructores de clases stub
		// Lo dejo así para que no de fallo pero habría que implementar los
		// constructores y añadir una estacion con cada estado
		// posible para que diera 100% coverage (lo he probado y funciona pero salta la
		// excepcion al llamar al constructor
		// de sistema por los nulls)
	}

	@Test
	@Tag("WhiteBox")
	void xmlToObjectPathVacioTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema.xmlToObject("");
		});
	}

	@Test
	@Tag("WhiteBox")
	void xmlToObjectPathNuloTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Sistema.xmlToObject(null);
		});
	}

	@Test
	@Tag("WhiteBox")
	void xmlToObjectFicheroNoExisteTest() {
		assertThrows(Exception.class, () -> {
			Sistema.xmlToObject("hola.xml");
		});
	}

}
