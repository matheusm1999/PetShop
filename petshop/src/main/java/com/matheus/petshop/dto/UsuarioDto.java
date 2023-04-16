package com.matheus.petshop.dto;

import com.matheus.petshop.model.Perfil;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public class UsuarioDto {
	
	@NotBlank
	private String CPF;
	
	@NotBlank
	private String nome;
	
	@Enumerated
	private Perfil perfil;
	
	@NotBlank
	private String senha;

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "UsuarioDto [CPF=" + CPF + ", nome=" + nome + ", perfil=" + perfil + ", senha=" + senha + "]";
	}

}
