package com.xventure.petproject.petprojectbackend.controller;

import com.xventure.petproject.petprojectbackend.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService)
    {
        this.userService=userService;
    }

}
