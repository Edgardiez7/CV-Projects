package tds.p3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * La clase GestorCorreo manejara los correos electronicos de nuestro sistema.
 * Implementara la funcionalidad para:
 * 
 * - Crear gestores vacios o a partir de una lista de correos.
 * 
 * - Añadir correos a un gestor.
 * 
 * - Comprobar que no se añaden correos repetidos.
 *   Un correo se considera repetido cuando coinciden fecha hora asunto y categoria
 * 
 * - Contar el numero de correos en el gestor.
 * 
 * - Saber la fecha de los correos mas recientes y mas antiguos.
 * 
 * - Obtener una lista de correos ordenados por fecha y hora
 * 
 * - Obtener una lista de correos ordenados por categoria: enviados, recibidos,
 * borrador y papelera. Y dentro de cada categoría ordenados por orden
 * cronologico.
 * 
 * - Obtener subgestores por fecha, categoria, de una categoria en una fecha o
 * de una categoria en un intervalo de fechas.
 * 
 * Cada gestor de correo será implementado como un ArrayList de correos.
 * 
 * @author edgdiez Edgar Diez Alonso
 * 
 */
public class GestorCorreo{

    private ArrayList<Correo> listaCorreos;

    /**
     * Constructor por Defecto de GestorCorreo.
     * Crea un ArrayList de correos vacio.
     */
    public GestorCorreo(){
        listaCorreos = new ArrayList<>();
    }

    /**
     * Constructor de GestorCorreo a partir de un ArrayList de correos.
     * Debe comprobar que no se añaden correos repetidos.
     * Un correo se considera repetido cuando coinciden fecha hora asunto y categoria.
     * Crea un ArrayList de correos a partir del dado.
     * @param listaCorreos arralist de correos a añadir al gestor
     * @throws IllegalStateException si hay dos o mas correos repetidos en el arraylist
     * @throws IllegalArgumentException si listaCorreos es null
     */
    public GestorCorreo(ArrayList<Correo> listaCorreos) throws IllegalArgumentException, IllegalStateException{        
        if(listaCorreos == null) throw new IllegalArgumentException();
        if(correosRepetidos(listaCorreos)) throw new IllegalStateException();  
        this.listaCorreos = listaCorreos;
    }

    /**
     * Devuelve la lista de correos del gestor.
     * @return Lista de Correos del gestor
     */
    public ArrayList<Correo> getListaCorreos(){
        return listaCorreos;
    }

    /**
     * Añade un correo al ArrayList del gestor.
     * Debe comprobar que no se añaden correos repetidos.
     * Un correo se considera repetido cuando coinciden fecha hora asunto y categoria.
     * @param correo correo a añadir al gestor
     * @throws IllegalStateException si hay dos o mas correos repetidos en el arraylist
     * @throws IllegalArgumentException si correo es null
     */
    public void añadirCorreo(Correo correo) throws IllegalArgumentException, IllegalStateException{
        if(correo == null) throw new IllegalArgumentException();
        if(siEsta(correo)) throw new IllegalStateException();        
        getListaCorreos().add(correo);
    }

    /**
     * Devuelve el numero de correos en un gestor
     * @return Numero de correos en un gestor
     */
    public int getNumeroCorreos(){
        return getListaCorreos().size();
    }

    /**
     * Devuelve un arraylist con los correos con fecha mas antigua del gestor.
     * Si hay varios correos con la misma fecha, los ordena por hora, y a igual
     * hora los coloca en el mismo orden que presentaban en el gestor.
     * @return Correos mas antiguos por fecha del arraylist
     * @throws IllegalStateException si el gestor no contiene correos
     */
    public ArrayList<Correo> getCorreosMasAntiguos() throws IllegalStateException{     
        try {
        	ArrayList<Correo> correosOrdenados = getListaOrdenadaCorreos();
        	return escogerCorreosFechaAntigua(correosOrdenados); 
    	}catch(IllegalStateException ise){
			throw new IllegalStateException();
		}
    }    

