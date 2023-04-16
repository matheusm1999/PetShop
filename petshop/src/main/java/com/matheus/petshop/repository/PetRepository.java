package com.matheus.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.matheus.petshop.model.Pet;

public interface PetRepository extends JpaRepository<Pet,Long>{

	@Query(value = "SELECT * from Pet WHERE id_cliente=:id_cliente",nativeQuery = true)
	public List<Pet> findAllByClienteId(long id_cliente); 
}
