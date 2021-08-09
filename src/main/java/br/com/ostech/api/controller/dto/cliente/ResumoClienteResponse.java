package br.com.ostech.api.controller.dto.cliente;

import br.com.ostech.api.model.Cliente;

public class ResumoClienteResponse {
	private Long clienteId;
	private String nome;
	private String contato;
	private String telefone;

	public ResumoClienteResponse(Cliente cliente) {
		this.clienteId = cliente.getId();
		this.nome = cliente.getNome();
		this.contato = cliente.getContato();
		this.telefone = cliente.getTelefone();
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

	public String getTelefone() {
		return telefone;
	}

}
