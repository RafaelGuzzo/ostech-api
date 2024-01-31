package br.com.ostech.service;

import br.com.ostech.controller.request.SystemConfigurationRequest;
import br.com.ostech.model.SystemConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SystemConfigurationService {

    Page<SystemConfiguration> findAll(Pageable pageable);

    SystemConfiguration getSystemConfigurationBy(Long id);

    SystemConfiguration save(SystemConfigurationRequest systemConfiguration);

    void delete(Long id);
}
