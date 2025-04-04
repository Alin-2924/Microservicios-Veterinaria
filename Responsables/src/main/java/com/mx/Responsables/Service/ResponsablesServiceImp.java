package com.mx.Responsables.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.Responsables.Entity.Responsables;
import com.mx.Responsables.Repository.IResponsablesRepository;

@Service
public class ResponsablesServiceImp implements IResponsablesService{
	
	@Autowired
	private IResponsablesRepository dao;

	@Override
	public List<Responsables> listar() {
		
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idResponsables"));
	}

	@Override
	public void guardar(Responsables responsables) {

		dao.save(responsables);
		
	}

	@Override
	public void eliminar(long idResponsables) {

		dao.deleteById(idResponsables);
		
	}

	@Override
	public void editar(Responsables responsables) {

		dao.save(responsables);
		
	}

	@Override
	public Responsables buscar(long idResponsables) {
	
		return dao.findById(idResponsables).orElse(null);
	}
	
	//METDODO PARA QUE OBTENGA TODOS LOS RESPONSABLES DE UNA VETERINARIA
	
	public List<Responsables> buscarPorVeterinaria(int veterinariaId) {
		
		return dao.findByVeterinariaId(veterinariaId);
	} 
	

}