    /**
     * Devuelve un arraylist con los correos con fecha mas reciente del gestor. Si
     * hay varios correos con la misma fecha, los ordena por hora, y a igual hora
     * los coloca en el mismo orden que presentaban en el gestor.
     * 
     * @return Correos mas recientes por fecha del arraylist
     * @throws IllegalStateException si el gestor no contiene correos
     */
    public ArrayList<Correo> getCorreosMasRecientes() throws IllegalStateException{
    	try {
    		ArrayList<Correo> correosOrdenados = getListaOrdenadaCorreos();
        	return escogerCorreosFechaReciente(correosOrdenados);
    	}catch(IllegalStateException ise){
			throw new IllegalStateException();
		}
    }

    /**
     * Devuelve un arraylist con los correos del gestor ordenados por fecha.
     * Si hay varios correos con la misma fecha, los ordena por hora, y a igual
     * hora los coloca en el mismo orden que presentaban en el gestor.
     * @return Lista de Correos ordenada por orden cronologico
     * @throws IllegalStateException si el gestor no contiene correos
     */
    public ArrayList<Correo> getListaOrdenadaCorreos() throws IllegalStateException{
        if(getListaCorreos().isEmpty()) throw new IllegalStateException();   
        ArrayList<Correo> correosOrdenados = ordenarPorFecha(getListaCorreos());
        return correosOrdenados;
    }

    /**
     * Devuelve un arraylist con los correos del gestor ordenados por categoría.
     * El orden es enviados -> recibidos -> borrador -> papelera.
     * Dentro de cada categoría los ordenamos por orden cronologico. Si hay varios correos
     * con la misma fecha, los ordena por hora, y a igual
     * hora los coloca en el mismo orden que presentaban en el gestor.
     * @return arraylist de los correos ordenados por categoria
     * @throws IllegalStateException si el gestor esta vacio
     */
    public ArrayList<Correo> getListaOrdenadaPorCategoria() throws IllegalStateException{
    	try {
        	ArrayList<Correo> correosOrdenados = getListaOrdenadaCorreos();
        	ArrayList<ArrayList<Correo>> correosOrdenadosPorCategoria = getCorreosPorCategorias(correosOrdenados);
            return pasarAUnaListaConCategoriaOrdenada(correosOrdenadosPorCategoria);
    	}catch(IllegalStateException ise){
    		throw new IllegalStateException();
    	}
    }

    /**
     * Devuelve un arraylist con los correos del gestor con una fecha concreta.
     * Si hay varios correos con una misma fecha los ordenamos por orden cronologico. 
     * Los ordena por horas, y a igual hora los colocamos en el mismo orden que presentaban en el gestor.  
     * @param fecha fecha a buscar
     * @return arraylist de los correos de una fecha concreta.
     * @throws IllegalStateException si el gestor esta vacio
     * @throws IllegalStateException si la fecha es null
     * @throws IllegalStateException si la fecha es posterior a la actual
     * @throws IllegalStateException si no hay ningun correo de esa fecha
     */
    public ArrayList<Correo> getCorreosDeUnaFecha(LocalDate fecha) throws IllegalStateException{
        if(fecha == null ) throw new IllegalStateException();
        if(fecha.isAfter(LocalDate.now()))  throw new IllegalStateException();
        try {
        	ArrayList<Correo> correosOrdenados = getListaOrdenadaCorreos();
        	ArrayList<Correo> correosDeUnaFecha = getCorreosDeUnaFechasAPartirDeUnaLista(fecha, correosOrdenados);
        	if(correosDeUnaFecha.isEmpty()) throw new IllegalStateException();
        	return correosDeUnaFecha;
        }catch(IllegalStateException ise) {
        	throw new IllegalStateException();
        }
        
    }

