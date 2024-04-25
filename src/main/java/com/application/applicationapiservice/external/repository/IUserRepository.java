package com.application.applicationapiservice.external.repository;

import com.application.applicationapiservice.common.value.user.User;

public interface IUserRepository {
    void persistUser(final User user);

    User getUserByUsername(final String username);
}
