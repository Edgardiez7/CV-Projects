package tds.p3;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase correo manejara la informacion relativa a los emails de 
 * nuestro gestor de correos.
 * Cada correo tendra:
 * 		- Direccion de origen.
 * 		- Direccion de destino.
 * 		- Fecha.
 * 		- Hora.
 * 		- Asunto.
 * 		- Contenido.
 * 		- Categoria.
 * 
 * Las direcciones de correo deberan ser validas.
 * 
 * El asunto debera tener entre una y diez palabras.
 * 
 * Los correos deberan poder ser comparados usando fecha y hora.
 * 
 * Dos correos seran iguales si coincide: fecha, hora, asunto y categoria.
 * Los demas parametros podran ser diferentes.
 * 
 * @author edgdiez Edgar Diez Alonso
 *
 */

public class Correo implements Comparable<Correo>{
	
	/**
	 * Crea un correo a partir de los siguientes parametros.
	 * 
	 * @param direccionOrigen Direccion del emisor del correo.
	 * @param direccionDestino Direccion del receptor del correo.
	 * @param fecha Fecha en la que se crea el correo.
	 * @param hora Hora en la que se crea el correo.
	 * @param asunto Asunto del correo, resumen del contenido.
	 * @param contenido Cuerpo del correo.
	 * @param categoria Categoria del correo.
	 * @throws IllegalArgumentException Si el email de origen es null 
	 * @throws IllegalArgumentException Si el email de destino es null
	 * @throws IllegalArgumentException Si el email de origen no es valido (no cumple el formato)
	 * @throws IllegalArgumentException Si el email de destino no es valido (no cumple el formato)
	 * @throws IllegalArgumentException Si la fecha es null
	 * @throws IllegalArgumentException Si la fecha es posterior a la actual
	 * @throws IllegalArgumentException Si la hora es null
	 * @throws IllegalArgumentException Si la fecha es el dia de hoy y la hora es posterior	 
	 * @throws IllegalArgumentException Si el asunto esta vacío o es null
	 * @throws IllegalArgumentException Si el asunto tiene mas de 10 palabras
	 * @throws IllegalArgumentException Si el contenido es null
	 * @throws IllegalArgumentException Si la categoria es null
	 */

	 private String direccionOrigen;
	 private String direccionDestino;
	 private LocalDate fecha;
	 private LocalTime hora;
	 private String asunto;
	 private String contenido;
	 private Categoria categoria;

	public Correo(String direccionOrigen, String direccionDestino, LocalDate fecha,
			LocalTime hora, String asunto, String contenido, Categoria categoria) throws IllegalArgumentException {
				if(direccionOrigen == null) throw new IllegalArgumentException("Correo origen no valido");
				if(direccionDestino == null) throw new IllegalArgumentException("Correo destino no valido");				
				//Validacion de email mendiante regex
				String regex = "^[A-Za-z0-9]([_.-]?[A-Za-z0-9]+)+@[a-zA-Z0-9]([-.]?[a-zA-Z0-9]+){2,}\\.[a-zA-Z]{2,}$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcherOrigen = pattern.matcher(direccionOrigen);
				Matcher matcherDestino = pattern.matcher(direccionDestino);
				if(!matcherOrigen.matches()) throw new IllegalArgumentException("Correo origen no valido");
				if(!matcherDestino.matches()) throw new IllegalArgumentException("Correo destino no valido");
				if(fecha == null) throw new IllegalArgumentException("Hora no valida");
				if(fecha.isAfter(LocalDate.now())) throw new IllegalArgumentException("Fecha no valida");
				if(hora == null) throw new IllegalArgumentException("Hora no valida");
				if(fecha.isEqual(LocalDate.now()) && hora.isAfter(LocalTime.now())) throw new IllegalArgumentException("Hora no valida");				
				if(asunto == null || asunto.equals("")) throw new IllegalArgumentException("Asunto no valido");
				StringTokenizer palabrasAsunto = new StringTokenizer(asunto);
				int numeroPalabrasAsunto = palabrasAsunto.countTokens();
				if (numeroPalabrasAsunto > 10) throw new IllegalArgumentException("Asunto tiene más de 10 palabras");
				if(contenido == null) throw new IllegalArgumentException("Contenido no valido");
				if(categoria == null) throw new IllegalArgumentException("Categoria no valida");

				this.direccionOrigen = direccionOrigen;
				this.direccionDestino = direccionDestino;
				this.fecha = fecha;
				this.hora = hora;
				this.asunto = asunto;
				this.contenido = contenido;
				this.categoria = categoria;
	}
	
	/**
	 * Devuelve la direccion de Origen de un correo.
	 * La direccion del emisor. 
	 * 
	 * @return Direccion Origen.
	 */
	public String getDireccionOrigen() {
		return direccionOrigen;
	}
	
	/**
	 * Devuelve la direccion Destino de un correo.
	 * La direccion del receptor.
	 * 
	 * @return Direccion Destino.
	 */
	public String getDireccionDestino() {
		return direccionDestino;
	}
	
	/**
	 * Devuelve la fecha de creacion del correo.
	 * 
	 * @return Fecha de creacion.
	 */
	public LocalDate getFecha() {
		return fecha;
	}
	
	/**
	 * Devuelve la hora de la creacion del correo.
	 * 
	 * @return Hora de creacion.
	 */
	public LocalTime getHora() {
		return hora;
	}
	
	/**
	 * Devuelve el asunto del correo.
	 * Resumen del cuerpo del correo.
	 * 
	 * @return Asunto del correo.
	 */
	public String getAsunto() {
		return asunto;
	}
	
	/**
	 * Devuelve el contenido del correo.
	 * La informacion que introduce el usuario
	 * en el cuerpo del correo.
	 * 
	 * @return Contenido del correo.
	 */
	public String getContenido() {
		return contenido;
	}
	
	/**
	 * Devuelve la categoria del correo.
	 * 
	 * @return Categoria del correo.
	 */
	public Categoria getCategoria() {
		return categoria;
	}
	
	
	
	/**
	 * Devuelve verdadero si los correos tienen los mismos campos,
	 * false en caso contrario.
	 * 
	 * @return true si los correos tienen los mismos campos
	 */
	public boolean equals(Correo c) {
		boolean iguales= false;
		if(getFecha().equals(c.getFecha()) && getHora().equals(c.getHora()) 
				&& getAsunto().equals(c.getAsunto()) && getCategoria().equals(c.getCategoria())){
			iguales = true;
		}
		return iguales;
	}

	/**
	 * Compara correos por fecha. A igual fecha compara por hora.
	 * Si tienen misma fecha y hora los deja en el orden inicial.
	 * @param correo correo a comparar
	 * @return -1(si es anterior) 0(si tienen misma fecha) 1(si es posterior)
	 */
	@Override 
	public int compareTo(Correo correo){
		int comparacion;
		if(getFecha().isBefore(correo.getFecha())){
			comparacion = -1;
		} else if(getFecha().isAfter(correo.getFecha())){
			comparacion = 1;
		} else{ //misma fecha
			if(getHora().isBefore(correo.getHora())){
				comparacion = -1;
			} else if(getHora().isAfter(correo.getHora())){
				comparacion = 1;
			} else { //misma fecha y hora
				comparacion = 0;
			}
		}
		return comparacion;
	}
}
