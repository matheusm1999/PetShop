package com.matheus.petshop.dto;
import com.matheus.petshop.model.Cliente;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;


public class EnderecoDto {
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
	private String Bairro;
	
	@NotBlank
	private String complemento;
	
	@NotBlank
	private String tag;
	
	private long id_endereco;

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

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
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
	
	

	public long getId_endereco() {
		return id_endereco;
	}

	public void setId_endereco(long id_endereco) {
		this.id_endereco = id_endereco;
	}

	@Override
	public String toString() {
		return "EnderecoDto [logradouro=" + logradouro + ", cidade=" + cidade + ", Bairro=" + Bairro + ", complemento="
				+ complemento + ", tag=" + tag + ", id_endereco=" + id_endereco + "]";
	}

	
}
