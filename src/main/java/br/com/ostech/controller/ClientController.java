package br.com.ostech.controller;

import br.com.ostech.controller.request.ClientRequest;
import br.com.ostech.controller.response.ClientResponse;
import br.com.ostech.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/client", produces = APPLICATION_JSON_VALUE)
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponse>> getAllClients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String documentNumber,
            Pageable pageable) {

        Page<ClientResponse> clients = clientService.findAll(name, documentNumber, pageable)
                .map(ClientResponse::new);

        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<ClientResponse> addUpdateClient(@Valid @RequestBody ClientRequest client) {
        ClientResponse newClient = new ClientResponse(clientService.save(client));

        return ResponseEntity.ok(newClient);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponse> getOneClient(@PathVariable UUID clientId) {
        ClientResponse clientFound = new ClientResponse(clientService.findByClientId(clientId));

        return ResponseEntity.ok(clientFound);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> removeClient(@PathVariable UUID clientId) {
        clientService.delete(clientId);

        return ResponseEntity.ok("client successfully deleted");
    }
}
