package com.application.applicationapiservice.external.repository.user;

import com.application.applicationapiservice.common.value.user.User;

public class UserModelMapper {
    public UserModelMapper() {
    }

    static UserDocument toUserDocument(final User user) {
        UserDocument userDocument = new UserDocument();

        userDocument.id = user.getId();
        userDocument.username = user.getUsername();
        userDocument.name = user.getName();
        userDocument.email = user.getEmail();
        userDocument.role = user.getUserType();
        userDocument.password = user.getPassword();

        return userDocument;
    }

    static User toUser(final UserDocument userDocument) {
        return new User(userDocument.id, userDocument.username, userDocument.name, userDocument.email,
                userDocument.role, userDocument.password);
    }
}
