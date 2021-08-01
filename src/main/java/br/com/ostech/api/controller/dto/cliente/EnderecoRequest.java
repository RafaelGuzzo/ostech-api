package br.com.ostech.api.controller.dto.cliente;

import javax.validation.constraints.NotBlank;

import br.com.ostech.api.model.Endereco;

public class EnderecoRequest {

	@NotBlank
	private String rua;

	@NotBlank
	private String bairro;

	@NotBlank
	private String numero;

	private String cep;
	private String cidade;
	private String complemento;
	private String uf;

	public EnderecoRequest(@NotBlank String rua, @NotBlank String bairro, @NotBlank String numero, String cep,
			String cidade, String complemento, String uf) {
		super();
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.cep = cep;
		this.cidade = cidade;
		this.complemento = complemento;
		this.uf = uf;
	}

	public Endereco converterParaEndereco() {
		return new Endereco(rua, bairro, cep, cidade, complemento, numero, uf);
	}
}
