package com.matheus.petshop.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ClienteDto {
	
	@NotBlank
	private String nome;
	
	@Size(max = 11)
	private String CPF;
	
	@NotEmpty
	@Valid
	private List<EnderecoDto> enderecos;
	
	@NotEmpty
	@Valid
	private List<ContatoDto> contatos;
	
	@NotEmpty
	@Valid
	private List<PetDto> pets;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}


	public List<EnderecoDto> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDto> enderecos) {
		this.enderecos = enderecos;
	}

	public List<ContatoDto> getContatos() {
		return contatos;
	}

	public void setContatos(List<ContatoDto> contatos) {
		this.contatos = contatos;
	}

	public List<PetDto> getPets() {
		return pets;
	}

	public void setPets(List<PetDto> pets) {
		this.pets = pets;
	}

	@Override
	public String toString() {
		return "ClienteDto [nome=" + nome + ", CPF=" + CPF + ", enderecos=" + enderecos + ", contatos=" + contatos
				+ ", pets=" + pets + "]";
	}

}
