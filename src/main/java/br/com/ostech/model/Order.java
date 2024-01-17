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
    @Column(name = "open_date")
    private LocalDate openDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    public Order() {
    }

    public Order(OrderBuilder builder) {
        this.client = builder.client;
        this.equipament = builder.equipament;
        this.description = builder.description;
        this.price = builder.price;
        this.status = builder.status;
        this.openDate = builder.openDate;
        this.endDate = builder.endDate;
    }

    public Order update(Order order) {
        this.client = order.getClient();
        this.equipament = order.getEquipament();
        this.description = order.getDescription();
        this.price = order.getPrice();
        this.status = order.getStatus();
        this.openDate = order.getOpenDate();
        this.endDate = order.getEndDate();

        return this;
    }

    public static class OrderBuilder {
        private Long id;
        private Client client;
        private String equipament;
        private String description;
        private BigDecimal price;
        private OrderStatus status;
        private LocalDate openDate;
        private LocalDate endDate;

        public OrderBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder client(Client client) {
            this.client = client;
            return this;
        }

        public OrderBuilder equipament(String equipament) {
            this.equipament = equipament;
            return this;
        }

        public OrderBuilder description(String description) {
            this.description = description;
            return this;
        }

        public OrderBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public OrderBuilder status(OrderStatus status) {
            this.status = status;
            return this;
        }

        public OrderBuilder openDate(LocalDate openDate) {
            this.openDate = openDate;
            return this;
        }

        public OrderBuilder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Order build(){
            return new Order(this);
        }

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
