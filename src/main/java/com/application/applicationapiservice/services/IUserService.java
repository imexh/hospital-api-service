package com.application.applicationapiservice.services;

import com.application.applicationapiservice.common.value.user.User;
import com.application.applicationapiservice.controllers.dto.CreateUserResponseDTO;

public interface IUserService {
    CreateUserResponseDTO createUser(final User user);
}
