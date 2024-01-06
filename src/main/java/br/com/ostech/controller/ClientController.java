package br.com.ostech.controller;

import br.com.ostech.controller.request.CreateClientRequest;
import br.com.ostech.controller.request.UpdateClientRequest;
import br.com.ostech.controller.response.ClientResponse;
import br.com.ostech.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientResponse>>getAllClients(){
        List<ClientResponse> clients = clientService.findAll().stream()
                .map(client -> new ClientResponse(client))
                .collect(Collectors.toList());

        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<ClientResponse> addClient(@Valid @RequestBody CreateClientRequest client){
        ClientResponse newClient = new ClientResponse(clientService.save(client));

        return ResponseEntity.ok(newClient);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getOneClient(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) String cpf) {

        if (bothParamsPresent(name, cpf)) {
            return searchByBothParams(name, cpf);
        } else if (name != null) {
            return searchByName(name);
        } else if (cpf != null) {
            return searchByCpf(cpf);
        } else {
            return ResponseEntity.badRequest().body("Provide at least one parameter (name or cpf) for the search");
        }
    }

    @PutMapping("/update/{clientId}")
    public ResponseEntity<ClientResponse> updateClient(@Valid @PathVariable Long clientId,
                                                       @RequestBody UpdateClientRequest clientRequest){
        ClientResponse clientUpdated = new ClientResponse(clientService.update(clientId,
                clientRequest));

        return ResponseEntity.ok(clientUpdated);
    }

    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<?> removeClient(@PathVariable Long clientId){
        clientService.delete(clientId);

        return ResponseEntity.ok("client successfully deleted");
    }

    private ResponseEntity<?> searchByBothParams(String name, String cpf){
        List<ClientResponse> clientFound = clientService.findByClientNameAndCpf(name, cpf)
                .stream()
                .map(client -> new ClientResponse(client))
                .collect(Collectors.toList());
        if(clientFound.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        } else {
            return ResponseEntity.ok(clientFound);
        }
    }

    private ResponseEntity<?> searchByName(String name){
        List<ClientResponse> clientFound = clientService.findByClientName(name)
                .stream()
                .map(client -> new ClientResponse(client))
                .collect(Collectors.toList());
        return searchResult(clientFound);
    }

    private ResponseEntity<?> searchByCpf(String cpf) {
        List<ClientResponse> clientFound = clientService.findByClientCpf(cpf)
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

    private boolean bothParamsPresent(String name, String cpf) {
        return name != null && cpf != null;
    }
}
