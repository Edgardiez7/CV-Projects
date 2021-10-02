/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.modelo;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

/**
 *
 * @author miris
 */
public class Empleado extends Usuario {

    private Date fechaInicio;
    private ArrayList<Rol> listaRol = new ArrayList<>();
    private ArrayList<VinculacionConLaEmpresa> listaVinculacion = new ArrayList<>();
    private ArrayList<Disponibilidad> listaDisponibilidad = new ArrayList<>();

    public Empleado(String file) throws ParseException {

        super(file);

        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(file));
        JsonObject jsonobject = reader.readObject();

        String dateStr = jsonobject.getString("FECHAINICIO");
        this.fechaInicio = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

        String f;
        Date fecha;
        //ROL
        JsonArray rolesFecha = jsonobject.getJsonArray("ROLESFECHA");
        JsonArray rolesNombre = jsonobject.getJsonArray("ROLESNOMBRE");
        for (int i = 0; i < rolesFecha.size(); i++) {
            TipoRol tr = TipoRol.valueOf(rolesNombre.getString(i));
            f = rolesFecha.getString(i);
            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(f);
            Rol rol = new Rol(fecha, tr);
            listaRol.add(rol);
        }

        //VINCULACION
        JsonArray vinculacionesFecha = jsonobject.getJsonArray("VINCULACIONESFECHA");
        JsonArray vinculacionesNombre = jsonobject.getJsonArray("VINCULACIONESNOMBRE");
        for (int i = 0; i < vinculacionesFecha.size(); i++) {
            TipoDeVinculacion tv = TipoDeVinculacion.valueOf(vinculacionesNombre.getString(i));
            f = vinculacionesFecha.getString(i);
            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(f);
            VinculacionConLaEmpresa vinculacion = new VinculacionConLaEmpresa(fecha, tv);
            listaVinculacion.add(vinculacion);
        }

        //DISPONIBILIDAD
        JsonArray jsonArrayComienzoD = jsonobject.getJsonArray("DISPONIBILIDADESINICIO");
        JsonArray jsonArrayFinalD = jsonobject.getJsonArray("DISPONIBILIDADESFINAL");
        JsonArray jsonArrayNombreD = jsonobject.getJsonArray("DISPONIBILIDADESNOMBRE");

        for (int i = 0; i < jsonArrayComienzoD.size(); i++) {
            TipoDeDisponibilidad td = TipoDeDisponibilidad.valueOf(jsonArrayNombreD.getString(i));
            f = jsonArrayComienzoD.getString(i);
            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(f);
            String f2 = jsonArrayFinalD.getString(i);
            Date fecha2 = new SimpleDateFormat("yyyy-MM-dd").parse(f2);
            Disponibilidad disponibilidad = new Disponibilidad(fecha, fecha2, td);
            listaDisponibilidad.add(disponibilidad);
        }

    }

    public Rol getRolActivo() {
        return listaRol.get(0);
    }

    public VinculacionConLaEmpresa getVinculacionActiva() {
        return listaVinculacion.get(0);
    }

    public Disponibilidad getDisponibilidadActiva() {
        return listaDisponibilidad.get(0);
    }
}
