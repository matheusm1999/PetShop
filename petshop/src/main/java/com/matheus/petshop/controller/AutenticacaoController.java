package com.matheus.petshop.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheus.petshop.autenticacao.AutenticacaoRequest;
import com.matheus.petshop.autenticacao.AutenticacaoResponse;
import com.matheus.petshop.autenticacao.AutenticacaoService;
import com.matheus.petshop.autenticacao.CadastroRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoController {
	
	@Autowired
	private AutenticacaoService service;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<AutenticacaoResponse> cadastro(@RequestBody CadastroRequest request){
		return ResponseEntity.ok(service.cadastrar(request));
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity<AutenticacaoResponse> autenticar(@RequestBody AutenticacaoRequest request){
		return ResponseEntity.ok(service.autenticar(request));
	}
	
}
