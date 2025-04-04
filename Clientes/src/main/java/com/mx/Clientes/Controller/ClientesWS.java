package com.mx.Clientes.Controller;

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

import com.mx.Clientes.Entity.Clientes;
import com.mx.Clientes.Service.ClientesServiceImp;

@RestController
@RequestMapping(path = "/C")
public class ClientesWS {
	
	@Autowired
	private ClientesServiceImp service;
	
	//URL : http://localhost: 8004/C
	
	//LISTAR
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Clientes> clientes = service.listar();
		if(clientes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.ok(clientes);
		}
	}
	
	//GUARDAR
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Clientes clientes){
		try {
			Clientes encontrado = service.buscar(clientes.getIdClientes());
			if(encontrado == null) {
				service.guardar(clientes);
				return ResponseEntity.ok("Se registro exitosamente");
			}else {
				return ResponseEntity.status(HttpStatus.FOUND).body("Este registro ya esxite");
			}
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
	}
	
	//BUSCAR
	
	@GetMapping(path = "/{idClientes}")
	public ResponseEntity<?> buscar(@PathVariable("idClientes") int idClientes){
		Clientes encontrado = service.buscar(idClientes);
		if(encontrado != null) {
			return ResponseEntity.ok(encontrado);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un registro con ese ID");
		}
	}
	
	//ELIMINAR
	
	@DeleteMapping(path = "/{idClientes}")
	public ResponseEntity<?> eliminar(@PathVariable int idClientes){
		Clientes encontrado = service.buscar(idClientes);
		if(encontrado != null) {
			service.eliminar(idClientes);
			return ResponseEntity.ok("El registro fue eliminado con exito");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este registro no se puede eliminar porque no existe");
		}
	}

}
