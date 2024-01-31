package br.com.ostech.service.impl;

import br.com.ostech.controller.request.SystemConfigurationRequest;
import br.com.ostech.exception.ClientNotFoundException;
import br.com.ostech.model.SystemConfiguration;
import br.com.ostech.repository.SystemConfigurationRepository;
import br.com.ostech.service.SystemConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SystemConfigurationServiceImpl implements SystemConfigurationService {

    private final SystemConfigurationRepository systemConfigurationRepository;

    @Autowired
    public SystemConfigurationServiceImpl(SystemConfigurationRepository systemConfigurationRepository) {
        this.systemConfigurationRepository = systemConfigurationRepository;
    }


    @Override
    public Page<SystemConfiguration> findAll(Pageable pageable) {
        return systemConfigurationRepository.findAll(pageable);
    }

    @Override
    public SystemConfiguration getSystemConfigurationBy(Long id) {
        return findBy(id);
    }

    @Override
    public SystemConfiguration save(SystemConfigurationRequest systemConfiguration) {
        if (systemConfiguration.getId() != null) {
            SystemConfiguration found = findBy(systemConfiguration.getId());
            found.update(systemConfiguration.convertToModel());
            return systemConfigurationRepository.save(found);
        }
        SystemConfiguration newSystemConfiguration = systemConfiguration.convertToModel();
        return systemConfigurationRepository.save(newSystemConfiguration);
    }

    @Override
    public void delete(Long id) {
        SystemConfiguration systemConfiguration = findBy(id);
        systemConfigurationRepository.delete(systemConfiguration);
    }

    private SystemConfiguration findBy(Long id) {
        Optional<SystemConfiguration> systemConfiguration = systemConfigurationRepository.findById(id);

        if (systemConfiguration.isEmpty()) {
            //TODO trocar
            throw new ClientNotFoundException();
        }

        return systemConfiguration.get();
    }
}
