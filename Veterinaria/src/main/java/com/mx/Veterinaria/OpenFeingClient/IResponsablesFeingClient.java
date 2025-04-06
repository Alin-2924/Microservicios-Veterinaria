package com.mx.Veterinaria.OpenFeingClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mx.Veterinaria.Entidades.Responsables;

@FeignClient(name = "Responsables", url = "http://localhost:8002", path = "/R")
public interface IResponsablesFeingClient {

	@GetMapping(path = "buscarPorVeterinaria/{veterinariaId}")
	public List<Responsables> buscarPorVeterinaria(@PathVariable("veterinariaId") int veterinariaId);

	@PostMapping
	public Responsables save(@RequestBody Responsables responsables);

}