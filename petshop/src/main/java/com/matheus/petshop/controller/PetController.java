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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.petshop.dto.PetDto;
import com.matheus.petshop.model.Pet;
import com.matheus.petshop.model.Raca;
import com.matheus.petshop.model.Atendimento;
import com.matheus.petshop.model.Contato;
import com.matheus.petshop.model.Endereco;
import com.matheus.petshop.repository.PetRepository;
import com.matheus.petshop.repository.RacaRepository;
import com.matheus.petshop.repository.AtendimentoRepository;
import com.matheus.petshop.repository.ContatoRepository;
import com.matheus.petshop.repository.EnderecoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pet")
public class PetController {

	private final PetRepository petRepository;
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Autowired
	private RacaRepository racaRepository;
	
	@Autowired
	public PetController(PetRepository petRepository) {
		this.petRepository = petRepository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id){
		Optional<Pet> petOpt = petRepository.findById(id);
		
				
		if(petOpt.isPresent() == true) {
			return ResponseEntity.status(200).body(petOpt.get());
		}
		
		return ResponseEntity.status(404).body("Registro não encontrado");		
	}
	
	@GetMapping
	public ResponseEntity<?> getAllPets(){
		return ResponseEntity.status(HttpStatus.OK).body(petRepository.findAll());		
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<?> savePet(@RequestBody @Valid PetDto petDto){
		
		List<Atendimento> atendimentos = new ArrayList<>();
		Atendimento atendimento;
		Pet pet = new Pet();
		Raca raca = new Raca();
		
		BeanUtils.copyProperties(petDto, pet);
		BeanUtils.copyProperties(petDto.getRaca(), raca);
		
		for(int i = 0; i < petDto.getAtendimentos().size(); i++) {
			atendimento = new Atendimento();
			BeanUtils.copyProperties(petDto.getAtendimentos().get(i), atendimento);
			atendimento.setData(LocalDateTime.now(ZoneId.of("UTC")));
			atendimentos.add(atendimento);
		}
		
		pet.setRaca(raca);
		pet.setAtendimentos(atendimentos);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(petRepository.save(pet));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePet(@PathVariable long id){
		Optional<Pet> petDto = petRepository.findById(id);
		
		if(petDto.isPresent()) {
			petRepository.delete(petDto.get());
			return ResponseEntity.status(HttpStatus.OK).body("Pet deletado com sucesso");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não pode ser encontrado");
	}
	
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePet(@PathVariable long id, @RequestBody @Valid PetDto petDto){
		Optional<Pet> petOpt = petRepository.findById(id);
		
		Pet pet = new Pet();
		List<Atendimento> atendimentos = new ArrayList<>();
		Raca raca = new Raca();
		
		if(petOpt.isPresent()) {
			atendimentos = atendimentoRepository.findAllByPetId(id);
	
			BeanUtils.copyProperties(petDto, pet);
			BeanUtils.copyProperties(petDto.getRaca(), raca);
			
			
			pet.setAtendimentos(atendimentos);
			pet.setIdPet(petOpt.get().getIdPet());
			raca.setId_raca(petOpt.get().getRaca().getId_raca());
			pet.setRaca(raca);
			
			System.out.println(pet);
			return ResponseEntity.status(HttpStatus.OK).body("Pet atualizado com sucesso\n" + petRepository.save(pet));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Erro ao atualizar o pet" );
		
	}
	
}
