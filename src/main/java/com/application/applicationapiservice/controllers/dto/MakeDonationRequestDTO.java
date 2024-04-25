package com.application.applicationapiservice.controllers.dto;


import com.application.applicationapiservice.common.value.donation.Donation;

public record MakeDonationRequestDTO(String philanthropistId, String patientId, String donationDate,
                                     Double donationAmount) {
    public Donation toDonation() {
        return new Donation(null, philanthropistId, patientId, donationDate, donationAmount);
    }
}
