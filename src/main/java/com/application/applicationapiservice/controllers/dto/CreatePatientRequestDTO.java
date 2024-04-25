package com.application.applicationapiservice.controllers.dto;


import com.application.applicationapiservice.common.value.patient.Patient;

public record CreatePatientRequestDTO(String name, String email, String birthDate, String income, String contact, boolean isQualified) {
    public Patient toPatient() {
        return new Patient(null, name, email, birthDate, income, contact, isQualified);
    }
}
