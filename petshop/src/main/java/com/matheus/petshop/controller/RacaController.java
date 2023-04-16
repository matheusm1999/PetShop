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

import com.matheus.petshop.dto.RacaDto;
import com.matheus.petshop.model.Raca;
import com.matheus.petshop.repository.RacaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/raca")
public class RacaController {
	
	private final RacaRepository racaRepository;

	@Autowired
	public RacaController (RacaRepository racaRepository) {
		this.racaRepository = racaRepository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id){
		Optional<Raca> racaOpt = racaRepository.findById(id);
		
				
		if(racaOpt.isPresent() == true) {
			return ResponseEntity.status(200).body(racaOpt.get());
		}
		
		return ResponseEntity.status(404).body("Registro não encontrado");		
	}
	
	@GetMapping
	public ResponseEntity<?> getAllRacas(){
		return ResponseEntity.status(HttpStatus.OK).body(racaRepository.findAll());		
	}
	
	@PostMapping
	public ResponseEntity<?> saveRaca(@RequestBody @Valid RacaDto racaDto){
		Raca raca = new Raca();
		BeanUtils.copyProperties(racaDto, raca);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(racaRepository.save(raca));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRaca(@PathVariable long id){
		Optional<Raca> racaOpt = racaRepository.findById(id);
		
		if(racaOpt.isPresent()) {
			racaRepository.delete(racaOpt.get());
			return ResponseEntity.status(HttpStatus.OK).body("Raça deletada com sucesso");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não pode ser encontrado");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateRaca(@PathVariable long id, @RequestBody @Valid RacaDto racaDto){
		Optional<Raca> racaOpt = racaRepository.findById(id);
		Raca raca = new Raca();
		
		if(racaOpt.isPresent()) {
			BeanUtils.copyProperties(racaDto, raca);
			raca.setId_raca(racaOpt.get().getId_raca());
			return ResponseEntity.status(HttpStatus.OK).body("Raça atualizada com sucesso\n" + racaRepository.save(raca));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Erro ao atualizar a raça" );
		
	}
	
}
