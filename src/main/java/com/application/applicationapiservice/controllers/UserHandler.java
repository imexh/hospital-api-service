package com.application.applicationapiservice.controllers;

import com.application.applicationapiservice.controllers.dto.CreateUserRequestDTO;
import com.application.applicationapiservice.controllers.dto.CreateUserResponseDTO;
import com.application.applicationapiservice.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class UserHandler {
    private final IUserService userService;

    public UserHandler(final IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create/user")
    public ResponseEntity<CreateUserResponseDTO> createUser(
            @RequestBody final CreateUserRequestDTO requestDTO) {

        return new ResponseEntity<>(this.userService.createUser(requestDTO.toUser()), HttpStatus.OK);
    }
}
