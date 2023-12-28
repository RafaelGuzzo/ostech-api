package br.com.ostech.controller.request;

import br.com.ostech.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UpdateClientRequest {

    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;
    @Email
    @NotBlank
    @NotEmpty
    private String email;
    private String cpf;
    private String contact;
    @NotBlank
    @NotEmpty
    private String phone;
    @NotBlank
    @NotEmpty
    private Address address;

    public UpdateClientRequest(String name, String email, String cpf, String contact, String phone, Address address) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.contact = contact;
        this.phone = phone;
        this.address = address;
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
}
