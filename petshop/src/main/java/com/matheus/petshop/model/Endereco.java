package com.matheus.petshop.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_endereco;
	
	@Column(nullable = false, unique = false, length = 100)
	private String logradouro;
	
	@Column(nullable = false, unique = false, length = 100)
	private String cidade;
	
	@Column(nullable = false, unique = false, length = 100)
	private String bairro;
	
	@Column(nullable = false, unique = false, length = 100)
	private String complemento;
	
	@Column(nullable = false, unique = false, length = 100)
	private String tag;
	
	public long getId_endereco() {
		return id_endereco;
	}

	public void setId_endereco(long id_endereco) {
		this.id_endereco = id_endereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	

	@Override
	public String toString() {
		return "Endereco [id_endereco=" + id_endereco + ", logradouro=" + logradouro + ", cidade=" + cidade
				+ ", Bairro=" + bairro + ", complemento=" + complemento + ", tag=" + tag + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id_endereco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return id_endereco == other.id_endereco;
	}
	
	
	

}
