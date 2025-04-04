package com.mx.Mascotas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Mascotas.Entity.Mascotas;

public interface IMascotasRepository extends JpaRepository<Mascotas, Integer>{
	
	public List<Mascotas> findByResponsableId(int responsableId);
	
	public List<Mascotas> findByVeterinariaId(int veterinariaId);
	

}
