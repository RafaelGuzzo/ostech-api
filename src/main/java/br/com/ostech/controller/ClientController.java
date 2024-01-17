package br.com.ostech.controller;

import br.com.ostech.controller.request.CreateClientRequest;
import br.com.ostech.controller.request.UpdateClientRequest;
import br.com.ostech.controller.response.ClientResponse;
import br.com.ostech.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponse>> getAllClients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String documentNumber,
            Pageable pageable) {

        Page<ClientResponse> clients = clientService.findAll(name, documentNumber, pageable).map(ClientResponse::new);
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<ClientResponse> addClient(@Valid @RequestBody CreateClientRequest client) {
        ClientResponse newClient = new ClientResponse(clientService.save(client));

        return ResponseEntity.ok(newClient);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getOneClient(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) String documentNumber) {

        if (bothParamsPresent(name, documentNumber)) {
            return searchByBothParams(name, documentNumber);
        } else if (name != null) {
            return searchByName(name);
        } else if (documentNumber != null) {
            return searchBydocumentNumber(documentNumber);
        } else {
            return ResponseEntity.badRequest().body("Provide at least one parameter (name or documentNumber) for the search");
        }
    }

    @PutMapping("/update/{clientId}")
    public ResponseEntity<ClientResponse> updateClient(@Valid @PathVariable Long clientId,
                                                       @RequestBody UpdateClientRequest clientRequest) {
        ClientResponse clientUpdated = new ClientResponse(clientService.update(clientId,
                clientRequest));

        return ResponseEntity.ok(clientUpdated);
    }

    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<?> removeClient(@PathVariable Long clientId) {
        clientService.delete(clientId);

        return ResponseEntity.ok("client successfully deleted");
    }

    private ResponseEntity<?> searchByBothParams(String name, String documentNumber) {
        List<ClientResponse> clientFound = clientService.findByClientNameAnddocumentNumber(name, documentNumber)
                .stream()
                .map(client -> new ClientResponse(client))
                .collect(Collectors.toList());
        if (clientFound.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        } else {
            return ResponseEntity.ok(clientFound);
        }
    }

    private ResponseEntity<?> searchByName(String name) {
        List<ClientResponse> clientFound = clientService.findByClientName(name)
                .stream()
                .map(client -> new ClientResponse(client))
                .collect(Collectors.toList());
        return searchResult(clientFound);
    }

    private ResponseEntity<?> searchBydocumentNumber(String documentNumber) {
        List<ClientResponse> clientFound = clientService.findByClientdocumentNumber(documentNumber)
                .stream()
                .map(client -> new ClientResponse(client))
                .collect(Collectors.toList());
        return searchResult(clientFound);
    }

    private ResponseEntity<?> searchResult(List<ClientResponse> clientFound) {
        if (clientFound.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        return ResponseEntity.ok(clientFound);
    }

    private boolean bothParamsPresent(String name, String documentNumber) {
        return name != null && documentNumber != null;
    }
}
