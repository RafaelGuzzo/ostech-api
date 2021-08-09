package br.com.ostech.api.controller.dto.ordemservico;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import br.com.ostech.api.model.OrdemServico;

public class OrdemServicoPdfResponse {

	private Long ordemServicoId;
	private String equipamento;
	private String observacoes;

	private String nome;
	private String contato;
	private String telefone;
	private String email;
	private String cpf;
	private String rua;
	private String bairro;
	private String numero;
	private String complemento;
	private String cep;
	private String cidade;
	private String uf;
	private BigDecimal preco;

	private Date dataAbertura;

	public OrdemServicoPdfResponse(OrdemServico ordemServico) {
		var cliente = ordemServico.getCliente();

		this.ordemServicoId = ordemServico.getId();
		this.equipamento = ordemServico.getEquipamento();
		this.observacoes = ordemServico.getDescricao();
		this.preco = ordemServico.getPreco();

		this.nome = cliente.getNome();
		this.contato = cliente.getContato();
		this.telefone = cliente.getTelefone();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
		this.rua = cliente.getEndereco().getRua();
		this.bairro = cliente.getEndereco().getBairro();
		this.numero = cliente.getEndereco().getNumero();
		this.complemento = cliente.getEndereco().getComplemento();
		this.cep = cliente.getEndereco().getCep();
		this.cidade = cliente.getEndereco().getCidade();
		this.uf = cliente.getEndereco().getUf();

		this.dataAbertura = Date.valueOf(ordemServico.getDataAbertura());
	}

	public Long getOrdemServicoId() {
		return ordemServicoId;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public String getObservacoes() {
		return observacoes;
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

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
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

	public String getComplemento() {
		return complemento;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public String getUf() {
		return uf;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public BigDecimal getPreco() {
		return preco;
	}

}
