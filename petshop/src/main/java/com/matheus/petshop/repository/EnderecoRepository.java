package com.matheus.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.matheus.petshop.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco,Long>{

	@Query(value = "SELECT * from Endereco WHERE id_cliente=:id_cliente",nativeQuery = true)
	public List<Endereco> findAllByClienteId(long id_cliente);
}
