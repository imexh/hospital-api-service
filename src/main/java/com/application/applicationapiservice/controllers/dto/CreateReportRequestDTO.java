package com.application.applicationapiservice.controllers.dto;

import com.application.applicationapiservice.common.value.report.Report;

public record CreateReportRequestDTO(String patientId, String doctorName, String disease) {
    public Report toReport() {
        return new Report(null, patientId(), doctorName(), disease());
    }
}
