package com.application.applicationapiservice.services;

import com.application.applicationapiservice.common.value.user.User;
import com.application.applicationapiservice.controllers.dto.CreateUserResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService {
    CreateUserResponseDTO createUser(final User user);

    User getUserByUsername(final String username);

    UserDetailsService userDetailsService();
}
