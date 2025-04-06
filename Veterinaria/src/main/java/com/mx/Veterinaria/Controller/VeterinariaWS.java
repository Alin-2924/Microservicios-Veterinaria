package com.mx.Veterinaria.Controller;

import java.util.List;
import java.util.Map;

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

import com.mx.Veterinaria.Entidades.Mascotas;
import com.mx.Veterinaria.Entidades.Responsables;
import com.mx.Veterinaria.Entity.Veterinaria;
import com.mx.Veterinaria.Service.VeterinariaServiceImp;

@RestController
@RequestMapping(path = "/V")
public class VeterinariaWS {

	@Autowired
	private VeterinariaServiceImp service;

	// url : http://localhost:8080/V

	// LISTAR

	@GetMapping
	public ResponseEntity<List<Veterinaria>> listar() {
		List<Veterinaria> veterinarias = service.listar();
		if (veterinarias.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.ok(veterinarias);
		}
	}

	// BUSCAR

	@GetMapping(path = "/{idVeterinaria}")
	public ResponseEntity<?> buscar(@PathVariable("idVeterinaria") int idVeterinaria) {
		Veterinaria encontrado = service.buscar(idVeterinaria);
		if (encontrado != null) {
			return ResponseEntity.ok(encontrado);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un registro con ese ID");
		}
	}

	// GUARDAR

	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Veterinaria veterinaria) {
		try {
			Veterinaria encontrado = service.buscar(veterinaria.getIdVeterinaria());
			if (encontrado == null) {
				service.guardar(veterinaria);
				return ResponseEntity.ok("Se registro exitosamente");
			} else {
				return ResponseEntity.status(HttpStatus.FOUND).body("Este registro ya esxite");
			}
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	// ELIMINAR

	@DeleteMapping(path = "/{idVeterinaria}")
	public ResponseEntity<?> eliminar(@PathVariable int idVeterinaria) {
		Veterinaria encontrado = service.buscar(idVeterinaria);
		if (encontrado != null) {
			service.eliminar(idVeterinaria);
			return ResponseEntity.ok("El registro fue eliminado con exito");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Este registro no se puede eliminar porque no existe");
		}

	}

	// METODO QUE CONSUME LOS MICROSERVICIOS

	// RESPONSABLE
	// url del metodo: httpp://localhost:8001/V/R/veterinariaId

	@GetMapping("/R/{veterinariaId}")
	public ResponseEntity<?> mostrarResponsablesPorVeterinaria(@PathVariable int veterinariaId) {
		try {
			// primero buscaremos si exite la veterinaria por Id
			Veterinaria aux = service.buscar(veterinariaId);
			if (aux == null) {
				return ResponseEntity.badRequest().body("No existen veterinarias con ese id");
			} else {

				// si existe entonce valida los responsables
				List<Responsables> responsables = service.obtenerResponsables(veterinariaId);
				if (responsables.isEmpty()) {
					return ResponseEntity.ofNullable("El responsable de la veterinaria es");
				} else {
					return ResponseEntity.ok(responsables);
				}
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Ocurrio un error, el servicio no esta disponible" + e.getMessage());
		}
	}

	// endpoint de Mascotas

	@GetMapping("/M/{veterinariaId}")
	public ResponseEntity<?> mascotas(@PathVariable int veterinariaId) {
		try {
			// validamos si existen veterinaria
			Veterinaria aux = service.buscar(veterinariaId);
			if (aux == null) {
				return ResponseEntity.badRequest().body("No existen veterinarias registradas");
			} else {
				List<Mascotas> mas = service.obtenerMascotas(veterinariaId);
				if (mas.isEmpty()) {
					return ResponseEntity.ofNullable("No existen mascotas");
				} else {
					return ResponseEntity.ok(mas);
				}
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error, Servicio no disponible" + e.getMessage());
		}
	}

	// nuevo metodo utilizando openFeing

	// --RESPONSABLES--//

	@PostMapping("/R/{veterinariaId}")
	public ResponseEntity<Responsables> guardarResponsables(@PathVariable("veterinariaId") int veterinariaId,
			@RequestBody Responsables responsables) {
		Responsables nuevoResponsables = service.saveResponsables(veterinariaId, responsables);
		return ResponseEntity.ok(nuevoResponsables);
	}

	// --MASCOTAS--//

	@PostMapping("/M/{veterinariaId}")
	public ResponseEntity<Mascotas> guardarMascotas(@PathVariable("veterinariaId") int veterinariaId,
			@RequestBody Mascotas mascotas) {
		Mascotas nuevaMascota = service.saveMascotas(veterinariaId, mascotas);
		return ResponseEntity.ok(nuevaMascota);
	}

	// PARA OBTENER TODO

	@GetMapping("/todo/{veterinariaId}")
	public ResponseEntity<?> listarTodo(@PathVariable int veterinariaId) {
		Map<String, Object> resultado = service.getVeterinariaAndModulos(veterinariaId);
		return ResponseEntity.ok(resultado);
	}
}