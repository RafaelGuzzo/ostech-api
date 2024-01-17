package br.com.ostech.controller.request;

import br.com.ostech.model.Client;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class ClientRequest {

    private UUID id;
    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;
    @Email
    @NotBlank
    @NotEmpty
    private String email;
    private String documentNumber;
    private String contact;
    @NotBlank
    @NotEmpty
    private String phone;
    @Valid
    private AddressRequest address;


    public ClientRequest() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getdocumentNumber() {
        return documentNumber;
    }

    public String getContact() {
        return contact;
    }

    public String getPhone() {
        return phone;
    }

    public AddressRequest getAddress() {
        return address;
    }

    public Client convertToModel() {
        return new Client.ClientBuilder()
                .name(name)
                .email(email)
                .documentNumber(documentNumber)
                .contact(contact)
                .phone(phone)
                .address(address.convertToModel())
                .build();
    }
}
