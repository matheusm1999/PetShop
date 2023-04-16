package com.matheus.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.matheus.petshop.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato,Long>{

	@Query(value = "SELECT * from Contato WHERE id_cliente=:id_cliente",nativeQuery = true)
	public List<Contato> findAllByClienteId(long id_cliente); 
}
