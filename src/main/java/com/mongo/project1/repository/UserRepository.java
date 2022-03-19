package com.mongo.project1.repository;

import com.mongo.project1.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByName(String name);
    public long count();
}
