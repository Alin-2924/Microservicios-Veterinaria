package com.mx.Veterinaria.Service;

import java.util.List;

import com.mx.Veterinaria.Entity.Veterinaria;

public interface IVeterinariaService {

	public List<Veterinaria> listar();
	public void guardar(Veterinaria veterinaria);
	public Veterinaria buscar(int idVeterinaria);
	public void editar(Veterinaria veterinaria);
	public void eliminar(int idVeterinaria);

}
