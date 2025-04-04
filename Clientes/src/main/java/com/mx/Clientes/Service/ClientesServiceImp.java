package com.mx.Clientes.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.Clientes.Entity.Clientes;
import com.mx.Clientes.Repository.IClientesRepository;

@Service
public class ClientesServiceImp implements IClientesService{
	
	@Autowired
	private IClientesRepository dao;

	@Override
	public List<Clientes> listar() {

		return dao.findAll(Sort.by(Sort.Direction.ASC, "idClientes"));
	}

	@Override
	public void guardar(Clientes clientes) {

		dao.save(clientes);
		
	}

	@Override
	public Clientes buscar(int idClientes) {

		return dao.findById(idClientes).orElse(null);
	}

	@Override
	public void editar(Clientes clientes) {

		dao.save(clientes);
		
	}

	@Override
	public void eliminar(int idClientes) {

		dao.deleteById(idClientes);
		
	}
	
	
	
	

}
