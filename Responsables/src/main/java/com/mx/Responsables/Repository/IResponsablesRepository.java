package com.mx.Responsables.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Responsables.Entity.Responsables;

@Repository
public interface IResponsablesRepository extends JpaRepository<Responsables, Long>{
	
	public List<Responsables> findByVeterinariaId(int veterinariaId);

}
