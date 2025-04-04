package com.mx.Clientes.Service;

import java.util.List;

import com.mx.Clientes.Entity.Clientes;

public interface IClientesService {
		
		public List<Clientes> listar();
		public void guardar(Clientes clientes);
		public Clientes buscar(int idClientes);
		public void editar(Clientes clientes);
		public void eliminar(int idClientes);


	}
