package br.com.ostech.api.controller.dto.cliente;

import br.com.ostech.api.model.Cliente;

public class InfoClienteResponse {

	private Long id;
	private String nome;
	private String contato;
	private String email;
	private String telefone;
	private String cpf;

	private InfoEnderecoResponse endereco;

	public InfoClienteResponse(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.contato = cliente.getContato();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.cpf = cliente.getCpf();
		this.endereco = new InfoEnderecoResponse(cliente.getEndereco());
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getContato() {
		return contato;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public InfoEnderecoResponse getEndereco() {
		return endereco;
	}

}
