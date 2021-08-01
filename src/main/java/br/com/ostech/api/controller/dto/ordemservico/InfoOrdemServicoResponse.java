package br.com.ostech.api.controller.dto.ordemservico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.ostech.api.model.OrdemServico;
import br.com.ostech.api.model.StatusOrdemServico;

public class InfoOrdemServicoResponse {

	private Long id;
	private String equipamento;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServico status;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataFinalizacao;
	private ClienteOrdemServicoResponse cliente;

	public InfoOrdemServicoResponse(OrdemServico ordemServico) {
		this.id = ordemServico.getId();
		this.cliente = new ClienteOrdemServicoResponse(ordemServico.getCliente());
		this.equipamento = ordemServico.getEquipamento();
		this.descricao = ordemServico.getDescricao();
		this.preco = ordemServico.getPreco();
		this.status = ordemServico.getStatus();
		this.dataAbertura = ordemServico.getDataAbertura();
		this.dataFinalizacao = ordemServico.getDataFinalizacao();
	}

	public Long getId() {
		return id;
	}

	public ClienteOrdemServicoResponse getCliente() {
		return cliente;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public StatusOrdemServico getStatus() {
		return status;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public LocalDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}

}
