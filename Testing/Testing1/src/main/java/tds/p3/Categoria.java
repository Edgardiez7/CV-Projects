package tds.p3;

/**
 * Enumeracion con todas las posibles categorias 
 * de un correo.
 * Estas pueden ser:
 * 		- Enviado
 * 		- Recibido
 * 		- Borrador
 * 		- Papelera
 * 
 * @author edgdiez Edgar Diez Alonso
 *
 */
public enum Categoria {
	ENVIADO(0),
	RECIBIDO(1),
	BORRADOR(2), 
	PAPELERA(3);
	
	private int prioridad;
	
	Categoria(int prioridad){
		this.prioridad = prioridad;
	}
	
	public int getPrioridad() {
		return prioridad;
	}
}
