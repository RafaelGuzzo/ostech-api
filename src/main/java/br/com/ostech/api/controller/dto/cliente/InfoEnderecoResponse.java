package br.com.ostech.api.controller.dto.cliente;

import br.com.ostech.api.model.Endereco;

public class InfoEnderecoResponse {

	private String rua;
	private String bairro;
	private String numero;
	private String cep;
	private String cidade;
	private String complemento;
	private String uf;

	public InfoEnderecoResponse(Endereco endereco) {
		this.rua = endereco.getRua();
		this.bairro = endereco.getBairro();
		this.numero = endereco.getNumero();
		this.cep = endereco.getCep();
		this.cidade = endereco.getCidade();
		this.complemento = endereco.getComplemento();
		this.uf = endereco.getUf();
	}

	public String getRua() {
		return rua;
	}

	public String getBairro() {
		return bairro;
	}

	public String getNumero() {
		return numero;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getUf() {
		return uf;
	}

}
