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

    @Column(name = "is_default")
    private boolean isDefault;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public SystemConfiguration() {
    }

    public SystemConfiguration(String logo, String description, boolean isDefault) {
        this.logo = logo;
        this.description = description;
        this.isDefault = isDefault;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
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

    public boolean isDefault() {
        return isDefault;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void update(SystemConfiguration systemConfiguration) {
        this.logo = systemConfiguration.logo;
        this.description = systemConfiguration.description;
        this.isDefault = systemConfiguration.isDefault;
        this.updateAt = LocalDateTime.now();
    }
}
