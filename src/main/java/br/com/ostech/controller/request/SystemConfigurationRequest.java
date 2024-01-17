package br.com.ostech.controller.request;

import org.springframework.web.multipart.MultipartFile;

public record SystemConfigurationRequest(String description, MultipartFile logo, boolean principal) {
}
