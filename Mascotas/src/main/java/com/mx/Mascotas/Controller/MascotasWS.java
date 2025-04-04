package com.mx.Mascotas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Mascotas.Entity.Mascotas;
import com.mx.Mascotas.Service.MascotasServiceImp;

@RestController
@RequestMapping(path = "/M")
public class MascotasWS {
	
	@Autowired
	private MascotasServiceImp service;
	
	//url : http://localhost:8003/M
	
	//LISTAR
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Mascotas> responsables = service.listar();
		if(responsables.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.ok(responsables);
		}
	}
	
	//GUARDAR
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Mascotas mascotas){
		try {
			Mascotas encontrado = service.buscar(mascotas.getIdMascotas());
			if(encontrado == null) {
				service.guardar(mascotas);
				return ResponseEntity.ok("Se registro exitosamente");
			}else {
				return ResponseEntity.status(HttpStatus.FOUND).body("Este registro ya esxite");
			}
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
	}
	
	//BUSCAR
	
	@GetMapping(path = "/{idMascotas}")
	public ResponseEntity<?> buscar(@PathVariable("idMascotas") int idMascotas){
		Mascotas encontrado = service.buscar(idMascotas);
		if(encontrado != null) {
			return ResponseEntity.ok(encontrado);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un registro con ese ID");
		}
	}
	
	//ELIMINAR
	
	@DeleteMapping(path = "/{idMascotas}")
	public ResponseEntity<?> eliminar(@PathVariable int idMascotas){
		Mascotas encontrado = service.buscar(idMascotas);
		if(encontrado != null) {
			service.eliminar(idMascotas);
			return ResponseEntity.ok("El registro fue eliminado con exito");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este registro no se puede eliminar porque no existe");
		}
	}
	
	//METODO QUE CONSUME LOS MICROSERVICIOS RESPONSABLE Y VETERINARIO
	
	//BUSCAR POR VETERINARIA
	@GetMapping("/V/{veterinariaId}")
	public ResponseEntity<List<Mascotas>> buscarPorVeterinaria(@PathVariable int veterinariaId){
		List<Mascotas> mascotas = service.buscarPorVeterinaria(veterinariaId);
		if(mascotas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.ok(mascotas);
		}
		
	}
	
	//BUSCAR POR RESPONSABLE
	@GetMapping("/R/{responsableId}")
	public ResponseEntity<List<Mascotas>> buscarResponsableId(@PathVariable int responsableId){
		List<Mascotas> mascotas = service.buscarResponsableId(responsableId);
		if(mascotas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.ok(mascotas);
		}
		
	}

}
