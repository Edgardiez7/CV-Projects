package com.uva.users.repository;

import com.uva.users.model.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodegaRepository extends JpaRepository<Bodega, Integer>{
    
}
