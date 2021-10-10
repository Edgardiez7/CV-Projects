package com.uva.users.controller;

import java.util.List;

import com.uva.users.exception.BodegaException;
import com.uva.users.exception.VinoConRelacionException;
import com.uva.users.exception.VinoException;
import com.uva.users.model.Bodega;
import com.uva.users.model.Vino;
import com.uva.users.model.VinoConRelacion;
import com.uva.users.repository.BodegaRepository;
import com.uva.users.repository.VinoConRelacionRepository;
import com.uva.users.repository.VinoRepository;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TiendaVinos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EjemploRest {

    private final VinoRepository repository;
    private final VinoConRelacionRepository repository2;
    private final BodegaRepository repository3;

    EjemploRest(VinoRepository repository, VinoConRelacionRepository repository2, BodegaRepository repository3) {
        this.repository = repository;
        this.repository2 = repository2;
        this.repository3 = repository3;
    }

    /*
     * {"nombreComercial" : "Vino1", "denominacion" : "Ribera", "categoria" :
     * "Joven", "precio" : 8.2, "bodega_id" : 2 }
     */
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String newVino(@RequestBody Vino newVino) {
        try {
            repository.save(newVino);
            return "Nuevo registro creado con id " + newVino.getId();
        } catch (Exception e) {
            // alternativa
            // throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Error al
            // crear el nuevo registro.");
            throw new VinoException("Error al crear el nuevo registro");
        }
    }

    @GetMapping()
    public List<Vino> getVinos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Vino getVinoConId(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new VinoException("Sin resultado"));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String putVinos(@RequestBody Vino body, @PathVariable Integer id) {
        Vino vino = repository.findById(id).orElseThrow(() -> new VinoException("Vino no existe en BD"));
        vino.updateParams(body);
        repository.save(vino);
        return "Realizada operacion PUT de vino con id: " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteVinos(@PathVariable Integer id) {
        try {
            repository.deleteById(id);
            return "Vino con id " + id + " eliminado de la BD.";
        } catch (Exception e) {
            throw new VinoException("Vino con id " + id + " no existe en la BD.");
        }
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String partialUpdateGeneric(@RequestBody Vino vinoDTO, @PathVariable Integer id) {
        Boolean needUpdate = false;
        Vino vino = repository.findById(id).orElseThrow(() -> new VinoException("Id no existe."));
        if (vinoDTO.getNombreComercial() != null) {
            vino.setNombreComercial(vinoDTO.getNombreComercial());
            needUpdate = true;
        }
        if (vinoDTO.getDenominacion() != null) {
            vino.setDenominacion(vinoDTO.getDenominacion());
            needUpdate = true;
        }
        if (vinoDTO.getCategoria() != null) {
            vino.setCategoria(vinoDTO.getCategoria());
            needUpdate = true;
        }
        if (vinoDTO.getPrecio() != null) {
            vino.setPrecio(vinoDTO.getPrecio());
            needUpdate = true;
        }
        if (vinoDTO.getBodega_id() != null) {
            vino.setBodega_id(vinoDTO.getBodega_id());
            needUpdate = true;
        }
        if (needUpdate) {
            repository.save(vino);
            return "Realizada operacion PATCH de vino con id: " + id;
        } else
            return "No se actualizó.";
    }

    @GetMapping("/VinoPorNombre/{nombre}")
    public Vino getVinoPorNombreComercial(@PathVariable String nombre) {
        return repository.findByNombreComercial(nombre)
                .orElseThrow(() -> new VinoException("No se ha encontrado el Vino con nombre " + nombre));
    }

    @GetMapping("/VinoPorPrecio")
    public List<Vino> getVinoPorPrecio(@RequestParam Float precio1, @RequestParam Float precio2) {
        return repository.findByPrecioBetween(precio1, precio2);
    }

    // Ejercicio 1
    @DeleteMapping(value = "/BorrarPorDenominacion_Categoria", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Vino> deletePorDenominacionCategoria(@RequestBody String json) {
        try {
            JSONObject jsonObjeto = new JSONObject(json);
            String denominacion = jsonObjeto.getString("denominacion");
            String categoria = jsonObjeto.getString("categoria");
            return repository.deleteByDenominacionAndCategoria(denominacion, categoria);
        } catch (Exception e) {
            System.out.println(e);
            throw new VinoException("Error al ejecutar el método deletePorDenominacionCategoria");
        }
    }

    // Ejercicio 2
    @GetMapping("/ContarConDenominacion")
    public String contarPorDenominacion(@RequestParam String denominacion) {
        return "La BD contiene " + repository.countByDenominacion(denominacion) + " vinos con denominacion "
                + denominacion;
    }

    // Ejercicio 3
    @GetMapping("/ContarSinDenominacion")
    public String contarSinDenominacion(@RequestParam String denominacion) {
        int siTiene = repository.countByDenominacion(denominacion);
        long totalVinos = repository.count();
        return "La BD contiene " + (totalVinos - siTiene) + " vinos que no tienen denominacion " + denominacion;
    }

    // Ejercicio 4
    /*
     * {"nombreComercial":"Vino2","denominacion":"Cambiado","categoria":"Anciano",
     * "precio":8.2,"bodegaId":{"id":4}}
     */
    @PostMapping(value = "/NewVinoConRelacion", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String newVinoConRelacion(@RequestBody VinoConRelacion newVinoConRelacion) {
        try {
            repository2.save(newVinoConRelacion);
            return "Nuevo vinoConRelacion creado con id " + newVinoConRelacion.getId();
        } catch (Exception e) {
            throw new VinoConRelacionException("Error al crear el nuevo registro");
        }
    }

    /*
     * {"nombre" : "Bodega1", "cif" : "12345F", "direccion" : "Direccion1"}
     */
    @PostMapping(value = "/NewBodega", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String newBodega(@RequestBody Bodega newBodega) {
        try {
            repository3.save(newBodega);
            return "Nueva bodega creado con id " + newBodega.getId();
        } catch (Exception e) {
            throw new BodegaException("Error al crear el nuevo registro");
        }
    }

    @GetMapping("/VinoConRelacion/{id}")
    public VinoConRelacion getVinoConRelacionConId(@PathVariable Integer id) {
        return repository2.findById(id).orElseThrow(() -> new VinoConRelacionException("Sin resultado."));
    }

    @GetMapping("/Bodega/{id}")
    public Bodega getBodegaConId(@PathVariable Integer id) {
        return repository3.findById(id).orElseThrow(() -> new BodegaException("Sin resultado."));
    }

    // Ejercicio 5
    @GetMapping("/VinoPorDenominacionYBodega/{denominacion}/{nombreBodega}")
    public List<VinoConRelacion> getVinoPorDenominacionYBodega(@PathVariable String denominacion,
            @PathVariable String nombreBodega) {
        return repository2.findByDenominacionYBodega(denominacion, nombreBodega);
    }

    // Ejercicio 6
    @PutMapping(value = "/VinoConRelacion/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String putVinoConRelacion(@RequestBody VinoConRelacion body, @PathVariable Integer id) {
        VinoConRelacion vinoCR = repository2.findById(id)
                .orElseThrow(() -> new VinoConRelacionException("VinoCR no existe en BD"));
        vinoCR.updateParams(body);
        repository2.save(vinoCR);
        return "Realizada operacion PUT de vinoConRelacion con id: " + id;
    }

    @PutMapping(value = "/Bodega/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String putBodega(@RequestBody Bodega body, @PathVariable Integer id) {
        Bodega bodega = repository3.findById(id).orElseThrow(() -> new BodegaException("VinoCR no existe en BD"));
        bodega.updateParams(body);
        repository3.save(bodega);
        return "Realizada operacion PUT de bodega con id: " + id;
    }

    @PatchMapping(value = "/VinoConRelacion/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String partialUpdateGenericVinoCR(@RequestBody VinoConRelacion vinoDTO, @PathVariable Integer id) {
        Boolean needUpdate = false;
        VinoConRelacion vino = repository2.findById(id)
                .orElseThrow(() -> new VinoConRelacionException("Id no existe."));
        if (vinoDTO.getNombreComercial() != null) {
            vino.setNombreComercial(vinoDTO.getNombreComercial());
            needUpdate = true;
        }
        if (vinoDTO.getDenominacion() != null) {
            vino.setDenominacion(vinoDTO.getDenominacion());
            needUpdate = true;
        }
        if (vinoDTO.getCategoria() != null) {
            vino.setCategoria(vinoDTO.getCategoria());
            needUpdate = true;
        }
        if (vinoDTO.getPrecio() != null) {
            vino.setPrecio(vinoDTO.getPrecio());
            needUpdate = true;
        }
        if (vinoDTO.getBodegaId() != null) {
            vino.setBodegaId(vinoDTO.getBodegaId());
            needUpdate = true;
        }
        if (needUpdate) {
            repository2.save(vino);
            return "Realizada operacion PATCH de vinoCR con id: " + id;
        } else
            return "No se actualizó.";
    }

    @PatchMapping(value = "/Bodega/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String partialUpdateGenericBodega(@RequestBody Bodega bodegaDTO, @PathVariable Integer id) {
        Boolean needUpdate = false;
        Bodega bodega = repository3.findById(id).orElseThrow(() -> new BodegaException("Id no existe."));
        if (bodegaDTO.getNombre() != null) {
            bodega.setNombre(bodegaDTO.getNombre());
            needUpdate = true;
        }
        if (bodegaDTO.getCif() != null) {
            bodega.setCif(bodegaDTO.getCif());
            needUpdate = true;
        }
        if (bodegaDTO.getDireccion() != null) {
            bodega.setDireccion(bodegaDTO.getDireccion());
            needUpdate = true;
        }
        if (needUpdate) {
            repository3.save(bodega);
            return "Realizada operacion PATCH de bodega con id: " + id;
        } else
            return "No se actualizó.";
    }
}