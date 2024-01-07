package br.com.ostech.model;

import br.com.ostech.enuns.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "order_service")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Client client;
    @NotNull
    private String equipament;
    @NotNull
    private String description;
    private BigDecimal price;
    private OrderStatus status;
    private LocalDate openDate;
    private LocalDate endDate;

    public Order(Client client, String equipament, String description, BigDecimal price, OrderStatus status, LocalDate openDate, LocalDate endDate) {
        this.client = client;
        this.equipament = equipament;
        this.description = description;
        this.price = price;
        this.status = status;
        this.openDate = openDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
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
}
