package com.xventure.petproject.petprojectbackend.controller;

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


@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {

    private UserService userService;

    public UserController(UserService userService)
    {
        this.userService=userService;
    }


    //user register
    @PostMapping("/register")
    @Operation(summary = "This is User Registration")
    @ApiResponse(responseCode = "200",
            description = "User Data added successfully",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<String> addUser(@Valid  @RequestBody User user)
    {
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
    public ResponseEntity<Boolean> emailIsExists(@RequestBody User user)
    {

        log.info("Inside emailIsExists method in UserController");
        return userService.emailIsExists(user);
    }






}
