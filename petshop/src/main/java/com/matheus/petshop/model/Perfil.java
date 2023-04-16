package com.matheus.petshop.model;

//import org.springframework.security.core.GrantedAuthority;

public enum Perfil /*implements GrantedAuthority */{
	ROLE_ADMIN ("Administrador"),
	ROLE_CLIENTE ("Cliente");
	
	private final String tipoPerfil;
	
	private Perfil(String tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
	}
	
	
	@Override
	public String toString() {
		return tipoPerfil;
	}

	/*
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
