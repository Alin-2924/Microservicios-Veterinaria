package com.mx.Mascotas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.Mascotas.Entity.Mascotas;
import com.mx.Mascotas.Repository.IMascotasRepository;

@Service
public class MascotasServiceImp implements IMascotasService{
	
	@Autowired
	private IMascotasRepository dao;

	@Override
	public List<Mascotas> listar() {

		return dao.findAll(Sort.by(Sort.Direction.ASC, "idMascotas"));
	}

	@Override
	public void guardar(Mascotas mascotas) {

		dao.save(mascotas);
		
	}

	@Override
	public void eliminar(int idMascotas) {

		dao.deleteById(idMascotas);
		
	}

	@Override
	public void editar(Mascotas mascotas) {

		dao.save(mascotas);
		
	}

	@Override
	public Mascotas buscar(int idMascotas) {

		return dao.findById(idMascotas).orElse(null);
	}
	
	//BUSCAR POR VETERINARIA Y RESPONSABLE
	
	
	public List<Mascotas> buscarPorVeterinaria(int veterinariaId){
		return dao.findByVeterinariaId(veterinariaId);
	}
	
	public List<Mascotas> buscarResponsableId(int responsableId){
		return dao.findByResponsableId(responsableId);
	}
	

}