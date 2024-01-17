package br.com.ostech.controller.request;

import br.com.ostech.enuns.OrderStatus;
import br.com.ostech.model.Client;
import br.com.ostech.model.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderRequest {

    private Long id;
    @NotNull
    private ClientRequest client;
    @NotBlank
    @NotEmpty
    private String equipament;
    @NotBlank
    @NotEmpty
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private OrderStatus status;
    @NotNull
    private LocalDate openDate;
    private LocalDate endDate;


    public Long getId() {
        return id;
    }

    public ClientRequest getClient() {
        return client;
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

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }


    public Order convertToModel(Client verifiedClient) {
        return new Order.OrderBuilder()
                .client(verifiedClient)
                .equipament(equipament)
                .description(description)
                .price(price)
                .status(status)
                .openDate(openDate)
                .endDate(endDate)
                .build();
    }

}
