package modelo;

import java.sql.Date;

/**
 *
 * @author User
 */
public class HistoricoCompra {

    private int id;
    private int idAnuncio;
    private Date fechaCompra;
    private String dni;

    public HistoricoCompra(int idAnuncio, String dni) {
        this.idAnuncio = idAnuncio;
        this.dni = dni;  
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

}
