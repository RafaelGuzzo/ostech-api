package br.com.ostech.controller.response;

import br.com.ostech.model.Address;
import br.com.ostech.model.Client;

import java.time.LocalDateTime;


public class ClientResponse {

    private String name;
    private String email;
    private String cpf;
    private String contact;
    private String phone;
    private Address address;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public ClientResponse(Client client) {
        this.name = client.getName();
        this.email = client.getEmail();
        this.cpf = client.getCpf();
        this.contact = client.getContact();
        this.phone = client.getPhone();
        this.address = client.getAddress();
        this.createAt = client.getCreateAt();
        this.updateAt = client.getUpdateAt();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getContact() {
        return contact;
    }

    public String getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }
}
