package com.mx.Veterinaria.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mx.Veterinaria.Entidades.Mascotas;
import com.mx.Veterinaria.Entidades.Responsables;
import com.mx.Veterinaria.Entity.Veterinaria;
import com.mx.Veterinaria.OpenFeingClient.IMascotasFeingClient;
import com.mx.Veterinaria.OpenFeingClient.IResponsablesFeingClient;
import com.mx.Veterinaria.Repository.VeterinariaRepository;

@Service
public class VeterinariaServiceImp implements IVeterinariaService {

	@Autowired
	private VeterinariaRepository dao;

	@Override
	public List<Veterinaria> listar() {

		return dao.findAll(Sort.by(Sort.Direction.ASC, "idVeterinaria"));
	}

	@Override
	public void guardar(Veterinaria veterinaria) {
		dao.save(veterinaria);

	}

	@Override
	public Veterinaria buscar(int idVeterinaria) {

		return dao.findById(idVeterinaria).orElse(null);
	}

	@Override
	public void editar(Veterinaria veterinaria) {
		dao.save(veterinaria);

	}

	@Override
	public void eliminar(int idVeterinaria) {
		dao.deleteById(idVeterinaria);

	}

	// ******METODO PARA CONSUMIR MICROSERVICIOS USANDO RestTemplate

	// METODO PARA CONSUMIR EL MICROSERVICIO DE RESPONSABLE USANDO RESTTEMPLATE

	// INYECTA EL RESTTEMPLATE
	@Autowired
	private RestTemplate restTemplate;

	// RESPONSABLES

	public List<Responsables> obtenerResponsables(int veterinariaId) {
		@SuppressWarnings("unchecked")
		List<Responsables> responsables = restTemplate
				.getForObject("http://localhost:8082/R/buscarPorVeterinaria/" + veterinariaId, List.class);
		return responsables;
	}

	// MASCOTAS

	public List<Mascotas> obtenerMascotas(int veterinariaId) {
		@SuppressWarnings("unchecked")
		List<Mascotas> mascotas = restTemplate
				.getForObject("http://localhost:8083/M/buscarPorVeterinaria/" + veterinariaId, List.class);
		return mascotas;
	}

	// INYECTA EL OPENFEINGCLIENT

	// --RESPONSABLES--//

	@Autowired
	private IResponsablesFeingClient respFC;
	// ********METODO PARA CONSUMIR CON OPENFEINGCLIENT

	public Responsables saveResponsables(int veterinariaId, Responsables responsables) {
		responsables.setVeterinariaId(veterinariaId);
		Responsables nuevoResponsable = respFC.save(responsables);
		return nuevoResponsable;
	}

	// Guardar un responsable

	// --MASCOTAS--//

	@Autowired
	private IMascotasFeingClient masFC;

	public Mascotas saveMascotas(int veterinariaId, Mascotas mascotas) {
		mascotas.setVeterinariaId(veterinariaId);
		Mascotas nuevaMascotas = masFC.save(mascotas);
		return nuevaMascotas;
	}

	// METODO PARA LISTAR TODA LA INFORMACION SOBRE LA VETERINARIA
	public Map<String, Object> getVeterinariaAndModulos(int veterinariaId) {

		Map<String, Object> resultado = new HashMap<>();

		// consultar si existe la veterinaria
		Veterinaria veterinaria = dao.findById(veterinariaId).orElse(null);
		if (veterinaria == null) {
			resultado.put("Veterinaria mensaje: ", "Esta veterinaria no existe");
		} else {
			resultado.put("Veterinaria: ", veterinaria);
			// consulta y validacion de Responsables
			List<Responsables> responsables = obtenerResponsables(veterinariaId);
			if (responsables.isEmpty()) {
				resultado.put("Responsable mensaje: ", "El responsable es");
			} else {
				resultado.put("El responsable es: ", responsables);
			}
			// consulta y validacion de las mascotas
			List<Mascotas> mas = obtenerMascotas(veterinariaId);
			if (mas.isEmpty()) {
				resultado.put("Mascota Mensaje", "La mascota es");
			} else {
				resultado.put("Mascota registrada", mas);
			} // cierre validacion Mascota

		} // cierre de validacion veterinaria

		return resultado;
	}

}
