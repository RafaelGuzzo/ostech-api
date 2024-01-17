package br.com.ostech.controller.response;

import br.com.ostech.model.Address;
import br.com.ostech.model.Client;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClientResponse(
        UUID id,
        String name,
        String email,
        String documentNumber,
        String contact,
        String phone,
        Address address,
        LocalDateTime createAt,
        LocalDateTime updateAt) {
    public ClientResponse(Client client) {
        this(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getDocumentNumber(),
                client.getContact(),
                client.getPhone(),
                client.getAddress(),
                client.getCreateAt(),
                client.getUpdateAt()
        );

    }
}
