package br.com.ostech.service;

import br.com.ostech.controller.request.CreateClientRequest;
import br.com.ostech.controller.request.UpdateClientRequest;
import br.com.ostech.controller.response.ClientResponse;
import br.com.ostech.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClientService {
    List<Client> findAll();

    Client save(CreateClientRequest client);

    Optional<ClientResponse> findByClientNameAndCpf(String name, String cpf);

    List<Client> findByClientName(String name);

    List<Client> findByClientCpf(String cpf);

    Client update(Long clientId, UpdateClientRequest clientRequest);

    void delete(Long clientId);
}
