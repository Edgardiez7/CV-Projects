package tds_pr4_edgdiez.p4;

import java.util.ArrayList;

/**
 * Clase que representa una estación de puntos de recogida de bicis. Cada
 * estación debe de tener al menos 1 punto de recogida. El identificador de la
 * estacion debe de ser único en todo el sistema. Cada estacion tiene una
 * localización definida por unas coordenadas geográficas
 * 
 * @author edgdiez
 * @author marcorr
 *
 */

public class Estacion {

	/**
	 * Método de creación de la estación
	 * 
	 * @param id          El id de la estación. Debe de ser único en todo
	 *                    el sistema y tener al menos un caracter
	 * @param listaPuntos Lista de puntos de recogida de esta estación. No puede
	 *                    haber dos puntos de recogida con el mismo id. Al menos
	 *                    tiene que haber un punto de recogida
	 * @param localizacion   Coordenadas de la estación
	 * @throws IllegalArgumentException Si el id es nulo o vacío
	 * @throws IllegalArgumentException Si la lista de puntos de recogida es vacía
	 * @throws IllegalArgumentException Si la lista de puntos de recogida tiene
	 *                                  puntos con id repetidos
	 * @throws IllegalArgumentException si la lista de puntos de recogida tiene
	 *                                  menos de un elemento
	 * @throws IllegalArgumentException Si la localización es nula
	 */
	public Estacion(String id, ArrayList<PuntoRecogida> listaPuntos, Coordenadas localizacion) {
		//implementacion
	}

	/**
	 * Añade un nuevo punto de recogida a la estación
	 * 
	 * @param nuevoPunto El nuevo punto de recogida a añadir
	 * @throws IllegalArgumentException Si nuevoPunto es null
	 * @throws IllegalArgumentException Si el nuevoPunto ya existe en la estación
	 *                                  (mismo id)
	 */
	public void addPuntoRecogida(PuntoRecogida nuevoPunto) {
		//implementacion
	}

	/**
	 * Elimina un punto de recogida de la estación
	 * 
	 * @param punto El punto de recogida a eliminar
	 * @throws IllegalArgumentException Si el punto es null
	 * @throws IllegalStateException si al eliminar el punto la estacion tiene 0 puntos
	 * @throws IllegalArgumentException Si el punto no existe en la estación
	 */
	public void eliminarPuntoRecogida(PuntoRecogida punto) {
		//implementacion
	}

	/**
	 * Cambia el estado de un punto de recogida
	 * 
	 * @param punto Punto de recogida a cambiar el estado
	 * @throws IllegalArgumentException si el punto es null
	 * @throws IllegalArgumentException si el estado es null
	 * @throws IllegalArgumentException si el punto no existe en esta estación
	 * @throws IllegalStateException    si no es posible cambiar el estado del punto
	 *                                  de recogida
	 */
	public void cambiarEstadoPuntoRecogida(PuntoRecogida punto, Estado nuevoEstado) {
		//implementacion
	}

	/**
	 * Obtiene los puntos de recogida dado una estado
	 * 
	 * @param estado El estado en que tienen que estar los puntos de recogida
	 *               solicitados
	 * @return Una lista de puntos de recogida con el estado indicado por estado
	 * @throws IllegalArgumentException el estado es null
	 * @throws IllegalStateException si no existen puntos de recogida en ese estado
	 */
	public ArrayList<PuntoRecogida> getPuntosDeRecogida(Estado estado) {
		return null;
	}
	
	/**
	 * Getter del id de una estacion
	 * @return id string único identificador de estacion
	 */
	public String getId() {
		return null;
	}
	
	/**
	 * Getter del arraylist con los puntos de una estacion
	 * @return ListaPuntos arraylist con los puntos
	 */
	public ArrayList<PuntoRecogida> getListaPuntos(){
		return null;
	}
	
	/**
	 * Getter de las coordenadas de una estacion
	 * @return coordenadas coords de la estacion
	 */
	public Coordenadas getCoordenadas() {
		return null;
	}
}