package com.application.applicationapiservice.common.value.report;

public class Report {
    private String id;
    private String patientId;
    private String doctorName;
    private String disease;

    public Report(String id, String patientId, String doctorName, String disease) {
        this.id = id;
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.disease = disease;
    }

    public String getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDisease() {
        return disease;
    }
}
