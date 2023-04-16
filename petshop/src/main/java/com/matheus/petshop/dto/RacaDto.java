package com.matheus.petshop.dto;

import jakarta.validation.constraints.NotBlank;

public class RacaDto {
	
	@NotBlank
	private String descricao;
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "RacaDto [descricao=" + descricao + "]";
	}
	
	

}
