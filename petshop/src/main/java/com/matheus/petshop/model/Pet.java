package com.matheus.petshop.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idPet;
	
	@Column(nullable = false, unique = false, length = 255)
	private String nome;

	@Column(nullable = false, unique = false)
	private Date dataNascimento;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_raca")
	private Raca raca;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_pet")
	private List<Atendimento> atendimentos;

	public long getIdPet() {
		return idPet;
	}

	public void setIdPet(long idPet) {
		this.idPet = idPet;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
	

	@Override
	public String toString() {
		return "Pet [idPet=" + idPet + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", raca=" + raca + ", atendimentos="
				+ atendimentos + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPet);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		return idPet == other.idPet;
	}
	

}
