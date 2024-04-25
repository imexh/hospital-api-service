package com.application.applicationapiservice.controllers.dto;

import com.application.applicationapiservice.common.enums.CommunicationMethod;
import com.application.applicationapiservice.common.value.philanthropist.Philanthropist;

public record CreatePhilanthropistRequestDTO(String id, CommunicationMethod communicationMethod) {
    public Philanthropist toPhilanthropist() {
        return new Philanthropist(id, communicationMethod);
    }
}
