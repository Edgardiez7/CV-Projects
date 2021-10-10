package com.uva.users.repository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import com.uva.users.model.Vino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VinoRepository extends JpaRepository<Vino, Integer>{
    Optional<Vino> findByNombreComercial(String nombre);
    List<Vino> findByPrecioBetween(Float precio1, float precio2);
    boolean existsVinoByDenominacionAndCategoria(String denominacion, String categoria);
    @Transactional
    //Long deleteByDenominacionAndCategoria(String denominacion, String categoria);
    List<Vino> deleteByDenominacionAndCategoria(String denominacion, String categoria);
    Integer countByDenominacion(String denominacion);
}