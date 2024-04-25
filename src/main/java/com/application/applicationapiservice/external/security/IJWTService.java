package com.application.applicationapiservice.external.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJWTService {
    String extractUsername(final String token);

    String generateToken(final UserDetails userDetails);

    boolean isTokenValid(final String token, final UserDetails userDetails);
}
