package com.mx.Mascotas.Service;

import java.util.List;

import com.mx.Mascotas.Entity.Mascotas;

public interface IMascotasService {
	
	public List<Mascotas> listar();
	
	public void guardar(Mascotas mascotas);
	
	public void eliminar(int idMascotas);
	
	public void editar(Mascotas mascotas);
	
	public Mascotas buscar(int idMascotas);

}	

