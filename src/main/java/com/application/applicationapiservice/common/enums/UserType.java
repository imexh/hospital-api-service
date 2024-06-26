package com.application.applicationapiservice.common.enums;

public enum UserType {
    DONATION_CENTER("DONATION_CENTER"),
    PHILANTHROPIST("PHILANTHROPIST"),
    ADMIN("ADMIN");

    private String value;

    UserType(String type) {
        value = type;
    }

    public String getValue() {
        return value;
    }
}
