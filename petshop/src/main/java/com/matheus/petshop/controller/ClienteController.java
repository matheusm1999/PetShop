package com.matheus.petshop.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
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
import com.matheus.petshop.dto.PetDto;
import com.matheus.petshop.model.Atendimento;
import com.matheus.petshop.model.Cliente;
import com.matheus.petshop.model.Contato;
import com.matheus.petshop.model.Endereco;
import com.matheus.petshop.model.Pet;
import com.matheus.petshop.model.Raca;
import com.matheus.petshop.repository.ClienteRepository;
import com.matheus.petshop.repository.ContatoRepository;
import com.matheus.petshop.repository.EnderecoRepository;
import com.matheus.petshop.repository.PetRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	private final ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ContatoRepository contatoRepository;
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id){
		Optional<Cliente> clienteOpt = clienteRepository.findById(id);
		
				
		if(clienteOpt.isPresent() == true) {
			return ResponseEntity.status(200).body(clienteOpt.get());
		}
		
		return ResponseEntity.status(404).body("Registro não encontrado");		
	}
	
	@GetMapping
	public ResponseEntity<?> getAllClientes(){
		return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());		
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<?> saveCliente(@RequestBody @Valid ClienteDto clienteDto){
		if(clienteRepository.existsByCPF(clienteDto.getCPF())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Cliente com esse CPF já existe");
		}
		
		Cliente cliente = new Cliente();
		Endereco endereco;
		Contato contato;
		List<Endereco> enderecos = new ArrayList<>();
		List<Contato> contatos = new ArrayList<>();
		List<Pet> pets = new ArrayList<>();
		
		BeanUtils.copyProperties(clienteDto, cliente);
		
		System.out.println(clienteDto);
			
		for(int i = 0; i < clienteDto.getEnderecos().size(); i++) {
			endereco = new Endereco();
			BeanUtils.copyProperties(clienteDto.getEnderecos().get(i), endereco);
			enderecos.add(endereco);
		}
		
		for(int i = 0; i < clienteDto.getContatos().size(); i++) {
			contato = new Contato();
			BeanUtils.copyProperties(clienteDto.getContatos().get(i), contato);
			contatos.add(contato);
		}
		
		pets = petDtoToPets(clienteDto.getPets());
		
		cliente.setEnderecos(enderecos);
		cliente.setContatos(contatos);
		cliente.setPets(pets);
		cliente.setDataCadastro(LocalDateTime.now(ZoneId.of("UTC")));
		System.out.println(cliente);
		
		//return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable long id){
		Optional<Cliente> clienteOpt = clienteRepository.findById(id);
		
		if(clienteOpt.isPresent()) {
			clienteRepository.delete(clienteOpt.get());
			return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não pode ser encontrado");
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCliente(@PathVariable long id, @RequestBody @Valid ClienteDto clienteDto){
		Optional<Cliente> clienteOpt = clienteRepository.findById(id);
		
		Cliente cliente = new Cliente();
		List<Endereco> enderecos = new ArrayList<>();
		List<Contato> contatos = new ArrayList<>();
		List<Pet> pets = new ArrayList<>();
		
		if(clienteOpt.isPresent()) {
			enderecos = enderecoRepository.findAllByClienteId(id);
			contatos = contatoRepository.findAllByClienteId(id);
			pets = petRepository.findAllByClienteId(id);
	
			BeanUtils.copyProperties(clienteDto, cliente);
		
			cliente.setEnderecos(enderecos);
			cliente.setContatos(contatos);
			cliente.setPets(pets);
			cliente.setId_cliente(clienteOpt.get().getId_cliente());
			cliente.setDataCadastro(clienteOpt.get().getDataCadastro());
			
			return ResponseEntity.status(HttpStatus.OK).body("Cliente atualizado com sucesso\n" + clienteRepository.save(cliente));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Erro ao atualizar o cliente" );
		
	}
	
	public List<Pet> petDtoToPets(List<PetDto> petDtos){
		Pet pet;
		Raca raca;
		Atendimento atendimento;
		ArrayList<Pet> pets = new ArrayList<>();
		
		for(int i = 0; i < petDtos.size(); i++) {
			pet = new Pet();
			raca = new Raca();
			List<Atendimento> atendimentos = new ArrayList<>();
			BeanUtils.copyProperties(petDtos.get(i), pet);
			BeanUtils.copyProperties(petDtos.get(i).getRaca(), raca);
			
			for(int j = 0; j < petDtos.get(i).getAtendimentos().size(); j++) {
				atendimento = new Atendimento();
				atendimento.setData(LocalDateTime.now(ZoneId.of("UTC")));
				BeanUtils.copyProperties(petDtos.get(i).getAtendimentos().get(i),atendimento);
				atendimentos.add(atendimento);
			}
			
			pet.setAtendimentos(atendimentos);
			pet.setRaca(raca);
			pets.add(pet);
		}
		
		return pets;
	}

}
