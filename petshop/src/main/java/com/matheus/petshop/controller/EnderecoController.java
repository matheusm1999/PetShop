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

import com.matheus.petshop.dto.ClienteDto;
import com.matheus.petshop.dto.EnderecoDto;
import com.matheus.petshop.model.Endereco;
import com.matheus.petshop.repository.EnderecoRepository;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
private final EnderecoRepository enderecoRepository;
	
	@Autowired
	public EnderecoController(EnderecoRepository enderecoRepository) {
		this.enderecoRepository = enderecoRepository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id){
		Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);
		
				
		if(enderecoOpt.isPresent() == true) {
			return ResponseEntity.status(200).body(enderecoOpt.get());
		}
		
		return ResponseEntity.status(404).body("Registro não encontrado");		
	}
	
	@GetMapping
	public ResponseEntity<?> getAllEnderecos(){
		/*
		ClienteDto cdto = new ClienteDto();
		cdto.setCPF("132");
		cdto.setNome("joao");
		
		EnderecoDto e1 = new EnderecoDto();
		e1.setBairro("b1");
		e1.setCidade("c1");
		e1.setComplemento("comp1");
		e1.setLogradouro("l1");
		e1.setTag("t1");
		
		EnderecoDto e2 = new EnderecoDto();
		e2.setBairro("b2");
		e2.setCidade("c2");
		e2.setComplemento("comp2");
		e2.setLogradouro("l2");
		e2.setTag("t2");
		
		List<EnderecoDto> enderecos = new ArrayList<EnderecoDto>();
		enderecos.add(e1);
		enderecos.add(e2);
		
		cdto.setEnderecosDto(enderecos);
		return ResponseEntity.status(HttpStatus.OK).body(cdto);
		 */
		
		return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.findAll());
		
	}
	
	@PostMapping
	public ResponseEntity<?> saveEndereco(@RequestBody @Valid EnderecoDto enderecoDto){
		Endereco endereco = new Endereco();
		BeanUtils.copyProperties(enderecoDto, endereco);
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoRepository.save(endereco));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEndereco(@PathVariable long id){
		Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);
		
		if(enderecoOpt.isPresent()) {
			enderecoRepository.delete(enderecoOpt.get());
			return ResponseEntity.status(HttpStatus.OK).body("Endereco deletado com sucesso");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não pode ser encontrado");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEndereco(@PathVariable long id, @RequestBody @Valid EnderecoDto enderecoDto){
		Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);
		Endereco endereco = new Endereco();
		
		if(enderecoOpt.isPresent()) {
			BeanUtils.copyProperties(enderecoDto, endereco);
			endereco.setId_endereco(enderecoOpt.get().getId_endereco());
			return ResponseEntity.status(HttpStatus.OK).body("Endereco atualizado com sucesso\n" + enderecoRepository.save(endereco));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Erro ao atualizar o endereco" );
		
	}

}
