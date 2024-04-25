package com.application.applicationapiservice.external.external_apis;

import com.application.applicationapiservice.common.value.report.Report;
import com.application.applicationapiservice.controllers.dto.ReportViewResponseDTO;

import java.util.List;

public interface IReportingServiceConnector {
    ReportViewResponseDTO getReportById(final String reportId);

    List<ReportViewResponseDTO> getReportByPatientId(final String patientId);

    List<ReportViewResponseDTO> getAllReports();

    ReportViewResponseDTO createReport(final Report report);
}
