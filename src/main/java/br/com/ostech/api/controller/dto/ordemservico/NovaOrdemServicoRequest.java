package br.com.ostech.api.controller.dto.ordemservico;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.ostech.api.model.Cliente;
import br.com.ostech.api.model.OrdemServico;
import br.com.ostech.api.validator.ExisteId;

public class NovaOrdemServicoRequest {

	@NotNull
	@ExisteId(atributo = "id", entidade = Cliente.class)
	private Long clienteId;

	@NotBlank
	private String equipamento;

	@NotBlank
	private String descricao;
	private BigDecimal preco;

	public NovaOrdemServicoRequest(@NotNull Long clienteId, @NotBlank String equipamento, @NotBlank String descricao,
			BigDecimal preco) {
		super();
		this.clienteId = clienteId;
		this.equipamento = equipamento;
		this.descricao = descricao;
		this.preco = preco;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public OrdemServico converterParaEntidade(Cliente cliente) {
		return new OrdemServico(cliente, equipamento, descricao, preco);
	}

}
