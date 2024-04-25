package com.application.applicationapiservice.external.external_apis;

import com.application.applicationapiservice.common.value.patient.Patient;
import com.application.applicationapiservice.controllers.dto.PatientViewResponseDTO;

import java.util.List;

public interface IPatientQualificationServiceConnector {

    PatientViewResponseDTO getPatientById(final String patientId);

    List<PatientViewResponseDTO> getAllPatients();

    List<PatientViewResponseDTO> getAllQualifiedPatients();

    boolean isPatientQualified(final String patientId);

    PatientViewResponseDTO createPatient(final Patient patient);
}
