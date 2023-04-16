package controller;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.matheus.petshop.controller.ClienteController;
import com.matheus.petshop.model.Atendimento;
import com.matheus.petshop.model.Cliente;
import com.matheus.petshop.model.Contato;
import com.matheus.petshop.model.Endereco;
import com.matheus.petshop.model.Pet;
import com.matheus.petshop.model.Raca;
import com.matheus.petshop.model.Tipo;
import com.matheus.petshop.service.ClienteService;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@WebMvcTest
public class ClienteControllerTest {

	@Autowired
	private ClienteController clienteController;
	
	@MockBean
	private ClienteService clienteService;
	
	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.clienteController);
	}
	
	@Test
	public void retornarSucesso_BuscarCliente(long id) {
		
		Contato contato = new Contato();
		contato.setIdContato(1);
		contato.setTag("Tag");
		contato.setTipo(Tipo.email);
		contato.setValor("matheus@email.com");
	
		ArrayList<Endereco> enderecos = new ArrayList<>(); 
		Endereco endereco = new Endereco();
		endereco.setId_endereco(1);
		endereco.setBairro("Centro");
		endereco.setCidade("Foz do Iguaçu");
		endereco.setComplemento("Casa");
		endereco.setLogradouro("Rua X");
		endereco.setTag("tag");
		
		Raca raca = new Raca();
		raca.setId_raca(1);
		raca.setDescricao("Akita");
		
		ArrayList<Atendimento> atendimentos = new ArrayList<>();
		Atendimento atendimento = new Atendimento();
		atendimento.setIdAtendimento(1);
		atendimento.setData(LocalDateTime.now(ZoneId.of("UTC")));
		atendimento.setValor(100);
		atendimento.setDescricaoAtendimento("Vacinas");
		atendimentos.add(atendimento);
		
		ArrayList<Pet> pets = new ArrayList<>();
		Pet pet = new Pet();
		pet.setIdPet(1);
		pet.setDataNascimento(new Date("2000-01-01"));
		pet.setNome("pet");
		pet.setRaca(raca);
		pet.setAtendimentos(atendimentos);
		
		ArrayList<Cliente> clientes = new ArrayList<>();
		Cliente cliente = new Cliente();
		cliente.setCPF("123456");
		cliente.setDataCadastro(LocalDateTime.now(ZoneId.of("UTC")));
		cliente.setNome("Matheus");
		cliente.setEnderecos(enderecos);
		cliente.setPets(pets);
		clientes.add(cliente);
		
		when(this.clienteService.findById(id))
			.thenReturn(clientes);
		
		given()
		.accept(ContentType.JSON)
		.when()
		.get("/cliente")
		.then()
		.statusCode(HttpStatus.OK.value());		
	}
	
	@Test
	public void naoEncontrado_BuscarCliente(long id) {
		
		when(this.clienteService.findById(5))
			.thenReturn(null);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/cliente/{id}", 5L)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
}
