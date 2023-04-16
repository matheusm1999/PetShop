package com.matheus.petshop.autenticacao;

import com.matheus.petshop.model.Cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastroRequest {

	private String cpf;
	private String senha;
	private String nome;
	private Cliente cliente;
	
}
