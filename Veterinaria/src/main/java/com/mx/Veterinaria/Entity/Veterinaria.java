package com.mx.Veterinaria.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Veterinaria")
@Data
public class Veterinaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVeterinaria;

	@Column(name = "Nombre_Veterinaria")
	private String nombre;

	@Column(name = "Direccion")
	private String direccion;

	@Column(name = "Telefono")
	private long telefono;

}