    /**
     * Devuelve un arraylist con los correos del gestor contenidos entre dos fechas ordenados en orden cronologico.
     * Si hay varios correos con una misma fecha los ordenamos por orden cronologico.
     * Si hay varios correos con la misma fecha, los ordena por hora, y a igual
     * hora los coloca en el mismo orden que presentaban en el gestor.
     * @param fechaAnterior fecha inicial
     * @param fechaPosterior fecha final
     * @return arraylist de los correos de contenidos entre dos fechas
     * @throws IllegalStateException si el gestor esta vacio
     * @throws IllegalArgumentException si la primera fecha es posterior a la segunda
     * @throws IllegalStateException si cualquiera de las dos fechas es null
     * @throws IllegalStateException si cualquiera de las dos fechas es posterior a la actual
     * @throws IllegalStateException si no hay ningun correo entre esas dos fechas
     */
    public ArrayList<Correo> getCorreosEntreDosFechas(LocalDate fechaAnterior, LocalDate fechaPosterior) throws IllegalStateException{
        if(fechaAnterior == null || fechaPosterior == null) throw new IllegalStateException();
        if(fechaAnterior.isAfter(fechaPosterior)) throw new IllegalArgumentException();
        if(fechaAnterior.isAfter(LocalDate.now()) || fechaPosterior.isAfter(LocalDate.now()))  throw new IllegalStateException();
        try {
        	ArrayList<Correo> correosOrdenados = getListaOrdenadaCorreos();
        	ArrayList<Correo> arrayEntreDosFechas = getCorreosEntreDosFechasAPartirDeUnaLista(fechaAnterior, fechaPosterior, correosOrdenados);
            if (arrayEntreDosFechas.isEmpty())  throw new IllegalStateException();
            return arrayEntreDosFechas;
        }catch(IllegalStateException ise) {
        	throw new IllegalStateException();
        }
        
    }

    /**
     * Devuelve un arraylist con los correos del gestor de una categoria en orden cronologico.
     * Si hay varios correos con una misma fecha los ordenamos por orden cronologico.
     * Si hay varios correos con la misma fecha, los ordena por hora, y a igual
     * hora los coloca en el mismo orden que presentaban en el gestor.
     * @param categoria categoria del correo
     * @return arraylist de los correos de una categoria
     * @throws IllegalStateException si el gestor esta vacio
     * @throws IllegalStateException si no hay ningun correo de esa categoria
     * 
     */
    public ArrayList<Correo> getCorreosDeUnaCategoria(Categoria categoria) throws IllegalStateException{
        if(categoria == null) throw new IllegalStateException();
    	try {
        	ArrayList<Correo> correosOrdenados = getListaOrdenadaCorreos();
            ArrayList<ArrayList<Correo>> correosOrdenadosPorCategoria = getCorreosPorCategorias(correosOrdenados);
            ArrayList<Correo> arrayCorreosDeUnaCategoria = correosOrdenadosPorCategoria.get(categoria.getPrioridad());
            if (arrayCorreosDeUnaCategoria.isEmpty())  throw new IllegalStateException();
            return arrayCorreosDeUnaCategoria;
        }catch(IllegalStateException ise) {
        	throw new IllegalStateException();
        }
    }

    /**
     * Devuelve un arraylist con los correos del gestor de una categoria contenidos entre dos fechas.
     * Si hay varios correos con una misma fecha los ordenamos por orden cronologico.
     * Si hay varios correos con la misma fecha, los ordena por hora, y a igual
     * hora los coloca en el mismo orden que presentaban en el gestor.
     * @param fechaAnterior fecha inicial
     * @param fechaPosterior fecha final
     * @param categoria categoria del correo
     * @return arraylist con los correos de una categoria entre dos fechas
     * @throws IllegalStateException si el gestor esta vacio
     * @throws IllegalArgumentException si la fechaAnterior es posterior a la fechaPosterior
     * @throws IllegalStateException si cualquiera de la dos fechas es null
     * @throws IllegalStateException si cualquiera de la dos fechas es posterior a la actual
     * @throws IllegalStateException si la categoria es null
     * @throws IllegalStateException si no hay ningun correo de esa categoria entre esas dos fechas
     * 
     */
    public ArrayList<Correo> getCorreosEntreDosFechasDeUnaCategoria(LocalDate fechaAnterior, LocalDate fechaPosterior, Categoria categoria) throws IllegalStateException{
    	 if(fechaAnterior == null || fechaPosterior == null || categoria == null) throw new IllegalStateException();
         if(fechaAnterior.isAfter(fechaPosterior)) throw new IllegalArgumentException();
         if(fechaAnterior.isAfter(LocalDate.now()) || fechaPosterior.isAfter(LocalDate.now()))  throw new IllegalStateException();
    	try {
        	ArrayList<Correo> correosDeUnaCategoria = getCorreosDeUnaCategoria(categoria);
        	return getCorreosEntreDosFechasAPartirDeUnaLista(fechaAnterior, fechaPosterior, correosDeUnaCategoria);

        }catch(IllegalStateException ise) {
        	throw new IllegalStateException();
        }
    }

