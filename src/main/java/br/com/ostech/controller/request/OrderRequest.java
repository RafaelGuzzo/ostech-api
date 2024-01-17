package br.com.ostech.controller.request;

import br.com.ostech.model.Client;
import br.com.ostech.model.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class OrderRequest {

    private Long id;
    @NotNull
    private String clientId;
    @NotBlank
    @NotEmpty
    private String equipament;
    @NotBlank
    @NotEmpty
    private String description;
    @NotNull
    private BigDecimal price;

    public OrderRequest() {

    }

    public Long getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getEquipament() {
        return equipament;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Order convertToModel(Client verifiedClient) {
        return new Order.OrderBuilder()
                .client(verifiedClient)
                .equipament(equipament)
                .description(description)
                .price(price)
                .build();
    }

}
