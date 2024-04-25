package com.application.applicationapiservice.external.external_apis.impl;

import com.application.applicationapiservice.common.value.philanthropist.Philanthropist;
import com.application.applicationapiservice.controllers.dto.PhilanthropistViewResponseDTO;
import com.application.applicationapiservice.external.external_apis.IPhilanthropistServiceConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class PhilanthropistServiceConnector implements IPhilanthropistServiceConnector {
    @Value("${philanthropist-service-service.url}")
    private String url;

    @Override
    public PhilanthropistViewResponseDTO getPhilanthropistById(final String philanthropistId) {
        final String fullUrl = String.format("%s/philanthropists/%s", this.url, philanthropistId);

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<PhilanthropistViewResponseDTO> responseEntity = restTemplate.getForEntity(fullUrl, PhilanthropistViewResponseDTO.class);

        return responseEntity.getBody();
    }

    @Override
    public PhilanthropistViewResponseDTO createPhilanthropist(final Philanthropist philanthropist) {
        final String fullUrl = String.format("%s/philanthropists", this.url);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("id", philanthropist.getId());
        requestBody.put("communicationMethod", philanthropist.getCommunicationMethod().name());

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<PhilanthropistViewResponseDTO> responseEntity = restTemplate.postForEntity(fullUrl, requestBody, PhilanthropistViewResponseDTO.class);

        return responseEntity.getBody();
    }
}
