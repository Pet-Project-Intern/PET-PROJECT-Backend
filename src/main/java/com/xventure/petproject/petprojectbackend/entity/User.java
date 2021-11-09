package com.xventure.petproject.petprojectbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;



    @NotEmpty(message = "EmailId Field is Required")
    @Email(message = "EmailId format incorrect")
    private String emailId;

    @NotEmpty(message = "Password Field is Required")
    @Size(min = 8,message = "Password field should be minimum 8 characters")
    private String password;

    private String userCategory;
}
