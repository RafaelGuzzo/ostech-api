package br.com.ostech.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ostech.api.controller.dto.ordemservico.AtualizaOrdemServicoRequest;
import br.com.ostech.api.controller.dto.ordemservico.InfoOrdemServicoResponse;
import br.com.ostech.api.controller.dto.ordemservico.NovaOrdemServicoRequest;
import br.com.ostech.api.controller.dto.ordemservico.OrdemServicoCadastraResponse;
import br.com.ostech.api.controller.dto.ordemservico.OrdemServicoPdfResponse;
import br.com.ostech.api.jasper.OrdemServicoPdf;
import br.com.ostech.api.model.Cliente;
import br.com.ostech.api.model.OrdemServico;
import br.com.ostech.api.repository.ClienteRepository;
import br.com.ostech.api.repository.OrdenServicoRepository;
import br.com.ostech.api.validator.ExisteId;

@RestController
@RequestMapping("/api/v1/ordens-servico")
@Validated
public class OrdemServicoController {

	private final Logger logger = LoggerFactory.getLogger(OrdemServicoController.class);

	@Autowired
	private OrdenServicoRepository ordemRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OrdemServicoPdf pdf;

	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<InfoOrdemServicoResponse> detalhe(
			@PathVariable @ExisteId(atributo = "id", entidade = OrdemServico.class) Long ordemServicoId) {

		var ordemServico = ordemRepository.findById(ordemServicoId).get();
		return ResponseEntity.ok(new InfoOrdemServicoResponse(ordemServico));
	}

	@GetMapping("/{clienteId}/cliente")
	public List<InfoOrdemServicoResponse> listaTodasOrdensSevicoCliente(
			@PathVariable @ExisteId(atributo = "id", entidade = Cliente.class) Long clienteId) {
		var clientes = ordemRepository.findAllByClienteId(clienteId);
		return clientes.stream().map(InfoOrdemServicoResponse::new).collect(Collectors.toList());
	}

	@GetMapping
	public List<InfoOrdemServicoResponse> lista() {
		var clientes = ordemRepository.findAll();
		return clientes.stream().map(InfoOrdemServicoResponse::new).collect(Collectors.toList());
	}

	@PostMapping
	public ResponseEntity<OrdemServicoCadastraResponse> cadastra(@RequestBody @Valid NovaOrdemServicoRequest ordemRequest,
			UriComponentsBuilder builder) {

		var cliente = clienteRepository.findById(ordemRequest.getClienteId()).get();
		var ordemServico = ordemRequest.converterParaEntidade(cliente);
		ordemRepository.save(ordemServico);

		URI uri = builder.path("/api/v1/ordens-servico/{ordemServicoId}").build(ordemServico.getId());
		logger.info("criado a ordem={} para o cliente={}", ordemServico.getId(), cliente.getNome());

		return ResponseEntity.created(uri).body(new OrdemServicoCadastraResponse(ordemServico.getId()));
	}

	@DeleteMapping("/{ordemServicoId}")
	public ResponseEntity<?> remove(
			@PathVariable @ExisteId(atributo = "id", entidade = OrdemServico.class) Long ordemServicoId) {

		var cliente = ordemRepository.findById(ordemServicoId).get();

		ordemRepository.delete(cliente);

		return ResponseEntity.noContent().build();

	}
	
	@PutMapping("/{ordemServicoId}")
	public ResponseEntity<InfoOrdemServicoResponse> atualiza(
			@PathVariable @ExisteId(atributo = "id", entidade = OrdemServico.class) Long ordemServicoId,
			@RequestBody @Valid AtualizaOrdemServicoRequest atualizaOrdemServicoRequest) {

		var ordemServico = ordemRepository.findById(ordemServicoId).get();
		var cliente = clienteRepository.findById(atualizaOrdemServicoRequest.getClienteId()).get();
		
		ordemServico.atualiza(atualizaOrdemServicoRequest.converterParaEntidade(cliente));
		logger.info("atualizado a Ordem Servico={}", ordemServico.getId());

		ordemRepository.save(ordemServico);

		return ResponseEntity.ok(new InfoOrdemServicoResponse(ordemServico));
	}

	@GetMapping("/{ordemServicoId}/relatorio")
	public ResponseEntity<byte[]> relatorioPorPessoa(@PathVariable Long ordemServicoId) throws Exception {
		var ordemServico = ordemRepository.findById(ordemServicoId).get();

		byte[] relatorio = pdf.relatorioCompleto(new OrdemServicoPdfResponse(ordemServico));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);
	}

}
