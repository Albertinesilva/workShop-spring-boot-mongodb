package com.swproject.workshopmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.swproject.workshopmongodb.domain.User;

@Repository
public interface UserRepositoty extends MongoRepository<User, String> {

}
