package com.xventure.petproject.petprojectbackend.service;

import com.xventure.petproject.petprojectbackend.repositry.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
}
