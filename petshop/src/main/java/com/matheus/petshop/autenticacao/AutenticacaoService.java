package com.matheus.petshop.autenticacao;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.matheus.petshop.configuration.JwtService;
import com.matheus.petshop.model.Cliente;
import com.matheus.petshop.model.Perfil;
import com.matheus.petshop.model.Usuario;
import com.matheus.petshop.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public AutenticacaoResponse cadastrar(CadastroRequest request) {
		Cliente cliente = request.getCliente();
		cliente.setDataCadastro(LocalDateTime.now(ZoneId.of("UTC")));
		
		Usuario usuario = Usuario.builder()
				.CPF(request.getCpf())
				.nome(request.getNome())
				.cliente(cliente)
				.senha(passwordEncoder.encode(request.getSenha()))
				.perfil(Perfil.ROLE_ADMIN)
				.build();
		usuarioRepository.save(usuario);
		
		String jwtToken = jwtService.generateToken(usuario);
		return AutenticacaoResponse.builder()
				.token(jwtToken)
				.build();
	}

	public AutenticacaoResponse autenticar(AutenticacaoRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCpf(), request.getSenha()));
		Usuario usuario = usuarioRepository.findByCPF(request.getCpf())
				.orElseThrow();
		
		String jwtToken = jwtService.generateToken(usuario);
		return AutenticacaoResponse.builder()
				.token(jwtToken)
				.build();
	}

}
