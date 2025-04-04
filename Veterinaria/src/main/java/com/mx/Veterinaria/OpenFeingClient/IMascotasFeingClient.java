package com.mx.Veterinaria.OpenFeingClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mx.Veterinaria.Entidades.Mascotas;

@FeignClient(name = "Mascotas", url = "http://localhost:8003", path = "/M") 
public interface IMascotasFeingClient {
	
	@GetMapping(path = "buscarPorVeterinaria/{veterinariaId}")
	public List<Mascotas> buscarPorVeterinaria(@PathVariable("veterinariaId") int veterinariaId);
	
	@PostMapping
	public Mascotas save(@RequestBody Mascotas mascotas);

}
