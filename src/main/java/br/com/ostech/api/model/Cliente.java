package br.com.ostech.api.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;
	private String email;
	private String cpf;

	@NotBlank
	@Size(max = 14)
	private String contato;
	private String telefone;

	@Embedded
	private Endereco endereco;

	@Deprecated
	public Cliente() {
	}

	public Cliente(@NotBlank String nome, String email, String cpf, @NotBlank @Size(max = 14) String contato,
			String telefone, Endereco endereco) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.contato = contato;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public void atualiza(Cliente cliente) {

		this.nome = cliente.getNome();
		this.contato = cliente.getContato();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.cpf = cliente.getCpf();
		this.endereco = cliente.getEndereco();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public String getContato() {
		return contato;
	}

	public String getTelefone() {
		return telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

}
