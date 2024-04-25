package com.application.applicationapiservice.controllers;

import com.application.applicationapiservice.controllers.dto.DonationViewResponseDTO;
import com.application.applicationapiservice.controllers.dto.MakeDonationRequestDTO;
import com.application.applicationapiservice.external.external_apis.IDonationManagementServiceConnector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class DonationHandler {
    private final IDonationManagementServiceConnector donationManagementServiceConnector;

    public DonationHandler(final IDonationManagementServiceConnector donationManagementServiceConnector) {
        this.donationManagementServiceConnector = donationManagementServiceConnector;
    }

    @GetMapping("/donations")
    public ResponseEntity<List<DonationViewResponseDTO>> getAllDonations() {
        return new ResponseEntity<>(this.donationManagementServiceConnector.getAllDonations(), HttpStatus.OK);
    }

    @GetMapping("/donations/{donationId}")
    public ResponseEntity<DonationViewResponseDTO> getDonationById(
            @PathVariable String donationId) {
        return new ResponseEntity<>(this.donationManagementServiceConnector.getDonationById(donationId), HttpStatus.OK);
    }

    @PostMapping("/donations")
    public ResponseEntity<DonationViewResponseDTO> donate(
            @RequestBody MakeDonationRequestDTO requestDTO) {
        return new ResponseEntity<>(this.donationManagementServiceConnector.createDonation(requestDTO.toDonation()), HttpStatus.CREATED);
    }
}
