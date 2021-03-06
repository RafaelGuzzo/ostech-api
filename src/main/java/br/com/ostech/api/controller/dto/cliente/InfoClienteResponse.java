package br.com.ostech.api.controller.dto.cliente;

import br.com.ostech.api.model.Cliente;

public class InfoClienteResponse {

	private Long clienteId;
	private String nome;
	private String contato;
	private String email;
	private String telefone;
	private String cpf;

	private InfoEnderecoResponse endereco;

	public InfoClienteResponse(Cliente cliente) {
		this.clienteId = cliente.getId();
		this.nome = cliente.getNome();
		this.contato = cliente.getContato();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.cpf = cliente.getCpf();
		this.endereco = new InfoEnderecoResponse(cliente.getEndereco());
	}

	public Long getClienteId() {
		return clienteId;
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
