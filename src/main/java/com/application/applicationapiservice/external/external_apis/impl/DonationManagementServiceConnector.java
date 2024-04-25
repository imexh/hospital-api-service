package com.application.applicationapiservice.external.external_apis.impl;

import com.application.applicationapiservice.common.value.donation.Donation;
import com.application.applicationapiservice.controllers.dto.DonationViewResponseDTO;
import com.application.applicationapiservice.external.external_apis.IDonationManagementServiceConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DonationManagementServiceConnector implements IDonationManagementServiceConnector {
    @Value("${donation-management-service.url}")
    private String url;

    @Override
    public DonationViewResponseDTO getDonationById(final String donationId) {
        final String fullUrl = String.format("%s/donations/%s", this.url, donationId);

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<DonationViewResponseDTO> responseEntity = restTemplate.getForEntity(fullUrl, DonationViewResponseDTO.class);

        return responseEntity.getBody();
    }

    @Override
    public List<DonationViewResponseDTO> getAllDonations() {
        final String fullUrl = String.format("%s/donations", this.url);

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<DonationViewResponseDTO[]> responseEntity = restTemplate.getForEntity(fullUrl, DonationViewResponseDTO[].class);

        List<DonationViewResponseDTO> donations = new ArrayList<>();

        if (responseEntity.getBody() != null) {
            donations = new ArrayList<>(List.of(responseEntity.getBody()));
        }

        return donations;
    }

    @Override
    public DonationViewResponseDTO createDonation(final Donation donation) {
        final String fullUrl = String.format("%s/donations", this.url);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("philanthropistId", donation.getPhilanthropistId());
        requestBody.put("patientId", donation.getPatientId());
        requestBody.put("donationDate", donation.getDonationDate());
        requestBody.put("donationAmount", donation.getDonationAmount().toString());

        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<DonationViewResponseDTO> responseEntity = restTemplate.postForEntity(fullUrl, requestBody, DonationViewResponseDTO.class);

        return responseEntity.getBody();
    }
}
