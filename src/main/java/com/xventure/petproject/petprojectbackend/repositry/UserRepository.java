package com.xventure.petproject.petprojectbackend.repositry;

import com.xventure.petproject.petprojectbackend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmailId(String emailId);

    List<User> findByUserCategory(String userCategory);
}
