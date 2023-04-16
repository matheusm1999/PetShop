package com.matheus.petshop.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Raca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_raca;
	
	@Column(nullable = false, unique = false, length = 100)
	private String descricao;
	
	public long getId_raca() {
		return id_raca;
	}

	public void setId_raca(long id_raca) {
		this.id_raca = id_raca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Raca [id=" + id_raca + ", descricao=" + descricao + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_raca);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Raca other = (Raca) obj;
		return id_raca == other.id_raca;
	}	

}
