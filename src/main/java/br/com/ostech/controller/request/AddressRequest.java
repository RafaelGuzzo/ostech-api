package br.com.ostech.controller.request;

import br.com.ostech.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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