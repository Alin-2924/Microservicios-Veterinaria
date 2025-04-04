package com.mx.Clientes.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CLIENTES")
public class Clientes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idClientes;
	
	@Column(name= "Nombre_Cliente")
	private String nombre;
	
	@Column(name = "Direccion")
	private String direccion;
	
	@Column(name = " Contacto")
	private long contacto;

}