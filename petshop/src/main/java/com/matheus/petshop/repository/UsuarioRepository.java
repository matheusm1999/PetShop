package com.matheus.petshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.petshop.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

	public Optional<Usuario> findByCPF(String cpf);
	public Optional<Usuario> findByNome(String nome);
}
