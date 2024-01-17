package br.com.ostech.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "system_configuration")
@DynamicUpdate
public class SystemConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String logo;

    private String description;

    private boolean principal;

    private LocalDateTime createAt = LocalDateTime.now();
    private LocalDateTime updateAt = LocalDateTime.now();

    public SystemConfiguration(Long id, String logo, String description, boolean principal) {
        this.id = id;
        this.logo = logo;
        this.description = description;
        this.principal = principal;
    }

    public Long getId() {
        return id;
    }

    public String getLogo() {
        return logo;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }
}
