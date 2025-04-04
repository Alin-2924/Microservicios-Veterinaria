package com.mx.Mascotas.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MACOTAS")
public class Mascotas {
	
	@Id
	@Column(name = "ID_MASCOTA", columnDefinition = "NUMBER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMascotas;
	
	@Column(name = "NOMBRE", columnDefinition = "NVARCHAR2(50)")
	private String nombre;
	
	@Column(name = "RAZA", columnDefinition = "NVARCHAR2(15)")
	private String raza;
	
	@Column(name = "EDAD", columnDefinition = "NUMBER")
	private int edad;
	
	@Column(name = "RAZON_CITA", columnDefinition = "NVARCHAR2(100)")
	private String razonCita;
	
	@Column(name = "CLIENTE_ID", columnDefinition = "NUMBER")
	private int clienteId;
	
	@Column(name = "RESPONSABLE_ID", columnDefinition = "NUMBER")
	private int responsableId;
	
	@Column(name = "VETERINARIA_ID", columnDefinition = "NUMBER")
	private int veterinariaId;

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

	public int getIdMascotas() {
		return idMascotas;
	}

	public void setIdMascotas(int idMascotas) {
		this.idMascotas = idMascotas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getRazonCita() {
		return razonCita;
	}

	public void setRazonCita(String razonCita) {
		this.razonCita = razonCita;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public int getResponsableId() {
		return responsableId;
	}

	public void setResponsableId(int responsableId) {
		this.responsableId = responsableId;
	}

	public int getVeterinariaId() {
		return veterinariaId;
	}

	public void setVeterinariaId(int veterinariaId) {
		this.veterinariaId = veterinariaId;
	}

	@Override
	public String toString() {
		return "Mascotas [idMascotas=" + idMascotas + ", nombre=" + nombre + ", raza=" + raza + ", edad=" + edad
				+ ", razonCita=" + razonCita + ", clienteId=" + clienteId + ", responsableId=" + responsableId
				+ ", veterinariaId=" + veterinariaId + "]";
	}
	
	

}
