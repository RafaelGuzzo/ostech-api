package br.com.ostech.controller.request;

import br.com.ostech.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class AddressRequest {

    @NotNull
    private String street;
    @NotNull
    private String district;
    @JsonProperty("zip_code")
    private String zipCode;
    private String city;
    private String complement;
    @NotNull
    private String number;
    private String uf;

    private AddressRequest() {
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
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

    public String getUf() {
        return uf;
    }

    public Address convertToModel() {
        return new Address.AddressBuilder()
                .street(street)
                .district(district)
                .zipCode(zipCode)
                .city(city)
                .complement(complement)
                .number(number)
                .uf(uf)
                .build();
    }
}