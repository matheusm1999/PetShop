package com.matheus.petshop.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.matheus.petshop.dto.UsuarioDto;
import com.matheus.petshop.model.Usuario;
import com.matheus.petshop.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

public class UsuarioController {

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id){
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		
		if(usuarioOpt.isPresent() == true) {
			return ResponseEntity.status(200).body(usuarioOpt.get());
		}
		
		return ResponseEntity.status(404).body("Registro não encontrado");		
	}
	
	@GetMapping
	public ResponseEntity<?> getAllClientes(){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());		
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<?> saveUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
		Usuario usuario = new Usuario();
		
		BeanUtils.copyProperties(usuarioDto, usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable long id){
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		
		if(usuarioOpt.isPresent()) {
			usuarioRepository.delete(usuarioOpt.get());
			return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não pode ser encontrado");
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable long id, @RequestBody @Valid UsuarioDto usuarioDto){
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		
		Usuario usuario = new Usuario();
		
		if(usuarioOpt.isPresent()) {
			BeanUtils.copyProperties(usuarioDto, usuario);
			usuario.setCPF(usuarioOpt.get().getCPF());
			return ResponseEntity.status(HttpStatus.OK).body("Usuario atualizado com sucesso\n" + usuarioRepository.save(usuario));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Erro ao atualizar o usuario" );
		
	}
	
}