    /**
     * Devuelve un arraylist con los correos del gestor de una categoria de una fecha concreta
     * Si hay varios correos con una misma fecha los ordenamos por orden cronologico.
     * Si hay varios correos con la misma fecha, los ordena por hora, y a igual
     * hora los coloca en el mismo orden que presentaban en el gestor.
     * @param fecha fecha a buscar
     * @param categoria categoria de los correos
     * @return arraylist con los correos de una categoria y un dia concreto
     * @throws IllegalStateException si el gestor esta vacio
     * @throws IllegalStateException si la fecha es null
     * @throws IllegalStateException si la fecha es posterior a la actual
     * @throws IllegalStateException si la categoria es null
     * @throws IllegalStateException si no hay ningun correo de esa categoria con esa fecha 
     */
    public ArrayList<Correo> getCorreosDeUnaFechaDeUnaCategoria(LocalDate fecha, Categoria categoria) throws IllegalStateException{
    	if(fecha == null || categoria == null) throw new IllegalStateException();
    	if(fecha.isAfter(LocalDate.now())) throw new IllegalStateException();
    	try {
    		ArrayList<Correo> correosDeUnaCategoria = getCorreosDeUnaCategoria(categoria);
            ArrayList<Correo> correosDeUnaFechaYCategoria = getCorreosDeUnaFechasAPartirDeUnaLista(fecha, correosDeUnaCategoria);
            if(correosDeUnaFechaYCategoria.isEmpty()) throw new IllegalStateException();
            return correosDeUnaFechaYCategoria;
    	}catch(IllegalStateException ise) {
        	throw new IllegalStateException();
        }
    }

    /**
     * Comprueba si hay dos correos iguales en un arraylist de correos.
     * @param listaCorreos arraylist de correos a comprobar
     * @return true si hay al menos 2 correos iguales en un arraylist
     */
    private boolean correosRepetidos(ArrayList<Correo> listaCorreos){
    	for(int i = 0; i < listaCorreos.size(); i++){
            for (int j = i+1; j < listaCorreos.size(); j++){
                if(listaCorreos.get(i).equals(listaCorreos.get(j))){
                    return true;
                }
            }            
        }
        return false;

    }

    /**
     * Ordena un arraylist de correos por orden cronologico. A igual fecha
     * ordena por hora y a igual fecha y hora deja los correos en el orden
     * inicial.
     * @param listaCorreos lista de correos a ordenar
     * @return correosMasAntiguos correos ordenados por fecha y hora
     */
    private ArrayList<Correo> ordenarPorFecha(ArrayList<Correo> listaCorreos){
        ArrayList<Correo> correosMasAntiguos = new ArrayList<>(getListaCorreos());
        Collections.sort(correosMasAntiguos);
        return correosMasAntiguos;
    }

    /**
     * Escoge los correos con la fecha mas antigua de un arraylist de correos
     * @param correosOrdenados array de correos a procesar
     * @return correosUltimaFecha array de correos con la fecha mas antigua, 
     * ordenados por hora.
     */
    private ArrayList<Correo> escogerCorreosFechaAntigua(ArrayList<Correo> correosOrdenados) {
        LocalDate fecha = correosOrdenados.get(0).getFecha();
        ArrayList<Correo> correosUltimaFecha = new ArrayList<>();
        for (int i = 0; i < correosOrdenados.size(); i++){
            if (correosOrdenados.get(i).getFecha().equals(fecha)){
                correosUltimaFecha.add(correosOrdenados.get(i));
            }
            else{
                break;
            }
        }
        return correosUltimaFecha;
    }

    /**
     * Escoge los correos con la fecha mas reciente de un arraylist de correos
     * @param correosOrdenados array de correos a procesar
     * @return correosFechaReciente array de correos con la fecha mas antigua, 
     * ordenados por hora.
     */
    private ArrayList<Correo> escogerCorreosFechaReciente(ArrayList<Correo> correosOrdenados) {
        LocalDate fecha = correosOrdenados.get(correosOrdenados.size()-1).getFecha();
        ArrayList<Correo> correosFechaReciente = new ArrayList<>();
        for (int i = correosOrdenados.size()-1; i >= 0 ; i--){
            if (correosOrdenados.get(i).getFecha().equals(fecha)){
                correosFechaReciente.add(correosOrdenados.get(i));
            }
            else{
                break;
            }
        }
        return correosFechaReciente;
    }
    
