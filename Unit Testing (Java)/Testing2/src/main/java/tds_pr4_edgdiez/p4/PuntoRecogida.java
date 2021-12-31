package tds_pr4_edgdiez.p4;
/**
 * Clase que representa un punto de recogida de bicis. Los puntos de recodida
 * tienen que tener un id único en el sistema y estado (Libre, Ocupado,
 * Inactivo). Las restricciones de cambio de estado son: Libre -- Ocupado o
 * Inactivo Ocupado -- Libre Inactivo -- Libre
 * 
 * @author edgdiez
 * @author marcorr *
 */
public class PuntoRecogida {

	/**
	 * Crea un nuevo punto de recogida de bicis
	 * 
	 * @param id     El identificador de este punto de recogida. Debe de ser único
	 *               en todo el sistema y tener al menos un caracter
	 * @param estado Estado inicial del punto de recogida. Debe de ser Libre,
	 *               Ocupado o Inactivo
	 * @throws IllegalArgumentException si el id nulo
	 * @throws IllegalArgumentException si el id es vacío
	 * @throws IllegalArgumentException si el estado es null
	 */
	public PuntoRecogida(String id, Estado estado) {
		//implementacion
	}

	/**
	 * Devuelve el id del punto de recogida
	 * 
	 * @return El id del punto de recogida
	 */
	public String getId() {
		return null;
	}

	/**
	 * Devuelve el estado actual del punto de recogida
	 * 
	 * @return El estado actual
	 */
	public Estado getEstado() {
		return null;
	}

	/**
	 * Cambia el estado del punto de recogida. Este cambio debe de cumplir las
	 * restricciones especficadas en la clase para poder realizarse
	 * 
	 * @param nuevoEstado El nuevo estado a cambiar
	 * @throws IllegalArgumentException si el nuevoEstado es null
	 * @throws IllegalStateException si no se puede hacer el cambio
	 */
	public void setEstado(Estado nuevoEstado) {
		//implementacion
	}

}