package com.application.applicationapiservice.controllers;

import com.application.applicationapiservice.controllers.dto.CreatePatientRequestDTO;
import com.application.applicationapiservice.controllers.dto.PatientViewResponseDTO;
import com.application.applicationapiservice.external.external_apis.IPatientQualificationServiceConnector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PatientHandler {
    private final IPatientQualificationServiceConnector patientQualificationServiceConnector;

    public PatientHandler(final IPatientQualificationServiceConnector patientQualificationServiceConnector) {
        this.patientQualificationServiceConnector = patientQualificationServiceConnector;
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientViewResponseDTO> createPatient(
            @RequestBody final CreatePatientRequestDTO requestDTO) {

        return new ResponseEntity<>(this.patientQualificationServiceConnector.createPatient(requestDTO.toPatient()), HttpStatus.OK);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientViewResponseDTO> getPatientById(
            @PathVariable final String id) {
        return new ResponseEntity<>(this.patientQualificationServiceConnector.getPatientById(id), HttpStatus.OK);
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientViewResponseDTO>> getAllPatients() {
        return new ResponseEntity<>(this.patientQualificationServiceConnector.getAllPatients(), HttpStatus.OK);
    }

    @GetMapping("/patients/{id}/qualified")
    public ResponseEntity<Boolean> isPatientQualified(
            @PathVariable final String id) {
        return new ResponseEntity<>(this.patientQualificationServiceConnector.isPatientQualified(id), HttpStatus.OK);
    }

    @GetMapping("/qualified/patients")
    public ResponseEntity<List<PatientViewResponseDTO>> getAllQualifiedPatients() {
        return new ResponseEntity<>(this.patientQualificationServiceConnector.getAllQualifiedPatients(), HttpStatus.OK);
    }
}
