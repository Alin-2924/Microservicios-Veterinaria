package com.mx.Veterinaria.Entidades;

import lombok.Data;

@Data
public class Responsables {

	private long idResponsable;
	private String nombre;
	private long contacto;
	private int veterinariaId;

	public Responsables() {

	}

	public Responsables(int idResponsable, String nombre, long contacto, int veterinariaId) {
		super();
		this.idResponsable = idResponsable;
		this.nombre = nombre;
		this.contacto = contacto;
		this.veterinariaId = veterinariaId;
	}

}
