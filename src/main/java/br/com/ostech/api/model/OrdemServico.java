package br.com.ostech.api.model;

import java.math.BigDecimal;
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

	private BigDecimal preco = new BigDecimal("0.0");

	@Enumerated(EnumType.STRING)
	private StatusOrdemServico status;

	private LocalDateTime dataAbertura = LocalDateTime.now();

	private LocalDateTime dataFinalizacao;

	@Deprecated
	public OrdemServico() {
		
	}
	public OrdemServico(@NotNull Cliente cliente, @NotBlank String equipamento, @NotBlank String descricao) {
		this.cliente = cliente;
		this.equipamento = equipamento;
		this.descricao = descricao;
	}
		
}
