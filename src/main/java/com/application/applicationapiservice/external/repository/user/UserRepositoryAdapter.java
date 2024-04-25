package com.application.applicationapiservice.external.repository.user;

import com.application.applicationapiservice.common.value.user.User;
import com.application.applicationapiservice.external.repository.IUserRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class UserRepositoryAdapter implements IUserRepository {
    private final MongoTemplate mongoTemplate;

    public UserRepositoryAdapter(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void persistUser(final User user) {
        Objects.requireNonNull(user, "User repository required non null custom email, got null.");

        UserDocument userDocument = UserModelMapper.toUserDocument(user);

        mongoTemplate.save(userDocument);
    }

    @Override
    public User getUserByUsername(final String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));

        final UserDocument userDocument = mongoTemplate.findOne(query, UserDocument.class);

        return userDocument != null ? UserModelMapper.toUser(userDocument) : null;
    }
}
