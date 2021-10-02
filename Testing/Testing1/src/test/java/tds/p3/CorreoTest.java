package tds.p3;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


/**
 * 
 * @author edgdiez Edgar Diez Alonso
 */
public class CorreoTest {

	private final int CORREOLIMITE = 316;
	private String contenido = "Me llena de orgullo y satisfaccion, en esta fechas tan sennaladas el vino y las gambas";
	private LocalDate fecha = LocalDate.of(2020, 1, 7);
	private LocalTime hora = LocalTime.of(20,30);
	
	@Test
	@Tag("BlackBox")
	void constructorValidoTest() {
		
		Correo correo = new Correo("paco-77_albal.perico@mail-33.com", "gema_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caido por la ventana", contenido, Categoria.BORRADOR);
		
		assertNotNull(correo);
		assertEquals(correo.getDireccionOrigen(), "paco-77_albal.perico@mail-33.com");
		assertEquals(correo.getDireccionDestino(), "gema_43.setas-ricas@email-76.es");
		assertEquals(correo.getFecha(), fecha);
		assertEquals(correo.getHora(), hora);
		assertEquals(correo.getAsunto(), "Paco se ha caido por la ventana");
		assertEquals(correo.getContenido(), contenido);
		assertEquals(correo.getCategoria(), Categoria.BORRADOR);
	}

	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen1Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("a@mes", "gema@email.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen2Test() {
		char[] origen = new char[CORREOLIMITE];
		for(int i = 0; i < CORREOLIMITE; i++) origen[i] = 'a';
		String correoOrigen = new String(origen);
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo( correoOrigen + "@m.es", "gema@email.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen3Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("a?@m.es", "gema@email.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen4Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("a@_m.es", "gema@email.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen5Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("a_.p@m.es", "gema@email.es", fecha, hora, 
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen6Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("a.-l@m.es", "gema@email.es", fecha, hora, 
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen7Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("a@m-.es", "gema@email.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen8Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("a@ma.e", "gema@email.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen9Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("aaa.es", "gema@email.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen10Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("a@m@a.es", "gema@email.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen11Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("a@2m.es", "gema@email.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen12Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("a@mes", "gema@email.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen13Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("a@m..es", "gema@email.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen14Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("a--b@m.es", "gema@email.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoOrigen15Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("a__b@m.es", "gema@email.es", fecha, hora, 
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoDestino1Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo( "gema@email.es", "a@mes",fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoDestino2Test() {
		char[] destino = new char[CORREOLIMITE];
		for(int i = 0; i < CORREOLIMITE; i++) destino[i] = 'a';
		String correoDestino = new String(destino);
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo( "gema@email.es", correoDestino + "@m.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoDestino3Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema@email.es", "a?@m.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoDestino4Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema@email.es","a@_m.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoDestino5Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema@email.es", "a_.p@m.es", fecha, hora, 
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void ConstructorNoValidoDestino6Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo( "gema@email.es", "a.-l@m.es", fecha, hora, 
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoDestino7Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema@email.es", "a@m-.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoDestino8Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema@email.es","a@ma.e",  fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoDestino9Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema@email.es", "aaa.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoDestino10Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema@email.es", "a@m@a.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoDestino11Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema@email.es", "a@2m.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoDestino12Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema@email.es", "a@mes", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoDestino13Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema@email.es", "a@m..es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoDestino14Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema@email.es", "a--b@m.es", fecha, hora,
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidoDestino15Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema@email.es", "a__b@m.es", fecha, hora, 
					"Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidaFecha1Test() {
		LocalDate fecha = LocalDate.now().plusDays(1);
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("paco-77_albal.perico@mail-33.com", "gema_43.setas-ricas@email-76.es",
					fecha, hora, "Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidaFecha2Test() {
		LocalDate fecha = LocalDate.now();
		LocalTime hora = LocalTime.now().plusHours(2);
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("paco-77_albal.perico@mail-33.com", "gema_43.setas-ricas@email-76.es",
					fecha, hora, "Paco se ha caido por la ventana",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("BlackBox")
	void constructorNoValidaAsunto1Test() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("paco-77_albal.perico@mail-33.com",
					"gema_43.setas-ricas@email-76.es", fecha, hora, "",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void constructorNoValidoOrigenNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo(null, "gema_2@gmail.com",
					fecha, hora, "Este asunto",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void constructorNoValidoDestinoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema_2@gmail.com", null,
					fecha, hora, "Este asunto",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void constructorNoValidoFechaNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema_2@gmail.com", "paco@hotmail.com",
					null, hora, "Este asunto",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void constructorNoValidoHoraNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema_2@gmail.com", "paco@hotmail.com",
					fecha, null, "Este asunto",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void constructorNoValidoHoraPosterior() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema_2@gmail.com", "paco@hotmail.com",
					LocalDate.now(), LocalTime.now().plusHours(2), "Este asunto",contenido, Categoria.BORRADOR);
		});
	}
	@Test
	@Tag("WhiteBox")
	void constructorNoValidoAsuntoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema_2@gmail.com", "paco@hotmail.com",
					fecha, hora, null,contenido, Categoria.BORRADOR);
		});
	}
	@Test
	@Tag("WhiteBox")
	void constructorNoValidoAsuntoVacio() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema_2@gmail.com", "paco@hotmail.com",
					fecha, hora, "",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void constructorNoValidoAsuntoLargo() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema_2@gmail.com", "paco@hotmail.com",
					fecha, hora, "Uno Dos Tres Cuatro Cinco Seis Siete Ocho Nueve Diez Once",contenido, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void constructorNoValidoContenidoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema_2@gmail.com", "paco@hotmail.com",
					fecha, hora, "Asunto",null, Categoria.BORRADOR);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void constructorNoValidoCategoriaNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Correo correo = new Correo("gema_2@gmail.com", "paco@hotmail.com",
					fecha, hora, "Asunto",contenido, null);
		});
	}
	
	@Test
	@Tag("WhiteBox")
	void equalsTest() {
		Correo correo1 = new Correo("paco-77_albal.perico@mail-33.com", "gema_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caido por la ventana", contenido, Categoria.BORRADOR);
		Correo correo2 = new Correo("paco34-77_albal.perico@mail-33.com", "gema34_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caido por la ventana", contenido + "hola", Categoria.BORRADOR);
		assertTrue(correo1.equals(correo2));
		
	}
	
	@Test
	@Tag("WhiteBox")
	void equalsTestFechaDistinta() {
		Correo correo1 = new Correo("paco-77_albal.perico@mail-33.com", "gema_43.setas-ricas@email-76.es",
				fecha.plusDays(7), hora, "Paco se ha caido por la ventana", contenido, Categoria.BORRADOR);
		Correo correo2 = new Correo("paco34-77_albal.perico@mail-33.com", "gema34_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caido por la ventana", contenido + "hola", Categoria.BORRADOR);
		assertFalse(correo1.equals(correo2));
	}
	
	@Test
	@Tag("WhiteBox")
	void equalsTestHoraDistinta() {
		Correo correo1 = new Correo("paco-77_albal.perico@mail-33.com", "gema_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caido por la ventana", contenido, Categoria.BORRADOR);
		Correo correo2 = new Correo("paco34-77_albal.perico@mail-33.com", "gema34_43.setas-ricas@email-76.es",
				fecha, hora.plusHours(2), "Paco se ha caido por la ventana", contenido + "hola", Categoria.BORRADOR);
		assertFalse(correo1.equals(correo2));
	}
	
	@Test
	@Tag("WhiteBox")
	void equalsTestAsuntoDistinto() {
		Correo correo1 = new Correo("paco-77_albal.perico@mail-33.com", "gema_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caido por la ventana", contenido, Categoria.BORRADOR);
		Correo correo2 = new Correo("paco34-77_albal.perico@mail-33.com", "gema34_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caido", contenido + "hola", Categoria.BORRADOR);
		assertFalse(correo1.equals(correo2));
	}
	
	@Test
	@Tag("WhiteBox")
	void equalsTestCategoriaDistinta() {
		Correo correo1 = new Correo("paco-77_albal.perico@mail-33.com", "gema_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caido por la ventana", contenido, Categoria.BORRADOR);
		Correo correo2 = new Correo("paco34-77_albal.perico@mail-33.com", "gema34_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caido por la ventana", contenido + "hola", Categoria.ENVIADO);
		assertFalse(correo1.equals(correo2));
	}
	
	@Test
	@Tag("WhiteBox")
	void compareToIgualesTest() {
		Correo correo1 = new Correo("paco-77_albal.perico@mail-33.com", "gema_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caidoasdf por la ventana", contenido, Categoria.BORRADOR);
		Correo correo2 = new Correo("paco34-77_albal.perico@mail-33.com", "gema34_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caido por la ventana", contenido + "hola", Categoria.BORRADOR);
		assertEquals(correo1.compareTo(correo2), 0);
		
	}
	
	@Test
	@Tag("WhiteBox")
	void compareToAnteriorFechaTest() {
		Correo correo1 = new Correo("paco-77_albal.perico@mail-33.com", "gema_43.setas-ricas@email-76.es",
				fecha.minusDays(2), hora, "Paco se ha caidoasdfasdf por la ventana", contenido, Categoria.BORRADOR);
		Correo correo2 = new Correo("paco34-77_albal.perico@mail-33.com", "gema34_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caido por la ventana", contenido + "hola", Categoria.BORRADOR);
		assertEquals(correo1.compareTo(correo2), -1);
		
	}
	
	@Test
	@Tag("WhiteBox")
	void compareToPosteriorFechaTest() {
		Correo correo1 = new Correo("paco-77_albal.perico@mail-33.com", "gema_43.setas-ricas@email-76.es",
				fecha.plusDays(2), hora, "Paco se ha caidoasdfasdf por la ventana", contenido, Categoria.BORRADOR);
		Correo correo2 = new Correo("paco34-77_albal.perico@mail-33.com", "gema34_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caido por la ventana", contenido + "hola", Categoria.BORRADOR);
		assertEquals(correo1.compareTo(correo2), +1);
		
	}
	
	@Test
	@Tag("WhiteBox")
	void compareToAnteriorHoraTest() {
		Correo correo1 = new Correo("paco-77_albal.perico@mail-33.com", "gema_43.setas-ricas@email-76.es",
				fecha, hora.minusHours(2), "Paco se ha caidoasdfasdf por la ventana", contenido, Categoria.BORRADOR);
		Correo correo2 = new Correo("paco34-77_albal.perico@mail-33.com", "gema34_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caido por la ventana", contenido + "hola", Categoria.BORRADOR);
		assertEquals(correo1.compareTo(correo2), -1);
		
	}
	
	@Test
	@Tag("WhiteBox")
	void compareToPosteriorHoraTest() {
		Correo correo1 = new Correo("paco-77_albal.perico@mail-33.com", "gema_43.setas-ricas@email-76.es",
				fecha, hora.plusHours(2), "Paco se ha caidoasdfasdf por la ventana", contenido, Categoria.BORRADOR);
		Correo correo2 = new Correo("paco34-77_albal.perico@mail-33.com", "gema34_43.setas-ricas@email-76.es",
				fecha, hora, "Paco se ha caido por la ventana", contenido + "hola", Categoria.BORRADOR);
		assertEquals(correo1.compareTo(correo2), +1);
		
	}
}