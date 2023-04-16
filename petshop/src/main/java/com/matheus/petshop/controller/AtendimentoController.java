package com.matheus.petshop.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.petshop.dto.AtendimentoDto;
import com.matheus.petshop.model.Atendimento;
import com.matheus.petshop.repository.AtendimentoRepository;

import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {
	
	private final AtendimentoRepository atendimentoRepository;

	@Autowired
	public AtendimentoController(AtendimentoRepository atendimentoRepository) {
		this.atendimentoRepository = atendimentoRepository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id){
		Optional<Atendimento> atendimentoOpt = atendimentoRepository.findById(id);
		
		if(atendimentoOpt.isPresent() == true) {
			return ResponseEntity.status(200).body(atendimentoOpt.get());
		}
		
		return ResponseEntity.status(404).body("Registro não encontrado");		
	}
	
	@GetMapping
	public ResponseEntity<?> getAllClientes(){
		return ResponseEntity.status(HttpStatus.OK).body(atendimentoRepository.findAll());		
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<?> saveAtendimento(@RequestBody @Valid AtendimentoDto atendimentoDto){
		Atendimento atendimento = new Atendimento();
		
		BeanUtils.copyProperties(atendimentoDto, atendimento);
		atendimento.setData(LocalDateTime.now(ZoneId.of("UTC")));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(atendimentoRepository.save(atendimento));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAtendimento(@PathVariable long id){
		Optional<Atendimento> atendimentoOpt = atendimentoRepository.findById(id);
		
		if(atendimentoOpt.isPresent()) {
			atendimentoRepository.delete(atendimentoOpt.get());
			return ResponseEntity.status(HttpStatus.OK).body("Atendimento deletado com sucesso");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não pode ser encontrado");
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAtendimento(@PathVariable long id, @RequestBody @Valid AtendimentoDto atendimentoDto){
		Optional<Atendimento> atendimentoOpt = atendimentoRepository.findById(id);
		
		Atendimento atendimento = new Atendimento();
		
		if(atendimentoOpt.isPresent()) {
			BeanUtils.copyProperties(atendimentoDto, atendimento);
			atendimento.setIdAtendimento(atendimentoOpt.get().getIdAtendimento());
			atendimento.setData(atendimentoOpt.get().getData());
			return ResponseEntity.status(HttpStatus.OK).body("Atendimento atualizado com sucesso\n" + atendimentoRepository.save(atendimento));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Erro ao atualizar o atendimento" );
		
	}
	
}
