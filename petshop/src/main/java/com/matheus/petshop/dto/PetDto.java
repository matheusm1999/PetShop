package com.matheus.petshop.dto;

import java.util.Date;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PetDto {
	
	@NotBlank
	private String nome;
	
	@Valid
	private List<AtendimentoDto> atendimentos;
	
	@Valid
	private RacaDto raca;
	
	@NotNull
	private Date dataNascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<AtendimentoDto> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<AtendimentoDto> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public RacaDto getRaca() {
		return raca;
	}

	public void setRaca(RacaDto raca) {
		this.raca = raca;
	}

	@Override
	public String toString() {
		return "PetDto [nome=" + nome + ", atendimentos=" + atendimentos + ", raca=" + raca + ", dataNascimento="
				+ dataNascimento + "]";
	}


}
