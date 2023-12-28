package br.com.ostech.service.impl;

import br.com.ostech.controller.request.CreateClientRequest;
import br.com.ostech.controller.request.UpdateClientRequest;
import br.com.ostech.controller.response.ClientResponse;
import br.com.ostech.exception.RuleException;
import br.com.ostech.model.Client;
import br.com.ostech.repository.ClientRepository;
import br.com.ostech.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client save(CreateClientRequest client) {

        Client newClient = client.convertToModel();

        return clientRepository.save(newClient);
    }

    @Override
    public Optional<ClientResponse> findByClientNameAndCpf(String name, String cpf) {
        return Optional.empty();
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
