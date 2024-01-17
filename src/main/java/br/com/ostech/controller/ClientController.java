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

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponse>>getAllClients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String cpf,
            Pageable pageable){

        Page<ClientResponse> clients = clientService.findAll(name, cpf, pageable)
                .map(ClientResponse::new);

        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<ClientResponse> addUpdateClient(@Valid @RequestBody ClientRequest client){
        ClientResponse newClient = new ClientResponse(clientService.save(client));

        return ResponseEntity.ok(newClient);
    }

    @GetMapping("/search")
    public ResponseEntity<ClientResponse> getOneClient(@RequestParam UUID clientId) {
        ClientResponse clientFound = new ClientResponse(clientService.findByClientId(clientId));

        return ResponseEntity.ok(clientFound);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeClient(@RequestParam UUID clientId){
        clientService.delete(clientId);

        return ResponseEntity.ok("client successfully deleted");
    }
}
