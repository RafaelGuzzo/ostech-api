//package br.com.ostech.service.impl;
//
//import br.com.ostech.controller.request.CreateClientRequest;
//import br.com.ostech.controller.request.UpdateClientRequest;
//import br.com.ostech.exception.RuleException;
//import br.com.ostech.model.Address;
//import br.com.ostech.model.Client;
//import br.com.ostech.repository.ClientRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//public class ClientServiceImplTest {
//
//    @Autowired
//    ClientServiceImpl clientServiceImpl;
//
//    @MockBean
//    ClientRepository clientRepository;
//
//
//    @Test
//    public void testFindAll() {
//        Address address1 = new Address("Rua A", "Bairro X", "12345678", "Cidade Y", "Casa", "123", "PR");
//        Address address2 = new Address("Rua B", "Bairro Y", "12345987", "Cidade X", "Apartamento", "12", "PR");
//        Client client1 = new Client("João","joao@teste.com","12345678912","Teste","45991225683",address1);
//        Client client2 = new Client("Maria","maria@teste.com","12345678913","Teste","45999236685",address2);
//        List<Client> clients = Arrays.asList(client1, client2);
//
//        when(clientRepository.findAll()).thenReturn(clients);
//
//        List<Client> result = clientServiceImpl.findAll();
//
//        verify(clientRepository, times(1)).findAll();
//
//        assertEquals(clients, result);
//    }
//
//
//    @Test
//    public void testSave() {
//        Address address1 = new Address("Rua A", "Bairro X", "12345678", "Cidade Y", "Casa", "123", "PR");
//        CreateClientRequest createRequest = new CreateClientRequest("João","joao@teste.com","12345678912","Teste","45991225683",address1);
//        Client newClient = createRequest.convertToModel();
//
//        when(clientRepository.save(any(Client.class))).thenReturn(newClient);
//
//        Client savedClient = clientServiceImpl.save(createRequest);
//
//        verify(clientRepository, times(1)).save(any(Client.class));
//
//        assertEquals(newClient, savedClient);
//    }
//
//
//    @Test
//    public void testCheckExistence_ClientExists() {
//        Long clientId = 1L;
//        Address address1 = new Address("Rua A", "Bairro X", "12345678", "Cidade Y", "Casa", "123", "PR");
//        Client existingClient = new Client("João","joao@teste.com","12345678912","Teste","45991225683",address1);
//
//        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
//
//        Client result = clientServiceImpl.checkExistence(clientId);
//
//        verify(clientRepository, times(1)).findById(clientId);
//
//        assertEquals(existingClient, result);
//    }
//
//    @Test
//    public void testCheckExistence_ClientDoesNotExist() {
//        Long clientId = 1L;
//
//        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());
//
//        assertThrows(RuleException.class, () -> clientServiceImpl.checkExistence(clientId));
//
//        verify(clientRepository, times(1)).findById(clientId);
//    }
//
//    @Test
//    public void testFindByClientName_ClientExists() {
//        String clientName = "João";
//        Address address1 = new Address("Rua A", "Bairro X", "12345678", "Cidade Y", "Casa", "123", "PR");
//        List<Client> clients = Arrays.asList(new Client("João","joao@teste.com","12345678912","Teste","45991225683",address1));
//
//        when(clientRepository.findByName(clientName)).thenReturn(clients);
//
//        List<Client> result = clientServiceImpl.findByClientName(clientName);
//
//        verify(clientRepository, times(1)).findByName(clientName);
//
//        assertEquals(clients, result);
//    }
//
//    @Test
//    public void testFindByClientName_NoClientsFound() {
//        String clientName = "Luana";
//
//        when(clientRepository.findByName(clientName)).thenReturn(Collections.emptyList());
//
//        List<Client> result = clientServiceImpl.findByClientName(clientName);
//
//        verify(clientRepository, times(1)).findByName(clientName);
//
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    public void testFindByClientdocumentNumber_ClientExists() {
//        String clientdocumentNumber = "12345678912";
//        Address address1 = new Address("Rua A", "Bairro X", "12345678", "Cidade Y", "Casa", "123", "PR");
//        List<Client> clients = Arrays.asList(new Client("João","joao@teste.com","12345678912","Teste","45991225683",address1));
//
//        when(clientRepository.findBydocumentNumber(clientdocumentNumber)).thenReturn(clients);
//
//        List<Client> result = clientServiceImpl.findByClientdocumentNumber(clientdocumentNumber);
//
//        verify(clientRepository, times(1)).findBydocumentNumber(clientdocumentNumber);
//
//        assertEquals(clients, result);
//    }
//
//    @Test
//    public void testFindByClientdocumentNumber_NoClientsFound() {
//        String clientdocumentNumber = "99999999999";
//
//        when(clientRepository.findBydocumentNumber(clientdocumentNumber)).thenReturn(Collections.emptyList());
//
//        List<Client> result = clientServiceImpl.findByClientdocumentNumber(clientdocumentNumber);
//
//        verify(clientRepository, times(1)).findBydocumentNumber(clientdocumentNumber);
//
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    public void testUpdate_ClientExists() {
//        Long clientId = 1L;
//        Address address1 = new Address("Rua A", "Bairro X", "12345678", "Cidade Y", "Casa", "123", "PR");
//        UpdateClientRequest updateRequest = new UpdateClientRequest("João Marcos","joaomarcos@teste.com","12345678912","Teste","45991225683",address1);
//        Client existingClient = new Client("João","joao@teste.com","12345678912","Teste","45991225683",address1);
//
//        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
//        when(clientRepository.save(any(Client.class))).thenReturn(existingClient);
//
//        Client result = clientServiceImpl.update(clientId, updateRequest);
//
//        verify(clientRepository, times(1)).findById(clientId);
//
//        verify(clientRepository, times(1)).save(any(Client.class));
//
//        assertEquals(existingClient, result);
//    }
//
//    @Test
//    public void testUpdate_ClientNotFound() {
//        Long clientId = 1L;
//        Address address1 = new Address("Rua A", "Bairro X", "12345678", "Cidade Y", "Casa", "123", "PR");
//        UpdateClientRequest updateRequest = new UpdateClientRequest("Luana Patricia","luanapatricia@teste.com","12345678913","Teste","45991225683",address1);
//
//        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());
//
//        assertThrows(RuleException.class, () -> clientServiceImpl.update(clientId, updateRequest));
//
//        verify(clientRepository, times(1)).findById(clientId);
//
//        verify(clientRepository, never()).save(any(Client.class));
//    }
//
//    @Test
//    public void testDelete_ClientExists() {
//        Long clientId = 1L;
//        Address address1 = new Address("Rua A", "Bairro X", "12345678", "Cidade Y", "Casa", "123", "PR");
//        Client existingClient = new Client("João","joao@teste.com","12345678912","Teste","45991225683",address1);
//
//        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
//
//        clientServiceImpl.delete(clientId);
//
//        verify(clientRepository, times(1)).findById(clientId);
//
//        verify(clientRepository, times(1)).deleteById(clientId);
//    }
//
//    @Test
//    public void testDelete_ClientNotFound() {
//        Long clientId = 1L;
//
//        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());
//
//        assertThrows(RuleException.class, () -> clientServiceImpl.delete(clientId));
//
//        verify(clientRepository, times(1)).findById(clientId);
//
//        verify(clientRepository, never()).deleteById(clientId);
//    }
//}
