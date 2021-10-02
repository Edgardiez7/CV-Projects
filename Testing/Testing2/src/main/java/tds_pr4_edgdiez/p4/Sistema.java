package tds_pr4_edgdiez.p4;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Clase que representa un Sistema de gestion de alquiler de bicis. Cada sistema
 * debe tener al menos una estacion.
 * 
 * El sistema de reserva debe permitir: - añadir nuevas estaciones - eliminar
 * estaciones, - obtener todas las estaciones del sistema - alquilar una bici
 * dado un punto de recogida (si es posible), - devolver una bici (si es
 * posible) - desactivar un punto de recogida (si es posible) - buscar las
 * estaciones cercanas (500m) a unas coordenadas dadas - obtener la distancia
 * entre dos estaciones - obtener el número de puntos de recogida de una
 * estación que estén en el estado indicado.
 * 
 * Para todas las operaciones que no se puedan realizar, el sistema lo deberá
 * notificar.
 * 
 * @author edgdiez
 *
 */

public class Sistema {

	private ArrayList<Estacion> arrayEstaciones;

	/**
	 * Constructor del Sistema de gestión de alquiler de bicis.
	 * 
	 * @param arrayEstaciones estaciones que pertenecen al sistema inicialmente
	 * @throws IllegalArgumentException si el array esta vacio
	 * @throws IllegalArgumentException si el array es null
	 * @throws IllegalStateException    si el array contiene estaciones con id
	 *                                  repetido
	 */
	public Sistema(ArrayList<Estacion> arrayEstaciones) throws IllegalArgumentException, IllegalStateException {

		if (arrayEstaciones == null)
			throw new IllegalArgumentException("El array Estaciones no puede ser null");
		if (arrayEstaciones.isEmpty())
			throw new IllegalArgumentException("El sistema debe contener al menos 1 estacion");
		if (estacionesIdRepetido(arrayEstaciones))
			throw new IllegalStateException("Hay dos o más estaciones con el mismo Id");
		this.arrayEstaciones = arrayEstaciones;
	}

	/**
	 * Getter del array de estaciones de un sistema
	 * 
	 * @return array arraylist con las estaciones del sistema
	 */
	public ArrayList<Estacion> getEstaciones() {
		return arrayEstaciones;
	}

	/**
	 * Añade una estacion al sistema
	 * 
	 * @param estacion estacion a añadir
	 * @throws IllegalArgumentException si la estacion es null
	 * @throws IllegalStateException    si el sistema ya contiene estaciones con el
	 *                                  mismo id
	 * @throws IllegalStateException    si hay id de pr iguales que id de estacion
	 *                                  en sistema
	 * @throws IllegalStateException    si hay id de pr repetidos en estacion
	 * @throws IllegalStateException    si hay id estacion a añadir igual que id pr
	 *                                  en sistema
	 */
	public void annadirEstacion(Estacion estacion) throws IllegalArgumentException, IllegalStateException {
		if (estacion == null) {
			throw new IllegalArgumentException("Estacion es null");
		}
		for (Estacion est : this.getEstaciones()) {
			if (est.getId().equals(estacion.getId())) {
				throw new IllegalStateException("ID estacion repetidos");
			}
			for (PuntoRecogida pr : estacion.getListaPuntos()) {
				if (pr.getId().equals(est.getId())) {
					throw new IllegalStateException("Id de pr a añadir igual a id de estacion del sistema");
				}
				for (PuntoRecogida pr2 : est.getListaPuntos()) {
					if (pr.getId().equals(pr2.getId())) {
						throw new IllegalStateException("ID de pr repetido en estacion");
					}
					if (pr2.getId().equals(estacion.getId())) {
						throw new IllegalStateException("ID estacion a añadir igual que el de un pr en sistema");
					}
				}
			}
		}
		this.getEstaciones().add(estacion);
	}

