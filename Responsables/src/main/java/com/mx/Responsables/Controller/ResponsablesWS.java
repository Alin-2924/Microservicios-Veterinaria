package com.mx.Responsables.Controller;

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

import com.mx.Responsables.Entity.Responsables;
import com.mx.Responsables.Service.ResponsablesServiceImp;

@RestController
@RequestMapping(path = "/R")
public class ResponsablesWS {
	
	@Autowired
	private ResponsablesServiceImp service;
	
	//url : http://localhost:8002/R
	
	//LISTAR
	
		@GetMapping
		public ResponseEntity<?> listar(){
			List<Responsables> responsables = service.listar();
			if(responsables.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}else {
				return ResponseEntity.ok(responsables);
			}
		}
		
		//GUARDAR
	
		@PostMapping
		public ResponseEntity<?> guardar(@RequestBody Responsables reponsables){
			try {
				Responsables encontrado = service.buscar(reponsables.getIdResponsables());
				if(encontrado == null) {
					service.guardar(reponsables);
					return ResponseEntity.ok("Se registro exitosamente");
				}else {
					return ResponseEntity.status(HttpStatus.FOUND).body("Este registro ya esxite");
				}
			}catch(IllegalArgumentException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
				}
		}
		
		
		//BUSCAR
		
		@GetMapping(path = "/{idResponsables}")
		public ResponseEntity<?> buscar(@PathVariable("idResponsables") int idResponsables){
			Responsables encontrado = service.buscar(idResponsables);
			if(encontrado != null) {
				return ResponseEntity.ok(encontrado);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un registro con ese ID");
			}
		}
		
		//ELIMINAR
		
		@DeleteMapping(path = "/{idResponsables}")
		public ResponseEntity<?> eliminar(@PathVariable int idResponsables){
			Responsables encontrado = service.buscar(idResponsables);
			if(encontrado != null) {
				service.eliminar(idResponsables);
				return ResponseEntity.ok("El registro fue eliminado con exito");
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este registro no se puede eliminar porque no existe");
			}
		}
		
		// PARA QUE OBTENGA TODOS LOS RESPONSABLES DE UNA VETERINARIA
		
		@GetMapping("/V/{veterinariaId}")
		public ResponseEntity<List<Responsables>> buscarPorVeterinaria(@PathVariable int veterinariaId){
			List<Responsables> responsables = service.buscarPorVeterinaria(veterinariaId);
			if(responsables.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}else {
				return ResponseEntity.ok(responsables);
			}
			
		}
		
}

