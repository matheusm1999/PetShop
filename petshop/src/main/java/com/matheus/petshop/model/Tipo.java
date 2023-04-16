package com.matheus.petshop.model;

public enum Tipo {
	email("e-mail"), telefone("telefone"); 
	
	private String valor;
	
	Tipo(String valor){
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	

}