	/**
	 * Eliminar una estacion del sistema
	 * 
	 * @param estacion estacion a eliminar del sistema
	 * @throws IllegalArgumentException si la estacion es null
	 * @throws IllegalArgumentException si la estacion no pertenede al sistema
	 * @throws IllegalStateException    si el sistema se queda sin estaciones
	 */
	public void eliminarEstacion(Estacion estacion) throws IllegalArgumentException, IllegalStateException {
		if (estacion == null)
			throw new IllegalArgumentException("Estacion es null.");
		if (!this.getEstaciones().contains(estacion))
			throw new IllegalArgumentException("Estacion es null.");
		if (this.getEstaciones().size() == 1)
			throw new IllegalStateException("El sistema no se puede quedar sin estaciones");
		this.getEstaciones().remove(estacion);
	}

	/**
	 * Alquila una bici de un punto de recogida. Para que sea posible, el estado del
	 * pr debe ser OCUPADO
	 * 
	 * @param pr punto de recogida a alquilar
	 * @throws IllegalArgumentException si el pr es null
	 * @throws IllegalArgumentException si el pr no pertenece al sistema
	 * @throws IllegalStateException    si el pr no esta ocupado
	 */
	public void alquilarBici(PuntoRecogida pr) throws IllegalArgumentException, IllegalStateException {
		if (pr == null)
			throw new IllegalArgumentException("PuntoRecogida es null.");
		if (!pr.getEstado().equals(Estado.OCUPADO))
			throw new IllegalStateException("El pr de recogida no esta ocupado");
		int indiceEstacion = prEnSistema(pr, this);
		if (indiceEstacion == -1)
			throw new IllegalArgumentException("El punto de recogida no pertenece al sistema.");
		int indPr = this.getEstaciones().get(indiceEstacion).getListaPuntos().indexOf(pr);
		this.getEstaciones().get(indiceEstacion).getListaPuntos().get(indPr).setEstado(Estado.LIBRE);
	}

	/**
	 * Devuelve una bici a un punto de recogida. Para que sea posible, el estado del
	 * pr debe ser LIBRE
	 * 
	 * @param pr punto de recogida en el que se va a devolver la bici
	 * @throws IllegalArgumentException si el pr es null
	 * @throws IllegalArgumentException si el pr no pertenece al sistema
	 * @throws IllegalStateException    si el pr no esta libre
	 */
	public void devolverBici(PuntoRecogida pr) throws IllegalArgumentException, IllegalStateException {
		if (pr == null)
			throw new IllegalArgumentException("PuntoRecogida es null");
		if (!pr.getEstado().equals(Estado.LIBRE))
			throw new IllegalStateException("El pr de recogida no esta libre");
		int indiceEstacion = prEnSistema(pr, this);
		if (indiceEstacion == -1)
			throw new IllegalArgumentException("El punto de recogida no pertenece al sistema");

		int indPr = this.getEstaciones().get(indiceEstacion).getListaPuntos().indexOf(pr);
		this.getEstaciones().get(indiceEstacion).getListaPuntos().get(indPr).setEstado(Estado.OCUPADO);
	}

	/**
	 * Desactiva un punto de recogida. Para ello el estado debe ser LIBRE
	 * 
	 * @param pr punto de recogida a desactivar
	 * @throws IllegalArgumentException si el pr es null
	 * @throws IllegalStateException    si el pr ya esta inactivo
	 * @throws IllegalStateException    si el pr esta ocupado
	 * @throws IllegalArgumentException si el pr no pertenece al sistema
	 */
	public void desactivarPuntoRecogida(PuntoRecogida pr) throws IllegalArgumentException, IllegalStateException {
		if (pr == null)
			throw new IllegalArgumentException("Punto Recogida es null.");
		if (pr.getEstado().equals(Estado.OCUPADO))
			throw new IllegalStateException("El pr de recogida esta ocupado");
		if (pr.getEstado().equals(Estado.INACTIVO))
			throw new IllegalStateException("El pr de recogida ya esta inactivo");
		int indiceEstacion = prEnSistema(pr, this);
		if (indiceEstacion == -1)
			throw new IllegalArgumentException("El punto de recogida noo pertenece al sistema");
		int indPr = this.getEstaciones().get(indiceEstacion).getListaPuntos().indexOf(pr);
		this.getEstaciones().get(indiceEstacion).getListaPuntos().get(indPr).setEstado(Estado.INACTIVO);
	}

