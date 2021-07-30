package br.com.ostech.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ostech.api.controller.dto.AtualizaClienteRequest;
import br.com.ostech.api.controller.dto.InfoClienteResponse;
import br.com.ostech.api.controller.dto.NovoClienteRequest;
import br.com.ostech.api.repository.ClienteRepository;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

	private final Logger logger = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteRepository repository;

	@GetMapping("/{clienteId}")
	public ResponseEntity<InfoClienteResponse> detalhe(@PathVariable Long clienteId) {
		var cliente = repository.findById(clienteId)
				.orElseThrow(() -> new IllegalArgumentException("Cliente não existe com o id informado"));

		return ResponseEntity.ok(new InfoClienteResponse(cliente));
	}

	@GetMapping
	public List<InfoClienteResponse> lista() {
		var clientes = repository.findAll();
		return clientes.stream().map(InfoClienteResponse::new).collect(Collectors.toList());
	}

	@PostMapping
	public ResponseEntity<?> cadastra(@RequestBody @Valid NovoClienteRequest clienteRequest,
			UriComponentsBuilder builder) {

		var cliente = repository.save(clienteRequest.converterParaCliente());
		logger.info("criado o cliente={}", cliente.getNome());

		URI uri = builder.path("/api/v1/clientes/{clienteId}").build(cliente.getId());

		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<?> remove(@PathVariable Long clienteId) {
		var cliente = repository.findById(clienteId)
				.orElseThrow(() -> new IllegalArgumentException("Cliente não existe com o id informado"));

		repository.delete(cliente);

		return ResponseEntity.noContent().build();

	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<InfoClienteResponse> atualiza(@PathVariable Long clienteId,
			@RequestBody @Valid AtualizaClienteRequest atualizaClienteRequest) {

		var cliente = repository.findById(clienteId)
				.orElseThrow(() -> new IllegalArgumentException("Cliente não existe com o id informado"));

		cliente.atualiza(atualizaClienteRequest.converterParaCliente());
		logger.info("atualizado o cliente={}", cliente.getNome());

		repository.save(cliente);

		return ResponseEntity.ok(new InfoClienteResponse(cliente));
	}

}
