package com.application.applicationapiservice.services.impl;

import com.application.applicationapiservice.common.value.user.User;
import com.application.applicationapiservice.controllers.dto.CreateUserResponseDTO;
import com.application.applicationapiservice.external.repository.IUserRepository;
import com.application.applicationapiservice.services.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CreateUserResponseDTO createUser(final User user) {
        if (userRepository.getUserByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("User with username already exists.");
        }

//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.persistUser(user);

        return new CreateUserResponseDTO(user);
    }
}
