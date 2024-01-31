package br.com.ostech.controller.response;

import br.com.ostech.model.SystemConfiguration;

import java.time.LocalDateTime;

public record SystemConfigurationResponse(
        Long id,
        String description,
        String logo,
        boolean isDefault,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
    public SystemConfigurationResponse(SystemConfiguration systemConfiguration) {
        this(
                systemConfiguration.getId(),
                systemConfiguration.getDescription(),
                systemConfiguration.getLogo(),
                systemConfiguration.isDefault(),
                systemConfiguration.getCreateAt(),
                systemConfiguration.getUpdateAt()
        );
    }
}
