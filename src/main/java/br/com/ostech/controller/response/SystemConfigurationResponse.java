package br.com.ostech.controller.response;

import java.time.LocalDateTime;

public record SystemConfigurationResponse(
        Long id,
        String description,
        String logo,
        boolean principal,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
}
