package br.com.ostech.api.controller.dto.cliente;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import br.com.ostech.api.model.Cliente;

public class NovoClienteRequest {

	@NotBlank
	private String nome;

	@NotBlank
	@Size(max = 14)
	private String contato;

	private String email;
	private String telefone;
	private String cpf;

	@NotNull
	private EnderecoRequest endereco;

	public NovoClienteRequest(@NotBlank String nome, @NotBlank @Size(max = 14) String contato, String email,
			String telefone, String cpf, EnderecoRequest endereco) {
		super();
		this.nome = nome;
		this.contato = contato;
		this.email = email;
		this.telefone = telefone;
		this.cpf = cpf;
		this.endereco = endereco;
	}

	public Cliente converterParaCliente() {
		return new Cliente(nome, email, cpf, contato, telefone, endereco.converterParaEndereco());
	}

}
