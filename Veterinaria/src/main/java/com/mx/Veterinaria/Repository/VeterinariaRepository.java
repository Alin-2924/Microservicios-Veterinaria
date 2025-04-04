package com.mx.Veterinaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Veterinaria.Entity.Veterinaria;

@Repository
public interface VeterinariaRepository extends JpaRepository<Veterinaria, Integer>{

}
