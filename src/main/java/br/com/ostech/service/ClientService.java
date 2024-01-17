package br.com.ostech.service;

import br.com.ostech.controller.request.CreateClientRequest;
import br.com.ostech.controller.request.UpdateClientRequest;
import br.com.ostech.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    Page<Client> findAll(String name, String documentNumber, Pageable pageable);

    Client save(CreateClientRequest client);

    List<Client> findByClientNameAnddocumentNumber(String name, String documentNumber);

    List<Client> findByClientName(String name);

    List<Client> findByClientdocumentNumber(String documentNumber);

    Client update(Long clientId, UpdateClientRequest clientRequest);

    void delete(Long clientId);
}
