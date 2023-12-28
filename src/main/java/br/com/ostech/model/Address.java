package br.com.ostech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String street;
    @NotNull
    private String neighborhood;
    private String cep;
    private String city;
    private String complement;
    @NotNull
    private String number;
    private String uf;

    @OneToOne(mappedBy = "address")
    private Client client;

    public Address(Long id, String street, String neighborhood, String cep, String city, String complement, String number, String uf) {
        this.id = id;
        this.street = street;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.city = city;
        this.complement = complement;
        this.number = number;
        this.uf = uf;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCep() {
        return cep;
    }

    public String getCity() {
        return city;
    }

    public String getComplement() {
        return complement;
    }

    public String getNumber() {
        return number;
    }

    public String getUf() {
        return uf;
    }

    public Address() {
    }
}
