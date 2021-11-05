package com.xventure.petproject.petprojectbackend.service;

import com.xventure.petproject.petprojectbackend.entity.User;
import com.xventure.petproject.petprojectbackend.repositry.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {


    private UserRepository userRepository;


    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    //user register
    public ResponseEntity<String> addUser(User user) {
        log.info("Inside addUser method in UserService");
        userRepository.save(user);

        return ResponseEntity.ok("User added Succssfully");
    }

    //check the email does exist
    public ResponseEntity<Boolean> emailIsExists(User user) {
        log.info("Inside emailIsExists method in UserService");
      User isExists=userRepository.findByEmailId(user.getEmailId());
      if (isExists==null)
      {
          return new ResponseEntity(false,HttpStatus.NOT_FOUND);
      }
      return ResponseEntity.ok(true);


    }
}
