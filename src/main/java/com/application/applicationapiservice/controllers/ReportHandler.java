package com.application.applicationapiservice.controllers;

import com.application.applicationapiservice.controllers.dto.CreateReportRequestDTO;
import com.application.applicationapiservice.controllers.dto.ReportViewResponseDTO;
import com.application.applicationapiservice.external.external_apis.IReportingServiceConnector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ReportHandler {
    private final IReportingServiceConnector reportingServiceConnector;

    public ReportHandler(final IReportingServiceConnector reportingServiceConnector) {
        this.reportingServiceConnector = reportingServiceConnector;
    }

    @GetMapping("/reports")
    public ResponseEntity<List<ReportViewResponseDTO>> getAllReports() {
        return new ResponseEntity<>(this.reportingServiceConnector.getAllReports(), HttpStatus.OK);
    }

    @GetMapping("/reports/report-id/{reportId}")
    public ResponseEntity<ReportViewResponseDTO> getReportById(
            @PathVariable String reportId) {
        return new ResponseEntity<>(this.reportingServiceConnector.getReportById(reportId), HttpStatus.OK);
    }

    @GetMapping("/reports/patient-id/{patientId}")
    public ResponseEntity<List<ReportViewResponseDTO>> getReportsByPatientId(
            @PathVariable String patientId) {
        return new ResponseEntity<>(this.reportingServiceConnector.getReportByPatientId(patientId), HttpStatus.OK);
    }

    @PostMapping("/reports")
    public ResponseEntity<ReportViewResponseDTO> createReport(
            @RequestBody CreateReportRequestDTO requestDTO) {
        return new ResponseEntity<>(this.reportingServiceConnector.createReport(requestDTO.toReport()), HttpStatus.CREATED);
    }
}
