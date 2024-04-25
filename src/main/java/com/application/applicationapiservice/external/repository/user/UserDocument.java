package com.application.applicationapiservice.external.repository.user;

import com.application.applicationapiservice.common.enums.UserType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import jakarta.validation.constraints.NotNull;

@Document(collection = "users")
public class UserDocument {
    @NotNull
    @Id
    String id;

    @NotNull
    @Field(name = "username")
    String username;

    @NotNull
    @Field(name = "name")
    String name;

    @NotNull
    @Field(name = "email")
    String email;

    @NotNull
    @Field(name = "role")
    UserType role;

    @NotNull
    @Field(name = "password")
    String password;
}