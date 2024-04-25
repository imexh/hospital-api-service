package com.application.applicationapiservice.controllers;

import com.application.applicationapiservice.controllers.dto.CreatePhilanthropistRequestDTO;
import com.application.applicationapiservice.controllers.dto.PhilanthropistViewResponseDTO;
import com.application.applicationapiservice.external.external_apis.impl.PhilanthropistServiceConnector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class PhilanthropistHandler {
    private final PhilanthropistServiceConnector philanthropistServiceConnector;

    public PhilanthropistHandler(final PhilanthropistServiceConnector philanthropistServiceConnector) {
        this.philanthropistServiceConnector = philanthropistServiceConnector;
    }

    @GetMapping("/philanthropists/{philanthropistId}")
    public ResponseEntity<PhilanthropistViewResponseDTO> getPhilanthropistById(
            @PathVariable String philanthropistId) {
        return new ResponseEntity<>(this.philanthropistServiceConnector.getPhilanthropistById(philanthropistId), HttpStatus.OK);
    }

    @PostMapping("/philanthropists")
    public ResponseEntity<PhilanthropistViewResponseDTO> createReport(
            @RequestBody CreatePhilanthropistRequestDTO requestDTO) {
        return new ResponseEntity<>(this.philanthropistServiceConnector.createPhilanthropist(requestDTO.toPhilanthropist()), HttpStatus.CREATED);
    }
}
