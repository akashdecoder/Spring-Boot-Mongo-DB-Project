package com.mongo.project1.repository;

import com.mongo.project1.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {

    User findUserByName(String name);

//    @Query(value = "{contact:'?0'}", fields = "")

    public long count();
}
