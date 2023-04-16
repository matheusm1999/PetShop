package com.matheus.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.petshop.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{
	
	public boolean existsByCPF(String CPF);

}
