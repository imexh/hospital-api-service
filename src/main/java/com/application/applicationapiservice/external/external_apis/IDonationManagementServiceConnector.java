package com.application.applicationapiservice.external.external_apis;

import com.application.applicationapiservice.common.value.donation.Donation;
import com.application.applicationapiservice.controllers.dto.DonationViewResponseDTO;

import java.util.List;

public interface IDonationManagementServiceConnector {
    DonationViewResponseDTO getDonationById(final String donationId);

    List<DonationViewResponseDTO> getAllDonations();

    DonationViewResponseDTO createDonation(final Donation donation);
}
