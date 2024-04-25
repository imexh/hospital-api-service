package com.application.applicationapiservice.common.value.donation;

public class Donation {
    private String id;
    private String philanthropistId;
    private String patientId;
    private String donationDate;
    private Double donationAmount;

    public Donation(String id, String philanthropistId, String patientId, String donationDate, Double donationAmount) {
        this.id = id;
        this.philanthropistId = philanthropistId;
        this.patientId = patientId;
        this.donationDate = donationDate;
        this.donationAmount = donationAmount;
    }

    public String getId() {
        return id;
    }

    public String getPhilanthropistId() {
        return philanthropistId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public Double getDonationAmount() {
        return donationAmount;
    }
}
