package br.com.ostech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String email;
    private String cpf;
    private String contact;
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true)
    private Address address;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Client(String name, String email, String cpf, String contact, String phone, Address address) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.contact = contact;
        this.phone = phone;
        this.address = address;
    }

    public Long getId() {
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

    public Address getAddress() {
        return address;
    }

    public LocalDateTime getCreateAt() {return createAt;}

    public LocalDateTime getUpdateAt() {return updateAt;}

    public Client updateClient(String name, String email, String cpf, String contact,
                               String phone, Address address){
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.contact = contact;
        this.phone = phone;
        this.address = address;

        return this;
    }

    public Client() {
    }
}
