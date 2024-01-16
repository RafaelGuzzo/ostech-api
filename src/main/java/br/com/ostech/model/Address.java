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
    private String district;
    private String zipCode;
    private String city;
    private String complement;
    @NotNull
    private String number;
    private String uf;

    private Address(AddressBuilder builder) {
        this.street = builder.street;
        this.district = builder.district;
        this.zipCode = builder.zipCode;
        this.city = builder.city;
        this.complement = builder.complement;
        this.number = builder.number;
        this.uf = builder.uf;
    }

    public Address() {
    }

    public static class AddressBuilder {
        private String street;
        private String district;
        private String number;
        private String zipCode;
        private String city;
        private String complement;
        private String uf;

        public AddressBuilder street(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder district(String district) {
            this.district = district;
            return this;
        }

        public AddressBuilder number(String number) {
            this.number = number;
            return this;
        }

        public AddressBuilder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder complement(String complement) {
            this.complement = complement;
            return this;
        }

        public AddressBuilder uf(String uf) {
            this.uf = uf;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }

    public String getZipCode() {
        return zipCode;
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
