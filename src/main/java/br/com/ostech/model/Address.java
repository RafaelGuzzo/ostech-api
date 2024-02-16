package br.com.ostech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
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

    public Address(AddressBuilder builder) {
        this.street = builder.street;
        this.district = builder.district;
        this.zipCode = builder.zipCode;
        this.city = builder.city;
        this.complement = builder.complement;
        this.number = builder.number;
        this.uf = builder.uf;
    }

    public Address update(Address address){
        this.street = address.getStreet();
        this.district = address.getDistrict();
        this.zipCode = address.getZipCode();
        this.city = address.getCity();
        this.complement = address.getComplement();
        this.number = address.getNumber();
        this.uf = address.getUf();

        return this;
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

        public Address build(){
            return new Address(this);
        }
    }
}
