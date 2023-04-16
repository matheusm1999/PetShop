package com.matheus.petshop.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.matheus.petshop.model.Atendimento;
import com.matheus.petshop.model.Cliente;
import com.matheus.petshop.model.Contato;
import com.matheus.petshop.model.Endereco;
import com.matheus.petshop.model.Pet;
import com.matheus.petshop.model.Raca;
import com.matheus.petshop.model.Tipo;

//Classe utilizada para testes
@Service
public class ClienteService {
	
	public ArrayList<Cliente> findById(long id){
		Contato contato = new Contato();
		contato.setIdContato(id);
		contato.setTag("Tag");
		contato.setTipo(Tipo.email);
		contato.setValor("matheus@email.com");
	
		ArrayList<Endereco> enderecos = new ArrayList<>(); 
		Endereco endereco = new Endereco();
		endereco.setId_endereco(1);
		endereco.setBairro("Centro");
		endereco.setCidade("Foz do Iguaçu");
		endereco.setComplemento("Casa");
		endereco.setLogradouro("Rua X");
		endereco.setTag("tag");
		
		Raca raca = new Raca();
		raca.setId_raca(1);
		raca.setDescricao("Akita");
		
		ArrayList<Atendimento> atendimentos = new ArrayList<>();
		Atendimento atendimento = new Atendimento();
		atendimento.setIdAtendimento(1);
		atendimento.setData(LocalDateTime.now(ZoneId.of("UTC")));
		atendimento.setValor(100);
		atendimento.setDescricaoAtendimento("Vacinas");
		atendimentos.add(atendimento);
		
		ArrayList<Pet> pets = new ArrayList<>();
		Pet pet = new Pet();
		pet.setIdPet(1);
		pet.setDataNascimento(new Date("2000-01-01"));
		pet.setNome("pet");
		pet.setRaca(raca);
		pet.setAtendimentos(atendimentos);
		
		ArrayList<Cliente> clientes = new ArrayList<>();
		Cliente cliente = new Cliente();
		cliente.setCPF("123456");
		cliente.setDataCadastro(LocalDateTime.now(ZoneId.of("UTC")));
		cliente.setNome("Matheus");
		cliente.setEnderecos(enderecos);
		cliente.setPets(pets);
		clientes.add(cliente);
		
		return clientes;
	}

}
