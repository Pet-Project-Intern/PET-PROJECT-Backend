package com.xventure.petproject.petprojectbackend.controller;

import com.xventure.petproject.petprojectbackend.dto.UserDTO;
import com.xventure.petproject.petprojectbackend.entity.User;
import com.xventure.petproject.petprojectbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //user register
    @PostMapping("/register")
    @Operation(summary = "This is User Registration")
    @ApiResponse(responseCode = "200",
            description = "User Data added successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<String> addUser(@Valid @RequestBody User user) {
        System.out.println(user);
        log.info("Inside addUser method in UserController");
        return userService.addUser(user);
    }

    //check the email does exist
    @GetMapping("/emailIsExists")
    @Operation(summary = "Check  EmailId Is Existing?")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Email does exists",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "404",
                    description = "Email doesn't Exists",
                    content = @Content(mediaType = "application/json")
            )

    })
    public ResponseEntity<Boolean> emailIsExists(@RequestBody User user) {
        log.info("Inside emailIsExists method in UserController");
        return userService.emailIsExists(user);
    }


    @GetMapping(value = "/loginUser")
    @Operation(summary = "login validation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User is Exist",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "400",
                    description = "Email address is incorrect",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "401",
                    description = "You are not registered in the system. Please register in the system",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "402",
                    description = "Password is incorrect. Please check and try again",
                    content = @Content(mediaType = "application/json")
            )
    })
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO dto) {
        return userService.loginUser(dto.getEmailId(), dto.getPassword());
    }

    @GetMapping(value = "/employee")
    public ResponseEntity<List<User>> getEmployee() {
        log.info("Get all employee");
        return ResponseEntity.ok(userService.getEmployee());

    }

    @GetMapping(value = "/admin")
    public ResponseEntity<List<User>> getAdmin(){
        log.info("get all admin users");
        return ResponseEntity.ok(userService.getAdmin());
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<Map<String, String>> editEmployee(@PathVariable String id, @RequestBody User user) {
        return ResponseEntity.ok(userService.editEmployee(id, user));
    }
}
