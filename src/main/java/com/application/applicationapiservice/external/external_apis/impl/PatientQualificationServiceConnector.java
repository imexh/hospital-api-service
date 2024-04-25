package com.application.applicationapiservice.external.external_apis.impl;

import com.application.applicationapiservice.common.value.patient.Patient;
import com.application.applicationapiservice.controllers.dto.PatientViewResponseDTO;
import com.application.applicationapiservice.external.external_apis.IPatientQualificationServiceConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PatientQualificationServiceConnector implements IPatientQualificationServiceConnector {
    @Value("${patient-qualification-service.url}")
    private String url;

    @Override
    public PatientViewResponseDTO getPatientById(final String patientId) {
        final String fullUrl = String.format("%s/patients/%s", this.url, patientId);

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<PatientViewResponseDTO> responseEntity = restTemplate.getForEntity(fullUrl, PatientViewResponseDTO.class);

        return responseEntity.getBody();
    }

    @Override
    public List<PatientViewResponseDTO> getAllPatients() {
        final String fullUrl = String.format("%s/patients", this.url);

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<PatientViewResponseDTO[]> responseEntity = restTemplate.getForEntity(fullUrl, PatientViewResponseDTO[].class);

        List<PatientViewResponseDTO> patients = new ArrayList<>();

        if (responseEntity.getBody() != null) {
            patients = new ArrayList<>(List.of(responseEntity.getBody()));
        }

        return patients;
    }

    @Override
    public List<PatientViewResponseDTO> getAllQualifiedPatients() {
        final String fullUrl = String.format("%s/qualified/patients", this.url);

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<PatientViewResponseDTO[]> responseEntity = restTemplate.getForEntity(fullUrl, PatientViewResponseDTO[].class);

        List<PatientViewResponseDTO> patients = new ArrayList<>();

        if (responseEntity.getBody() != null) {
            patients = new ArrayList<>(List.of(responseEntity.getBody()));
        }

        return patients;
    }

    @Override
    public boolean isPatientQualified(final String patientId) {
        final String fullUrl = String.format("%s/patients/%s/qualified", this.url, patientId);

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(fullUrl, Boolean.class);

        return responseEntity.getBody() != null ? responseEntity.getBody() : false;
    }

    @Override
    public PatientViewResponseDTO createPatient(final Patient patient) {
        final String fullUrl = String.format("%s/patients", this.url);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", patient.getName());
        requestBody.put("email", patient.getEmail());
        requestBody.put("birthDate", patient.getBirthDate());
        requestBody.put("income", patient.getIncome());
        requestBody.put("contact", patient.getContact());

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<PatientViewResponseDTO> responseEntity = restTemplate.postForEntity(fullUrl, requestBody, PatientViewResponseDTO.class);

        return responseEntity.getBody();
    }
}
