package com.application.applicationapiservice.external.external_apis.impl;

import com.application.applicationapiservice.common.value.report.Report;
import com.application.applicationapiservice.controllers.dto.ReportViewResponseDTO;
import com.application.applicationapiservice.external.external_apis.IReportingServiceConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReportingServiceConnector implements IReportingServiceConnector {
    @Value("${reporting-service-service.url}")
    private String url;

    @Override
    public ReportViewResponseDTO getReportById(final String reportId) {
        final String fullUrl = String.format("%s/reports/report-id/%s", this.url, reportId);

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<ReportViewResponseDTO> responseEntity = restTemplate.getForEntity(fullUrl, ReportViewResponseDTO.class);

        return responseEntity.getBody();
    }

    @Override
    public List<ReportViewResponseDTO> getReportByPatientId(final String patientId) {
        final String fullUrl = String.format("%s/reports/patient-id/%s", this.url, patientId);

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<ReportViewResponseDTO[]> responseEntity = restTemplate.getForEntity(fullUrl, ReportViewResponseDTO[].class);

        List<ReportViewResponseDTO> reports = new ArrayList<>();

        if (responseEntity.getBody() != null) {
            reports = new ArrayList<>(List.of(responseEntity.getBody()));
        }

        return reports;
    }

    @Override
    public List<ReportViewResponseDTO> getAllReports() {
        final String fullUrl = String.format("%s/reports", this.url);

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<ReportViewResponseDTO[]> responseEntity = restTemplate.getForEntity(fullUrl, ReportViewResponseDTO[].class);

        List<ReportViewResponseDTO> reports = new ArrayList<>();

        if (responseEntity.getBody() != null) {
            reports = new ArrayList<>(List.of(responseEntity.getBody()));
        }

        return reports;
    }

    @Override
    public ReportViewResponseDTO createReport(final Report report) {
        final String fullUrl = String.format("%s/reports", this.url);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("patientId", report.getPatientId());
        requestBody.put("doctorName", report.getDoctorName());
        requestBody.put("disease", report.getDisease());

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<ReportViewResponseDTO> responseEntity = restTemplate.postForEntity(fullUrl, requestBody, ReportViewResponseDTO.class);

        return responseEntity.getBody();
    }
}
