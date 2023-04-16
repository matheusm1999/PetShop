package com.matheus.petshop.dto;

import com.matheus.petshop.model.Tipo;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public class ContatoDto {
	
	@NotBlank
	private String valor;
	
	@NotBlank
	private String tag;
	
	@Enumerated
	private Tipo tipo;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "ContatoDto [valor=" + valor + ", tag=" + tag + ", tipo=" + tipo + "]";
	}
	
	
}
