package br.com.ostech.service.impl;

import br.com.ostech.controller.request.AddressRequest;
import br.com.ostech.controller.request.ClientRequest;
import br.com.ostech.exception.ClientNotFoundException;
import br.com.ostech.model.Address;
import br.com.ostech.model.Client;
import br.com.ostech.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ClientServiceImplTest {


    @Autowired
    ClientServiceImpl clientService;

    @MockBean
    ClientRepository clientRepository;

/*
    @Test
    public void findAllWhenNameAndCpfProvided() {
        //When name and CPF are informed.
        String name = "João";
        String cpf = "12345678912";
        Pageable pageable = Pageable.unpaged();
        List<Client> clients = Arrays.asList(
                new Client.ClientBuilder()
                        .name("João Lucio")
                        .email("joao@teste.com")
                        .cpf("12345678912")
                        .address(new Address("Rua A", "Bairro X", "12345678", "Cidade X", "Casa", "123", "PR"))
                        .build(),
                new Client.ClientBuilder()
                        .name("John Smith")
                        .email("smith@example.com")
                        .cpf("987654321")
                        .address(new Address("Rua B", "Bairro Y", "12345987", "Cidade Y", "Apartamento", "12", "PR"))
                        .build()
        );


        when(clientRepository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(new PageImpl<>(clients));

        Page<Client> result = clientService.findAll(name, cpf, pageable);

        assertEquals(clients.size(), result.getContent().size());
    }

    @Test
    public void findAllWhenNameAndCpfNotProvided() {
        //When name and CPF aren't informed.
        Pageable pageable = Pageable.unpaged();
        List<Client> clients = Arrays.asList(
                new Client.ClientBuilder()
                        .name("João Lucio")
                        .email("joao@teste.com")
                        .cpf("12345678912")
                        .address(new Address("Rua A", "Bairro X", "12345678", "Cidade X", "Casa", "123", "PR"))
                        .build(),
                new Client.ClientBuilder()
                        .name("John Smith")
                        .email("smith@example.com")
                        .cpf("987654321")
                        .address(new Address("Rua B", "Bairro Y", "12345987", "Cidade Y", "Apartamento", "12", "PR"))
                        .build()
        );

        when(clientRepository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(new PageImpl<>(clients));

        Page<Client> result = clientService.findAll(null, null, pageable);

        assertEquals(clients.size(), result.getContent().size());
    }

    @Test
    public void findAllWhenNameAndCpfNotProvidedWithError() {
        //When name and cpf are not provided and the result returns an error.
        Pageable pageable = Pageable.unpaged();

        when(clientRepository.findAll(any(Specification.class), eq(pageable)))
                .thenThrow(new RuntimeException("Unexpected call to repository"));

        try {
            clientService.findAll(null, null, pageable);
            fail("Should throw an exception");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Unexpected call to repository"));
        }
    }

    @Test
    public void findAllWhenValidName() {
        //When name is provided and is correct.
        String validName = "João Lucio";

        Client client = new Client.ClientBuilder()
                .name(validName)
                .cpf("12345678912")
                .build();

        when(clientRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(client)));

        Page<Client> result = clientService.findAll(validName, null, Pageable.unpaged());

        assertTrue(result.getContent().contains(client));
    }

    @Test
    public void findAllWhenNameNotFound() {
        //When name is provided but not found.
        String nonExistentName = "Non Existent Name";

        when(clientRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        Page<Client> result = clientService.findAll(nonExistentName, null, Pageable.unpaged());

        assertTrue(result.isEmpty());
    }

    @Test
    public void findAllWhenNameBelowMinLength() {
        //When name is provided but is below the minimum number of characters.
        String shortName = "Ab";  //Name with less than 3 characters

        when(clientRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        Page<Client> result = clientService.findAll(shortName, null, Pageable.unpaged());

        assertTrue(result.isEmpty());
    }


    @Test
    public void testDelete() {
        UUID clientId = UUID.randomUUID();
        Client client = new Client();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        clientService.delete(clientId);

        verify(clientRepository).findById(clientId);
        verify(clientRepository).delete(client);
    }

    @Test
    public void testDeleteNonexistentClient() {
        UUID clientId = UUID.randomUUID();
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientService.delete(clientId));

        verify(clientRepository).findById(clientId);
        verify(clientRepository, never()).delete(any());
    }
    */
}
