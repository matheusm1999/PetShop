package com.matheus.petshop.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Atendimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idAtendimento;
	
	@Column(nullable = false, unique = false, length = 255)
	private String descricaoAtendimento;
	
	@Column(nullable = false, unique = false)
	private long valor;
	
	@Column(nullable = false, unique = false)
	private LocalDateTime data;
	
	
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
	
	public LocalDateTime getData() {
		return data;
	}
	
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public long getIdAtendimento() {
		return idAtendimento;
	}
	
	public void setIdAtendimento(long idAtendimento) {
		this.idAtendimento = idAtendimento;
	}
	
	@Override
	public String toString() {
		return "Atendimento [idAtendimento=" + idAtendimento + ", descricaoAtendimento=" + descricaoAtendimento
				+ ", valor=" + valor + ", data=" + data + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idAtendimento);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atendimento other = (Atendimento) obj;
		return idAtendimento == other.idAtendimento;
	}
	
	

}
