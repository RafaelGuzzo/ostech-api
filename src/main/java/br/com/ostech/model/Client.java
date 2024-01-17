package br.com.ostech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String name;
    private String email;
    private String cpf;
    private String contact;
    private String phone;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Client() {
    }

    public Client(ClientBuilder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.cpf = builder.cpf;
        this.contact = builder.contact;
        this.phone = builder.phone;
        this.address = builder.address;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public Client update(Client client){
        this.name = client.getName();
        this.email = client.getEmail();
        this.cpf = client.getCpf();
        this.contact = client.getContact();
        this.phone = client.getPhone();
        this.address = client.getAddress();
        this.updateAt = LocalDateTime.now();

        return this;
    }

    public static class ClientBuilder {
        private String name;
        private String email;
        private String cpf;
        private String contact;
        private String phone;
        private Address address;

        public ClientBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ClientBuilder email(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public ClientBuilder contact(String contact) {
            this.contact = contact;
            return this;
        }

        public ClientBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public ClientBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public Client build(){
            return new Client(this);
        }
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

    public String getCpf() {
        return cpf;
    }

    public String getContact() {
        return contact;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public Address getAddress() {
        return address;
    }
}
