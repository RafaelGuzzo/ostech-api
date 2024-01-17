package br.com.ostech.controller.response;

import br.com.ostech.enuns.OrderStatus;
import br.com.ostech.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderResponse(
        Long id,
        String equipament,
        String description,
        BigDecimal price,
        OrderStatus status,
        LocalDate openDate,
        LocalDate endDate) {
    public OrderResponse(Order order){
        this(
                order.getId(),
                order.getEquipament(),
                order.getDescription(),
                order.getPrice(),
                order.getStatus(),
                order.getOpenDate(),
                order.getEndDate()
        );
    }
}
