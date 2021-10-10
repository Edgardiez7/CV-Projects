package com.uva.users.repository;

import java.util.List;

import com.uva.users.model.VinoConRelacion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VinoConRelacionRepository extends JpaRepository<VinoConRelacion, Integer>{
    List<VinoConRelacion> findByDenominacionYBodega(String denominacion, String nombreBodega);
}
