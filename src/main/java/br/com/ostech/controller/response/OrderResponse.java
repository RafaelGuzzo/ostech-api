package br.com.ostech.controller.response;

import br.com.ostech.enuns.OrderStatus;
import br.com.ostech.model.Client;
import br.com.ostech.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(
        Long id,
        String equipament,
        String description,
        BigDecimal price,
        OrderStatus status,
        LocalDateTime createAt,
        LocalDateTime updateAt,
        ClientOrderResponse client) {

    public OrderResponse(Order order) {
        this(
                order.getId(),
                order.getEquipament(),
                order.getDescription(),
                order.getPrice(),
                order.getStatus(),
                order.getCreateAt(),
                order.getUpdateAt(),
                new ClientOrderResponse(order.getClient())
        );
    }
}

record ClientOrderResponse(
        String clientId,
        String name,
        String documentNumber,
        String contact,
        String phone) {

    public ClientOrderResponse(Client client) {
        this(
                client.getId().toString(),
                client.getName(),
                client.getDocumentNumber(),
                client.getContact(),
                client.getPhone()
        );
    }
}
