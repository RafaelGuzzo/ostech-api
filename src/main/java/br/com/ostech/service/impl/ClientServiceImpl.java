package br.com.ostech.service.impl;

import br.com.ostech.controller.request.ClientRequest;
import br.com.ostech.exception.ClientNotFoundException;
import br.com.ostech.model.Client;
import br.com.ostech.repository.ClientRepository;
import br.com.ostech.repository.OrderRepository;
import br.com.ostech.repository.specification.ClientSpecification;
import br.com.ostech.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    private final OrderRepository orderRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<Client> findAll(String name, String documentNumber, Pageable pageable) {
        Specification<Client> specification = ClientSpecification.filterBy(name, documentNumber);
        return clientRepository.findAll(specification, pageable);
    }

    @Override
    public Client save(ClientRequest clientRequest) {
        if (clientRequest.getId() != null) {
            Client clientFounded = this.findByClient(clientRequest.getId());
            clientFounded.update(clientRequest.convertToModel());
            return clientRepository.save(clientFounded);
        }
        Client newClient = clientRequest.convertToModel();
        return clientRepository.save(newClient);
    }

    @Override
    public Client findByClientId(UUID clientId) {
        return findByClient(clientId);
    }

    @Override
    @Transactional
    public void delete(UUID clientId) {
        Client client = findByClient(clientId);
        orderRepository.deleteByClient(client);
        clientRepository.delete(client);
    }

    private Client findByClient(UUID clientId) {
        return clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
    }
}
