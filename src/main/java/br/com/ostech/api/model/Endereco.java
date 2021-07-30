package br.com.ostech.api.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {

	@NotBlank
	private String rua;

	@NotBlank
	private String bairro;
	private String cep;
	private String cidade;
	private String complemento;

	@NotBlank
	private String numero;
	private String uf;

	@Deprecated
	public Endereco() {
	}

	public Endereco(@NotBlank String rua, @NotBlank String bairro, String cep, String cidade, String complemento,
			@NotBlank String numero, String uf) {
		super();
		this.rua = rua;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.complemento = complemento;
		this.numero = numero;
		this.uf = uf;
	}

	public String getRua() {
		return rua;
	}

	public String getBairro() {
		return bairro;
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

	public String getNumero() {
		return numero;
	}

	public String getUf() {
		return uf;
	}

}
