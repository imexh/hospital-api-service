package com.application.applicationapiservice.external.external_apis;

import com.application.applicationapiservice.common.value.philanthropist.Philanthropist;
import com.application.applicationapiservice.controllers.dto.PhilanthropistViewResponseDTO;

public interface IPhilanthropistServiceConnector {
    PhilanthropistViewResponseDTO getPhilanthropistById(final String philanthropistId);

    PhilanthropistViewResponseDTO createPhilanthropist(final Philanthropist philanthropist);
}
