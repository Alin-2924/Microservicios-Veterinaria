package com.mx.Responsables.Entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name =  "RESPONSABLES")
@Access(AccessType.FIELD) 
public class Responsables {
	

	@Id
	@Column(name = "ID_RESPONSABLES", columnDefinition = "NUMBER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idResponsables;
	
	@Column(name = "NOMBRE", columnDefinition = "NVARCHAR2(50)")
	private String nombre;
	
	@Column(name = "CONTACTO", columnDefinition = "NUMBER(10)")
	private long contacto;
	
	@JoinColumn(name = "VETERINARIA_ID", columnDefinition = "NUMBER")
	private int veterinariaId;
	
	public Responsables() {
		
	}

	public Responsables(long idResponsables, String nombre, long contacto, int veterinariaId) {
		super();
		this.idResponsables = idResponsables;
		this.nombre = nombre;
		this.contacto = contacto;
		this.veterinariaId = veterinariaId;
	}

	public long getIdResponsables() {
		return idResponsables;
	}

	public void setIdResponsables(long idResponsables) {
		this.idResponsables = idResponsables;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getContacto() {
		return contacto;
	}

	public void setContacto(long contacto) {
		this.contacto = contacto;
	}

	public int getVeterinariaId() {
		return veterinariaId;
	}

	public void setVeterinariaId(int veterinariaId) {
		this.veterinariaId = veterinariaId;
	}

	@Override
	public String toString() {
		return "Responsables [idResponsables=" + idResponsables + ", nombre=" + nombre + ", contacto=" + contacto
				+ ", veterinariaId=" + veterinariaId + "]";
	}
	
	
}
