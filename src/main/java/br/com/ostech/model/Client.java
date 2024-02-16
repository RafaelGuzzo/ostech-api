package br.com.ostech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    @NotNull
    private String name;
    private String email;
    @Column(name = "document_number")
    private String documentNumber;
    private String contact;
    private String phone;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;


    private Client(ClientBuilder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.documentNumber = builder.documentNumber;
        this.contact = builder.contact;
        this.phone = builder.phone;
        this.address = builder.address;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public Client update(Client client) {
        this.name = client.getName();
        this.email = client.getEmail();
        this.documentNumber = client.getDocumentNumber();
        this.contact = client.getContact();
        this.phone = client.getPhone();
        this.address = client.getAddress();
        this.createAt = client.getCreateAt();
        this.updateAt = LocalDateTime.now();

        return this;
    }

    public static class ClientBuilder {
        private String name;
        private String email;
        private String documentNumber;
        private String contact;
        private String phone;
        private Address address;

        public ClientBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ClientBuilder email(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder documentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public ClientBuilder contact(String contact) {
            this.contact = contact;
            return this;
        }

        public ClientBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public ClientBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }

}