	/**
	 * Busca las estaciones a menos de 500m de las coordenadas pasadas como
	 * parametro
	 * 
	 * @param coords coordenadas centro
	 * @return arraylist con las estaciones a menos de 500m de las coords dadas
	 * @throws IllegalArgumentException si coords es null
	 * @throws IllegalStateException    si no hay estaciones cerca
	 */
	public ArrayList<Estacion> estacionesCercanas(Coordenadas coords)
			throws IllegalArgumentException, IllegalStateException {
		if (coords == null)
			throw new IllegalArgumentException("Coords es null");
		ArrayList<Estacion> estCerca = new ArrayList<>();
		for (Estacion est : this.getEstaciones()) {
			if (est.getCoordenadas().distancia(coords) <= 500) {
				estCerca.add(est);
			}
		}
		if (estCerca.isEmpty())
			throw new IllegalStateException("Ninguna estacion cerca");
		else
			return estCerca;
	}

	/**
	 * Calcula y devuelve la distancia entre dos estaciones en metros.
	 * 
	 * @param est1 estacion 1
	 * @param est2 estacion 2
	 * @return distancia entre dos estaciones en metros
	 * @throws IllegalArgumentException si est 1 o est 2 es null
	 * @throws IllegalArgumentException si est1 o est2 no pertenecen al sistema
	 */
	public double distanciaEntreEstaciones(Estacion est1, Estacion est2) throws IllegalArgumentException {
		if (est1 == null || est2 == null)
			throw new IllegalArgumentException("Null param");
		if (!this.getEstaciones().contains(est1) || !this.getEstaciones().contains(est2))
			throw new IllegalArgumentException("Estacion no pertenece a sistema");
		return (est1.getCoordenadas().distancia(est2.getCoordenadas()));
	}

	/**
	 * Calcula y devuelve el numero de puntos de recogida de una estacion en el
	 * estado determinado.
	 * 
	 * @param est    estacion
	 * @param estado estado a buscar
	 * @return numPuntos numero de pr de una estacion en el estado especificado
	 * @throws IllegalArgumentException si est es null
	 * @throws IllegalArgumentException si est no pertenece al sistema
	 */
	public int numeroPuntosRecogidaEstacionEnEstado(Estacion est, Estado estado) throws IllegalArgumentException {
		if (est == null)
			throw new IllegalArgumentException("Est es null");
		if (!this.getEstaciones().contains(est))
			throw new IllegalArgumentException("Est no pertenece al sistema");
		return est.getPuntosDeRecogida(estado).size();
	}

	/**
	 * Busca una estacion en el sistema por su id
	 * 
	 * @param id identificador de la estacion
	 * @return estacion que coincide id
	 * @throws IllegalArgumentException si id es null
	 * @throws IllegalArgumentException si id es vacio
	 * @throws IllegalArgumentException si est no existe en smasteristema
	 */
	public Estacion buscarEstacion(String id) throws IllegalArgumentException {
		if (id == null)
			throw new IllegalArgumentException("Id null");
		if (id.equals(""))
			throw new IllegalArgumentException("Id vacio");
		for (Estacion est : this.getEstaciones()) {
			if (est.getId().equals(id))
				return est;
		}
		throw new IllegalArgumentException("Id no pertenece al sistema");
	}

