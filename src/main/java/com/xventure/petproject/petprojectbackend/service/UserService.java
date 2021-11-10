package com.xventure.petproject.petprojectbackend.service;

import com.xventure.petproject.petprojectbackend.entity.User;
import com.xventure.petproject.petprojectbackend.exception.UserNotFoundException;
import com.xventure.petproject.petprojectbackend.repositry.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //user register
    public ResponseEntity<String> addUser(User user) {
        log.info("Inside addUser method in UserService");
        userRepository.save(user);
        return ResponseEntity.ok("User added Successfully");
    }

    //check the email does exist
    public ResponseEntity<Boolean> emailIsExists(User user) {
        log.info("Inside emailIsExists method in UserService");
        User isExists = userRepository.findByEmailId(user.getEmailId());
        if (isExists == null) {
            return new ResponseEntity(false, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(true);
    }

    //get employee data particular organization
    public List<User> getEmployee() {
        return userRepository.findByUserCategory("employee");
    }

    public Map<String, String> deleteUser(String id) {
        userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User doesn't exits in database")
        );
        userRepository.deleteById(id);
        Map<String, String> response = new HashMap<String, String>();
        response.put("msg", "user delete successfully");
        return response;
    }

    public Map<String, String> editEmployee(String id, User user) {
        userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User doesn't exits in database")
        );
        userRepository.save(user);
        Map<String, String> response = new HashMap<String, String>();
        response.put("msg", "user edit successfully");
        return response;
    }
}
