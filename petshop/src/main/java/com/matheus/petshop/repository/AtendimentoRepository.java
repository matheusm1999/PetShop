package com.matheus.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.matheus.petshop.model.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento,Long>{

	@Query(value = "SELECT * from Atendimento WHERE id_pet=:id_pet",nativeQuery = true)
	List<Atendimento> findAllByPetId(long id_pet);
	
}
