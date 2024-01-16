package br.com.ostech.service.impl;

import br.com.ostech.controller.request.CreateClientRequest;
import br.com.ostech.controller.request.UpdateClientRequest;
import br.com.ostech.exception.RuleException;
import br.com.ostech.model.Client;
import br.com.ostech.repository.ClientRepository;
import br.com.ostech.repository.specification.ClientSpecification;
import br.com.ostech.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Page<Client> findAll(String name, String cpf, Pageable pageable) {
        Specification<Client> specification = ClientSpecification.filterBy(name, cpf);
        return clientRepository.findAll(specification, pageable);
    }

    @Override
    public Client save(CreateClientRequest client) {

        Client newClient = client.convertToModel();

        return clientRepository.save(newClient);
    }

    @Override
    public List<Client> findByClientNameAndCpf(String name, String cpf) {
        return clientRepository.findByNameAndCpf(name, cpf);
    }

    @Override
    public List<Client> findByClientName(String name) {
        return clientRepository.findByName(name);
    }

    @Override
    public List<Client> findByClientCpf(String cpf) {
        return clientRepository.findByCpf(cpf);
    }

    @Override
    public Client update(Long clientId, UpdateClientRequest clientRequest) {
        Client client = checkExistence(clientId);
        Client clientUpdated = client.updateClient(clientRequest.getName(),
                clientRequest.getEmail(),clientRequest.getCpf(),clientRequest.getContact(),clientRequest.getPhone(),
                clientRequest.getAddress());

        return clientRepository.save(clientUpdated);
    }

    @Override
    public void delete(Long clientId) {
        checkExistence(clientId);

        clientRepository.deleteById(clientId);
    }

    public Client checkExistence(Long clientId){
        Optional<Client> client = clientRepository.findById(clientId);

        if(client.isEmpty()){

            throw new RuleException("Client not found");
        }

        return client.get();
    }
}
