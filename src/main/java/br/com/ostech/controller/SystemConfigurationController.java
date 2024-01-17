package br.com.ostech.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/system/configuration", produces = APPLICATION_JSON_VALUE)
public class SystemConfigurationController {
}
