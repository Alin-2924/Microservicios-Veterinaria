package com.mx.Responsables.Service;

import java.util.List;

import com.mx.Responsables.Entity.Responsables;

public interface IResponsablesService {
	
	public List<Responsables> listar();
	
	public void guardar(Responsables responsables);
	
	public void eliminar(long idResponsables);
	
	public void editar(Responsables responsables);
	
	public Responsables buscar(long idResponsables);

}