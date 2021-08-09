package br.com.ostech.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class OrdemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	private Cliente cliente;

	@NotBlank
	private String equipamento;

	@NotBlank
	private String descricao;

	private BigDecimal preco = new BigDecimal(0.0);

	@Enumerated(EnumType.STRING)
	private StatusOrdemServico status;

	private LocalDate dataAbertura;

	private LocalDate dataFinalizacao;

	@Deprecated
	public OrdemServico() {

	}

	public OrdemServico(@NotNull Cliente cliente, @NotBlank String equipamento, @NotBlank String descricao,
			BigDecimal preco) {
		this.cliente = cliente;
		this.equipamento = equipamento;
		this.descricao = descricao;
		this.preco = preco;
		this.status = StatusOrdemServico.ABERTA;
		this.dataAbertura = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public Cliente getCliente() {
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

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public LocalDate getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void atualiza(OrdemServico ordemServico) {
		this.cliente = ordemServico.getCliente();
		this.equipamento = ordemServico.getEquipamento();
		this.descricao = ordemServico.getDescricao();
		this.preco = ordemServico.getPreco();
	}

}
