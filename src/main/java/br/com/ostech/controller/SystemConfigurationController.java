package br.com.ostech.controller;

import br.com.ostech.controller.request.SystemConfigurationRequest;
import br.com.ostech.controller.response.SystemConfigurationResponse;
import br.com.ostech.service.SystemConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/system/configuration", produces = APPLICATION_JSON_VALUE)
public class SystemConfigurationController {

    private final SystemConfigurationService systemConfigurationService;

    @Autowired
    public SystemConfigurationController(SystemConfigurationService systemConfigurationService) {
        this.systemConfigurationService = systemConfigurationService;
    }

    @GetMapping
    public ResponseEntity<Page<SystemConfigurationResponse>> getAllConfigs(@PageableDefault Pageable pageable) {
        Page<SystemConfigurationResponse> configuration = systemConfigurationService.findAll(pageable)
                .map(SystemConfigurationResponse::new);
        return ResponseEntity.ok(configuration);
    }

    @GetMapping("/{systemConfigurationId}")
    public ResponseEntity<SystemConfigurationResponse> getOneConfig(@PathVariable Long systemConfigurationId) {
        SystemConfigurationResponse systemConfigurationFound = new SystemConfigurationResponse(
                systemConfigurationService.getSystemConfigurationBy(systemConfigurationId)
        );
        return ResponseEntity.ok(systemConfigurationFound);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<SystemConfigurationResponse> save(@ModelAttribute SystemConfigurationRequest request) {
        SystemConfigurationResponse systemConfigurationSaved = new SystemConfigurationResponse(
                systemConfigurationService.save(request)
        );
        return ResponseEntity.ok(systemConfigurationSaved);
    }

    @DeleteMapping("/{systemConfigurationId}")
    public ResponseEntity<?> removeClient(@PathVariable Long systemConfigurationId) {
        systemConfigurationService.delete(systemConfigurationId);
        return ResponseEntity.ok("systemConfiguration successfully deleted");
    }
}
