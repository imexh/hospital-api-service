package com.application.applicationapiservice.controllers.dto;

import com.application.applicationapiservice.common.enums.UserType;
import com.application.applicationapiservice.common.value.user.User;

public record CreateUserRequestDTO(String username, String name, String email, UserType userType,
                                   String password) {
    public User toUser() {
        return new User(null, username, name, email, userType, password);
    }
}
