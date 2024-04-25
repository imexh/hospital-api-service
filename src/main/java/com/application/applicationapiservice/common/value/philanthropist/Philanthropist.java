package com.application.applicationapiservice.common.value.philanthropist;

import com.application.applicationapiservice.common.enums.CommunicationMethod;

public class Philanthropist {
    private String id;
    private CommunicationMethod communicationMethod;

    public Philanthropist(String id, CommunicationMethod communicationMethod) {
        this.id = id;
        this.communicationMethod = communicationMethod;
    }

    public String getId() {
        return id;
    }

    public CommunicationMethod getCommunicationMethod() {
        return communicationMethod;
    }
}
