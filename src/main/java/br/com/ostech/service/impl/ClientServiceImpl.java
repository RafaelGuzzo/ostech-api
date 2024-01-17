package br.com.ostech.service.impl;

import br.com.ostech.controller.request.ClientRequest;
import br.com.ostech.exception.ClientNotFoundException;
import br.com.ostech.model.Address;
import br.com.ostech.model.Client;
import br.com.ostech.repository.AddressRepository;
import br.com.ostech.repository.ClientRepository;
import br.com.ostech.repository.specification.ClientSpecification;
import br.com.ostech.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Page<Client> findAll(String name, String cpf, Pageable pageable) {
        Specification<Client> specification = ClientSpecification.filterBy(name, cpf);
        return clientRepository.findAll(specification, pageable);
    }

    @Override
    public Client save(ClientRequest clientRequest) {
        if(clientRequest.getId() != null){
            Client clientFounded =  this.findByClient(clientRequest.getId());
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
    public void delete(UUID clientId) {
        Client client = findByClient(clientId);
        //recebe o cliente e deleta por entidade em vez de por ID

        clientRepository.delete(client);
    }

    private Client findByClient(UUID clientId){
        Optional<Client> client = clientRepository.findById(clientId);

        if(client.isEmpty()){
            //clientNotFoundException
            throw new ClientNotFoundException();
        }

        return client.get();
    }
}
