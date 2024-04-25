package com.application.applicationapiservice.controllers;

import com.application.applicationapiservice.controllers.dto.CreateUserRequestDTO;
import com.application.applicationapiservice.controllers.dto.CreateUserResponseDTO;
import com.application.applicationapiservice.controllers.dto.LoginRequestDTO;
import com.application.applicationapiservice.controllers.dto.LoginResponseDTO;
import com.application.applicationapiservice.external.security.IJWTService;
import com.application.applicationapiservice.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class UserHandler {
    private final IUserService userService;
    private final IJWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserHandler(final IUserService userService, final IJWTService jwtService, final AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponseDTO> createUser(
            @RequestBody final CreateUserRequestDTO requestDTO) {

        return new ResponseEntity<>(this.userService.createUser(requestDTO.toUser()), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody final LoginRequestDTO requestDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    requestDTO.username(), requestDTO.password()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        var user = userService.getUserByUsername(requestDTO.username());
        var jwt = jwtService.generateToken(user);

        return new ResponseEntity<>(new LoginResponseDTO(jwt), HttpStatus.OK);
    }
}