	/**
	 * Comprueba si hay dos o más estaciones con el mismo id en un arraylist de
	 * estaciones. Para cada id, comprueba si todos los ids con indice posterior en
	 * el array son iguales.
	 * 
	 * @param array array a comprobar
	 * @return repetidas true si hay 2 o mas estaciones con mismo id en el array
	 */
	private boolean estacionesIdRepetido(ArrayList<Estacion> array) {
		boolean repetidas = false;
		for (int i = 0; i < array.size(); i++) {
			String id = array.get(i).getId();
			for (int j = i + 1; j < array.size(); j++) {
				String id2 = array.get(j).getId();
				if (id.equals(id2)) {
					repetidas = true;
					break;
				}
			}
		}
		return repetidas;
	}

	/**
	 * Devuelve el índice de la estacion que contiene el punto de recogida en el
	 * array de un sistema Si no se encuentra el pr en el sistema devuelve -1
	 * 
	 * @param pr punto de recogida
	 * @param s  sistema
	 * @return pertenece -1 si no pertenece, o el indice de la estacion que lo
	 *         contiene
	 */
	private int prEnSistema(PuntoRecogida pr, Sistema s) {
		int pertenece = -1;
		for (int i = 0; i < s.getEstaciones().size(); i++) {
			if (s.getEstaciones().get(i).getListaPuntos().contains(pr)) {
				pertenece = i;
				break;
			}
		}
		return pertenece;
	}

	/**
	 * Genera un xml con la informacion del sistema, de acuerdo a la estructura de
	 * la siguiente dtd
	 * https://gitlab.inf.uva.es/edgdiez/tds_practica4_edgdiez_dtd/-/raw/master/pr4-edgdiez-DTD.dtd
	 * 
	 * @param sistema     sistema con la informacion
	 * @param pathFichero ruta del fichero a generar
	 * @throws ParserConfigurationException excepcion
	 * @throws TransformerException         excepcion
	 * @throws ParserConfigurationException excepcion
	 * @throws IllegalArgumentException     si el path es null
	 * @throws IllegalArgumentException     si el path esta vacio
	 * @throws IllegalArgumentException     si el sistema es null
	 */
	public static void sistemaToXML(Sistema sistema, String pathFichero)
			throws ParserConfigurationException, TransformerException {

		if (sistema == null)
			throw new IllegalArgumentException("sistema null");
		if (pathFichero == null)
			throw new IllegalArgumentException("El Path no puede ser nulo");
		if (pathFichero.equals(""))
			throw new IllegalArgumentException("El Path no puede estar vacio");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		factory.setValidating(true);
		Document doc = builder.newDocument();

		Element root = doc.createElement("sistema");
		doc.appendChild(root);

		for (Estacion est : sistema.getEstaciones()) {
			root.appendChild(createEstacion(doc, est));
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();

		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		DOMSource source = new DOMSource(doc);

		File myFile = new File(pathFichero + ".xml");

		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);

		transf.transform(source, console);
		transf.transform(source, file);
	}

	/**
	 * Crea un nodo estacion
	 * 
	 * @param doc      Document
	 * @param estacion
	 * @return est nodo estacion
	 */
	private static Node createEstacion(Document doc, Estacion estacion) {
		Element est = doc.createElement("estacion");
		est.setAttribute("idEst", estacion.getId());
		est.appendChild(createCoordenadas(doc, estacion.getCoordenadas()));
		for (PuntoRecogida pr : estacion.getListaPuntos()) {
			est.appendChild(createPuntoRecogida(doc, pr));
		}
		return est;
	}

	/**
	 * Crea un nodo punto recogida
	 * 
	 * @param doc Document
	 * @param pr  punto recogida
	 * @return ptoRec nodo pr
	 */
	private static Node createPuntoRecogida(Document doc, PuntoRecogida pr) {
		Element ptoRec = doc.createElement("puntoRecogida");
		ptoRec.setAttribute("idPR", pr.getId());
		ptoRec.setAttribute("estado", pr.getEstado().toString());
		return ptoRec;
	}

