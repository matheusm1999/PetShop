package com.matheus.petshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AtendimentoDto {
	
	@NotBlank
	private String descricaoAtendimento;
	
	@NotNull
	private long valor;
	
	public String getDescricaoAtendimento() {
		return descricaoAtendimento;
	}

	public void setDescricaoAtendimento(String descricaoAtendimento) {
		this.descricaoAtendimento = descricaoAtendimento;
	}

	public long getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "AtendimentoDto [descricaoAtendimento=" + descricaoAtendimento + ", valor=" + valor + "]";
	}
	
	

}
