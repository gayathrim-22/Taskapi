package com.jspiders.taskapi.data.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateUserRequest {

    @Length(min = 3,max = 45,message = "Name should be min-3 char and max-45 char only")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Invalid email id format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Length(min = 10,max = 10,message = "Mobile no should be 10-digits only")
    @NotBlank(message = "Mobile cannot be empty")
    private String mobile;

    @Length(min = 6,max = 15,message = "Password should be min-6 char and max-15 char only")
    @NotBlank(message = "Password cannot be empty")
    private String password;
}