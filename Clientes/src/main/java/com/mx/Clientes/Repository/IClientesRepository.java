package com.mx.Clientes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Clientes.Entity.Clientes;

public interface IClientesRepository extends JpaRepository<Clientes, Integer>{

}
