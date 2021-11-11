package com.xventure.petproject.petprojectbackend.service;

import com.xventure.petproject.petprojectbackend.dto.UserDTO;
import com.xventure.petproject.petprojectbackend.entity.User;
import com.xventure.petproject.petprojectbackend.exception.ErrorSelector;
import com.xventure.petproject.petprojectbackend.exception.GenaricException;
import com.xventure.petproject.petprojectbackend.exception.UserNotFoundException;
import com.xventure.petproject.petprojectbackend.repositry.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

    //get admin data
    public List<User> getAdmin(){return userRepository.findByUserCategory("admin");}

    //    hashing method
    private String hashingPass(String text) {
        MessageDigest digest = null;
        String s = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            s = digest.digest(text.getBytes(StandardCharsets.UTF_8)).toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return s;
    }

    private String removeWhiteSpaces(String s) {
        return s.replaceAll("\\s+", "");
    }


    private boolean isEmailSyntactical(String mail) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE
        );
        return VALID_EMAIL_ADDRESS_REGEX.matcher(mail).find();
    }

    public ResponseEntity<UserDTO> loginUser(String email, String password) {

//        removing white spaces
        String mail = removeWhiteSpaces(email);
        String pass = removeWhiteSpaces(password);
//        mail syntax checking
        if (!isEmailSyntactical(mail)) {
            throw new GenaricException(ErrorSelector.EMAIL_SYNTAX_ERROR);
        }
//        user loading
        User search = userRepository.findByEmailId(mail);
//        if there is no user
        if (search == null) {
            throw new GenaricException(ErrorSelector.EMAIL_NOT_FOUND);
        }
//        if password mismatching
        if (!search.getPassword().equals(pass)) {
            throw new GenaricException(ErrorSelector.PASSWORD_NOT_MATCH);
        }
//        if all is well
        return ResponseEntity.status(200).body(new UserDTO(
                search.getId(),
                search.getEmailId(),
                search.getPassword()
        ));
    }

    public Map<String, String> deleteUser(String id) {
        userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User doesn't exits in database")
        );
        log.info("User delete successfully UserId: " + id);
        userRepository.deleteById(id);
        Map<String, String> response = new HashMap<String, String>();
        response.put("msg", "user delete successfully");
        return response;
    }

    public Map<String, String> editEmployee(String id, User user) {
        userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User doesn't exits in database")
        );
        System.out.println(user);
        userRepository.save(user);
        log.info("User edit successfully UserId: " + id);
        Map<String, String> response = new HashMap<String, String>();
        response.put("msg", "user edit successfully");
        return response;
    }
}
