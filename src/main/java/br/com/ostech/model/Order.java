package br.com.ostech.model;

import br.com.ostech.enuns.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private BigDecimal price = BigDecimal.ONE;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.OPEN;
    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();
    @Column(name = "update_at")
    private LocalDateTime updateAt = LocalDateTime.now();

    public Order() {
    }

    public Order(OrderBuilder builder) {
        this.client = builder.client;
        this.equipament = builder.equipament;
        this.description = builder.description;
        this.price = builder.price;
    }

    public Order update(Order order) {
        this.client = order.getClient();
        this.equipament = order.getEquipament();
        this.description = order.getDescription();
        this.price = order.getPrice();
        this.createAt = order.getUpdateAt();
        this.updateAt = LocalDateTime.now();

        return this;
    }

    public void changeNextStatus() {
        this.status = this.status.next();
        this.updateAt = LocalDateTime.now();
    }

    public static class OrderBuilder {
        private Long id;
        private Client client;
        private String equipament;
        private String description;
        private BigDecimal price;

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

        public Order build() {
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }
}
