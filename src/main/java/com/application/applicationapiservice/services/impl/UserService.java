package com.application.applicationapiservice.services.impl;

import com.application.applicationapiservice.common.value.user.User;
import com.application.applicationapiservice.controllers.dto.CreateUserResponseDTO;
import com.application.applicationapiservice.external.repository.IUserRepository;
import com.application.applicationapiservice.services.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(final IUserRepository userRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CreateUserResponseDTO createUser(final User user) {
        if (userRepository.getUserByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("User with username already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.persistUser(user);

        return new CreateUserResponseDTO(user);
    }

    @Override
    public User getUserByUsername(final String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
                return userRepository.getUserByUsername(username);
            }
        };
    }
}
