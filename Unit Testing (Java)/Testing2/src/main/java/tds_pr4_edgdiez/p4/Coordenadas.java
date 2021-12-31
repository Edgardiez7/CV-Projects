package tds_pr4_edgdiez.p4;

/**
 * Clase que representa unas coordenadas geográficas expresadas en coordenadas
 * decimales. La latitud debe de estar comprendida entre -90 y 90 y la longitud
 * entre -180 y 180
 * 
 * @author edgdiez
 * @author marcorr
 *
 */
public class Coordenadas {

	/**
	 * Creación de las coordenadas
	 * 
	 * @param latitud  Valor de la latitud (entre -90 y 90)
	 * @param longitud Valor de la longitud (entre -180 y 180)
	 * @throws IllegalArgumentException si la latitud es menor que -90 o mayor que
	 *                                  90
	 * @throws IllegalArgumentException si la longitud es menor que -180 o mayor que
	 *                                  180
	 */
	public Coordenadas(double latitud, double longitud) {

	}

	/**
	 * Devuelve la latitud de las coordenadas
	 * 
	 * @return La latitud de las coordenadas
	 */
	public double getLatitud() {
		return -91;
	}

	/**
	 * Devuelve la longitud de las coordenadas
	 * 
	 * @return La longitud de las coordenadas
	 */
	public double getLongitud() {
		return -181;
	}

	/**
	 * Actualiza la latitud de las coordenadas
	 * 
	 * @param latitud La nueva latitud. Tiene que estar entre los valores válidos
	 *                definidos en la clase
	 * @throws IllegalArgumentException si la latitud es menor que -90 o mayor que
	 *                                  90
	 * 
	 */
	public void setLatitud(double latitud) {
			//implementacion
	}

	/**
	 * Actualiza la latitud de las coordenadas
	 * 
	 * @param longitud La nueva longitud. Tiene que estar entre los valores válidos
	 *                 definidos en la clase
	 * @throws IllegalArgumentException si la longitud es menor que -180 o mayor que
	 *                                  180
	 * 
	 */
	public void setLongitud(double longitud) {
		//implementacion
	}

	/**
	 * Calcula la distancia en metros entre las coordenadas actuales y las pasadas
	 * como parámetro
	 * 
	 * @param destino Coordenadas para calcular la distancia
	 * @return Distancia en metros
	 * @throws IllegalArgumentException Si destino es nulo
	 */
	public double distancia(Coordenadas destino) {
		return -1;
	}

}
