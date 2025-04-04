package com.mx.Veterinaria.Entidades;

import lombok.Data;

@Data
public class Mascotas {
	
	private int idMascotas;
	private String nombre;
	private String raza;
	private int edad;
	private String razonCita;
	private int clienteId;
	private int responsableId;
	private int veterinariaId;
	
	public Mascotas () {
		
	}

	public Mascotas(int idMascotas, String nombre, String raza, int edad, String razonCita, int clienteId,
			int responsableId, int veterinariaId) {
		super();
		this.idMascotas = idMascotas;
		this.nombre = nombre;
		this.raza = raza;
		this.edad = edad;
		this.razonCita = razonCita;
		this.clienteId = clienteId;
		this.responsableId = responsableId;
		this.veterinariaId = veterinariaId;
	}
	
	

}