	/**
	 * Crea un nodo coordenadas
	 * 
	 * @param doc    Document
	 * @param coords coordenadas
	 * @return coordenadas nodo coordenadas
	 */
	private static Node createCoordenadas(Document doc, Coordenadas coords) {
		Element coordenadas = doc.createElement("coordenadas");
		coordenadas.appendChild(createLatitud(doc, coords.getLatitud()));
		coordenadas.appendChild(createLongitud(doc, coords.getLongitud()));
		return coordenadas;
	}

	/**
	 * Crea un nodo latitud
	 * 
	 * @param doc Document
	 * @param lat latitud
	 * @return lati nodo latitud
	 */
	private static Node createLatitud(Document doc, Double lat) {
		Element lati = doc.createElement("latitud");
		lati.appendChild(doc.createTextNode(lat + ""));
		return lati;
	}

	/**
	 * Crea un nodo longitud
	 * 
	 * @param doc   Document
	 * @param longi longitud
	 * @return longit nodo longitud
	 */
	private static Node createLongitud(Document doc, Double longi) {
		Element longit = doc.createElement("longitud");
		longit.appendChild(doc.createTextNode(longi + ""));
		return longit;
	}

	/**
	 * Transforma un sistema en xml and un sistema Java
	 * 
	 * @param pathFichero string ruta del xml
	 * @returns sistema sistema del xml en java
	 * @throws IllegalArgumentException     si el path es null
	 * @throws IllegalArgumentException     si el path esta vacio
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static Sistema xmlToObject(String pathFichero)
			throws IllegalArgumentException, ParserConfigurationException, SAXException, IOException {
		if (pathFichero == null)
			throw new IllegalArgumentException("El Path no puede ser nulo");
		if (pathFichero.equals(""))
			throw new IllegalArgumentException("El Path no puede estar vacio");
		File file = new File(pathFichero);
		Sistema sistema = null;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		Element root = doc.getDocumentElement();
		ArrayList<Estacion> arrayEstaciones = new ArrayList<>();
		NodeList listaEstaciones = root.getElementsByTagName("estacion");
		for (int temp = 0; temp < listaEstaciones.getLength(); temp++) { // para cada estacion
			Coordenadas coords;
			double lat = 0;
			double longi = 0;
			String estId = "";
			ArrayList<PuntoRecogida> arrayPr = new ArrayList<>();
			Node node = listaEstaciones.item(temp);

			Element eElement = (Element) node;
			estId = eElement.getAttribute("idEst");
			NodeList coordenadas = eElement.getElementsByTagName("coordenadas");
			Node nodeC = coordenadas.item(0);
			Element cElement = (Element) nodeC;
			longi = Double.parseDouble(cElement.getElementsByTagName("longitud").item(0).getTextContent());
			lat = Double.parseDouble(cElement.getElementsByTagName("latitud").item(0).getTextContent());

			NodeList puntosRecogida = eElement.getElementsByTagName("puntoRecogida");
			for (int pr = 0; pr < puntosRecogida.getLength(); pr++) { // para cada pr
				Node npr = puntosRecogida.item(pr);
				String idPR = "";
				Estado estado;
				Element prElement = (Element) npr;
				idPR = prElement.getAttribute("idPR");
				estado = asignarEstado(prElement.getAttribute("estado"));
				PuntoRecogida puntoRec = new PuntoRecogida(idPR, estado);
				arrayPr.add(puntoRec);

			}

			coords = new Coordenadas(lat, longi);
			Estacion estacion = new Estacion(estId, arrayPr, coords);
			arrayEstaciones.add(estacion);
		}
		sistema = new Sistema(arrayEstaciones);
		return sistema;

	}

	/**
	 * Parser de string a Estado
	 * 
	 * @param est string estado
	 * @return estado Estado
	 */
	private static Estado asignarEstado(String est) {
		Estado estado;
		if (est.equals("INACTIVO"))
			estado = Estado.INACTIVO;
		else if (est.equals("LIBRE"))
			estado = Estado.LIBRE;
		else
			estado = Estado.OCUPADO;
		return estado;
	}

}
