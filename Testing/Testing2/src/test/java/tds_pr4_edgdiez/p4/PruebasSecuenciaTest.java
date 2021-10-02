package tds_pr4_edgdiez.p4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.MockType;

import java.util.ArrayList;

/**
 * Clase de testeo pruebas de secuencia del sistema
 * 
 * @author edgdiez
 */

@ExtendWith(EasyMockExtension.class)
public class PruebasSecuenciaTest {

	ArrayList<PuntoRecogida> listaPts1;
	ArrayList<PuntoRecogida> listaPts2;
	ArrayList<PuntoRecogida> listaPts3;
	ArrayList<Estacion> listaEsts;
	Sistema sistema;
	@Mock(type = MockType.NICE)
	PuntoRecogida pr1e1;
	@Mock(type = MockType.NICE)
	PuntoRecogida pr2e1;
	@Mock(type = MockType.NICE)
	PuntoRecogida pr1e2;
	@Mock(type = MockType.NICE)
	PuntoRecogida pr2e2;
	@Mock(type = MockType.NICE)
	PuntoRecogida pr1e3;
	@Mock
	Coordenadas c1;
	@Mock
	Coordenadas c2;
	@Mock
	Coordenadas c3;
	@Mock
	Coordenadas c4;
	@Mock
	Estacion est1;
	@Mock
	Estacion est2;
	@Mock
	Estacion est3;

	@BeforeEach
	void inic() {
		listaPts1 = new ArrayList<>();
		listaPts1.add(pr1e1);
		listaPts1.add(pr2e1);
		listaPts2 = new ArrayList<>();
		listaPts2.add(pr1e2);
		listaPts2.add(pr2e2);
		listaPts3 = new ArrayList<>();
		listaPts3.add(pr1e3);
		listaEsts = new ArrayList<>();
		listaEsts.add(est1);
		listaEsts.add(est2);	
	}

	@AfterEach
	void end() {
		listaPts1.clear();
		listaPts2.clear();
		listaPts3.clear();
		listaEsts.clear();
	}

	@Test
	@Tag("Secuencia")
	 void pruebaSistemaTest() {
		ArrayList<PuntoRecogida> arrayInact = new ArrayList<>();
		arrayInact.add(pr1e2);
		EasyMock.expect(est1.getId()).andStubReturn("est1");
		EasyMock.expect(est1.getCoordenadas()).andStubReturn(c1);
		EasyMock.expect(est2.getCoordenadas()).andStubReturn(c2);
		EasyMock.expect(est3.getCoordenadas()).andStubReturn(c3);
		EasyMock.expect(est2.getId()).andStubReturn("est2");
		EasyMock.expect(est3.getId()).andStubReturn("est3");
		EasyMock.expect(est1.getListaPuntos()).andStubReturn(listaPts1);
		EasyMock.expect(est2.getListaPuntos()).andStubReturn(listaPts2);
		EasyMock.expect(est2.getPuntosDeRecogida(Estado.INACTIVO)).andStubReturn(arrayInact);
		EasyMock.expect(est3.getListaPuntos()).andStubReturn(listaPts3);
		EasyMock.expect(pr1e1.getId()).andStubReturn("pr1e1");
		EasyMock.expect(pr2e1.getId()).andStubReturn("pr2e1");
		EasyMock.expect(pr1e2.getId()).andStubReturn("pr1e2");
		EasyMock.expect(pr2e2.getId()).andStubReturn("pr2e2");
		EasyMock.expect(pr1e3.getId()).andStubReturn("pr1e3");
		EasyMock.expect(pr1e1.getEstado()).andStubReturn(Estado.LIBRE);	
		EasyMock.expect(pr2e1.getEstado()).andStubReturn(Estado.LIBRE);
		EasyMock.expect(pr1e2.getEstado()).andStubReturn(Estado.LIBRE);
		EasyMock.expect(pr2e2.getEstado()).andStubReturn(Estado.OCUPADO);
		EasyMock.expect(pr1e3.getEstado()).andStubReturn(Estado.LIBRE);
		EasyMock.expect(c2.distancia(c4)).andReturn(100.0);
		EasyMock.expect(c2.distancia(c3)).andReturn(401.0);
		EasyMock.expect(c3.distancia(c4)).andReturn(501.0);
		EasyMock.replay(est1);
		EasyMock.replay(est2);
		EasyMock.replay(est3);
		EasyMock.replay(pr1e1);
		EasyMock.replay(pr1e2);
		EasyMock.replay(pr2e1);
		EasyMock.replay(pr2e2);
		EasyMock.replay(pr1e3);
		EasyMock.replay(c2);
		EasyMock.replay(c3);
		EasyMock.replay(c4);
		sistema = new Sistema(listaEsts);
		sistema.annadirEstacion(est3);
		assertTrue(sistema.getEstaciones().contains(est3));
		sistema.eliminarEstacion(est1);
		assertFalse(sistema.getEstaciones().contains(est1));
		sistema.alquilarBici(pr2e2);
		EasyMock.reset(pr2e2);
		EasyMock.expect(pr2e2.getEstado()).andStubReturn(Estado.LIBRE);
		EasyMock.replay(pr2e2);
		assertEquals(sistema.buscarEstacion("est2").getListaPuntos().get(1).getEstado(), Estado.LIBRE);
		sistema.devolverBici(pr2e2);
		EasyMock.reset(pr2e2);
		EasyMock.expect(pr2e2.getEstado()).andStubReturn(Estado.OCUPADO);
		EasyMock.replay(pr2e2);
		assertEquals(sistema.buscarEstacion("est2").getListaPuntos().get(1).getEstado(), Estado.OCUPADO);
		sistema.desactivarPuntoRecogida(pr1e2);
		EasyMock.reset(pr2e1);
		EasyMock.expect(pr2e1.getEstado()).andStubReturn(Estado.INACTIVO);
		EasyMock.replay(pr2e1);
		assertEquals(sistema.numeroPuntosRecogidaEstacionEnEstado(est2, Estado.INACTIVO), 1);
		ArrayList<Estacion> estCerca = sistema.estacionesCercanas(c4);
		assertNotNull(estCerca);
		assertFalse(estCerca.isEmpty());
		assertEquals(estCerca.size(), 1);
		assertTrue(estCerca.contains(est2));
		assertEquals(sistema.distanciaEntreEstaciones(est2, est3), 401);
		EasyMock.verify(est1);
		EasyMock.verify(est2);
		EasyMock.verify(est3);
		EasyMock.verify(pr1e1);
		EasyMock.verify(pr1e2);
		EasyMock.verify(pr2e1);
		EasyMock.verify(pr2e2);
		EasyMock.verify(pr1e3);
		EasyMock.verify(c2);
		EasyMock.verify(c3);
		EasyMock.verify(c4);
	}
}
