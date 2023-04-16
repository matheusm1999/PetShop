package com.matheus.petshop.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.petshop.dto.ContatoDto;
import com.matheus.petshop.model.Contato;
import com.matheus.petshop.repository.ContatoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contato")
public class ContatoController {

private final ContatoRepository contatoRepository;
	
	@Autowired
	public ContatoController(ContatoRepository contatoRepository) {
		this.contatoRepository = contatoRepository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id){
		Optional<Contato> contatoOpt = contatoRepository.findById(id);
		
				
		if(contatoOpt.isPresent() == true) {
			return ResponseEntity.status(200).body(contatoOpt.get());
		}
		
		return ResponseEntity.status(404).body("Registro não encontrado");		
	}
	
	@GetMapping
	public ResponseEntity<?> getAllClientes(){
		return ResponseEntity.status(HttpStatus.OK).body(contatoRepository.findAll());		
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<?> saveCliente(@RequestBody @Valid ContatoDto contatoDto){
		Contato contato = new Contato();
		BeanUtils.copyProperties(contatoDto, contato);
		return ResponseEntity.status(HttpStatus.CREATED).body(contatoRepository.save(contato));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteContato(@PathVariable long id){
		Optional<Contato> contatoOpt = contatoRepository.findById(id);
		
		if(contatoOpt.isPresent()) {
			contatoRepository.delete(contatoOpt.get());
			return ResponseEntity.status(HttpStatus.OK).body("Contato deletado com sucesso");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não pode ser encontrado");
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<?> updateContato(@PathVariable long id, @RequestBody @Valid ContatoDto contatoDto){
		Optional<Contato> contatoOpt = contatoRepository.findById(id);
		Contato contato = new Contato();
		
		if(contatoOpt.isPresent()) {
			BeanUtils.copyProperties(contatoDto, contato);
			contato.setIdContato(contatoOpt.get().getIdContato());
			
			return ResponseEntity.status(HttpStatus.OK).body("Cliente atualizado com sucesso\n" + contatoRepository.save(contato));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Erro ao atualizar o contato" );
		
	}
}