    /**
     * Obtiene una lista de listas de correos donde cada lista de correos es de una categoria
     * @param correosOrdenados array de correo a procesar
     * @return correosPorCategoria arrays de correos indexado por categoria
     */
    private ArrayList<ArrayList<Correo>> getCorreosPorCategorias(ArrayList<Correo> correosOrdenados) {
    	ArrayList<Correo> correosDeUnaCategoria;
    	Correo c;
    	ArrayList<ArrayList<Correo>> correosPorCategoria = new ArrayList<>();
    	for(int i = 0; i < Categoria.values().length; i++) correosPorCategoria.add(new ArrayList<>());
    	for(int i = 0; i < correosOrdenados.size(); i++) {
    		c = correosOrdenados.get(i);
    		correosDeUnaCategoria = correosPorCategoria.get(c.getCategoria().getPrioridad());
    		correosDeUnaCategoria.add(c);
    	}
    	return correosPorCategoria;
    }
    
    /**
     * Obtiene una lista unica Ordenada por categorias a partir de una lista de listas de correos
     * indexadas por Categoria
     * @param listasCorreosPorCategorias lista de listas de correos indexaas por categoria
     * @return lista con los correos ordenados por categoria en una unica lista
     */
    private ArrayList<Correo> pasarAUnaListaConCategoriaOrdenada(ArrayList<ArrayList<Correo>> listasCorreosPorCategorias){
    	ArrayList<Correo> correosOrdenadosPorCategoria = new ArrayList<>();
    	ArrayList<Correo> listaDeUnaCategoria;
    	for(int i = 0; i < listasCorreosPorCategorias.size(); i++) {
    		listaDeUnaCategoria = listasCorreosPorCategorias.get(i);
    		correosOrdenadosPorCategoria.addAll(listaDeUnaCategoria);
    	}
    	return correosOrdenadosPorCategoria;
    }
    
    /**
     * Devuelve si el correo existe en el Gestor
     * @param correo correo a comparar
     * @return true si existe el correo y false si no existe en el Gestor
     */
    private boolean siEsta(Correo correo) {
    	for(Correo c: getListaCorreos()) if(c.equals(correo)) return true;
    	return false;
    }
    
    /**
     * Obtiene la lista de correos a partir y las fechas ya validadas y una
     * lista de correos que debe de estar ordenada, los correos deben estar
     * entre la dos fechas
     * @param fechaAnterior fecha inicial
     * @param fechaPosterior fecha final
     * @param correosOrdenados lista de correos ordenada de donde se selecciona los correos entre las fechas
     * @return lista de correos entre dos fechas
     */
    private ArrayList<Correo> getCorreosEntreDosFechasAPartirDeUnaLista(LocalDate fechaAnterior, LocalDate fechaPosterior, ArrayList<Correo> correosOrdenados){
    	ArrayList<Correo> arrayEntreDosFechas = new ArrayList<>();
    	Correo correo; 
    	for(int i = 0; i < correosOrdenados.size(); i++){
        	correo = correosOrdenados.get(i);
            if((correo.getFecha().isAfter(fechaAnterior) || correo.getFecha().equals(fechaAnterior))
              && (correo.getFecha().isBefore(fechaPosterior) || correo.getFecha().equals(fechaPosterior))){
                arrayEntreDosFechas.add(correo);
            }
        }
		return arrayEntreDosFechas;
    }
    
    /**
     * Obtiene la lista de correos de una fecha a partir de una lista de correos
     * que debe de estar ordenada
     * @param fecha fecha de los correos que hay que seleccionar
     * @param correosOrdenados lista de correos que hay que procesar
     * @return lista de correos de una fecha
     */
    private ArrayList<Correo> getCorreosDeUnaFechasAPartirDeUnaLista(LocalDate fecha, ArrayList<Correo> correosOrdenados){
	    ArrayList<Correo> arraysFechaConcreta = new ArrayList<>();
		Correo correo; 
	    for(int i = 0; i < correosOrdenados.size(); i++){
	    	correo = correosOrdenados.get(i);
	        if(correo.getFecha().equals(fecha)){
	            arraysFechaConcreta.add(correo);
	        }
	    }
	    return arraysFechaConcreta;
    }
}