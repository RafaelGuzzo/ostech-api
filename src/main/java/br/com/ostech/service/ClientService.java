package br.com.ostech.service;

import br.com.ostech.controller.request.ClientRequest;
import br.com.ostech.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ClientService {
    Page<Client> findAll(String name, String documentNumber, Pageable pageable);

    Client save(ClientRequest client);

    void delete(UUID clientId);

    Client findByClientId(UUID clientId);
}
