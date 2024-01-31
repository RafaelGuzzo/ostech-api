package br.com.ostech.controller.request;

import br.com.ostech.model.SystemConfiguration;
import org.springframework.web.multipart.MultipartFile;

import static br.com.ostech.utils.Utils.convertToBase64;

public class SystemConfigurationRequest {

    private Long id;
    private String description;
    private MultipartFile logo;
    private boolean isDefault;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLogo(MultipartFile logo) {
        this.logo = logo;
    }

    public void setisDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public MultipartFile getLogo() {
        return logo;
    }

    public boolean isisDefault() {
        return isDefault;
    }

    public SystemConfiguration convertToModel() {
        String logoConverted = convertToBase64(logo);
        return new SystemConfiguration(logoConverted, this.description, this.isDefault);
    }
}
