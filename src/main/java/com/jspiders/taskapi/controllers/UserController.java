package com.jspiders.taskapi.controllers;

import com.jspiders.taskapi.data.users.AppUserDTO;
import com.jspiders.taskapi.data.users.CreateUserRequest;
import com.jspiders.taskapi.data.users.CreateUserResponse;
import com.jspiders.taskapi.services.impl.AppUserServiceImpl2;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {
    //immutable
    private final AppUserServiceImpl2 appUserService;
    @Autowired
    public UserController(AppUserServiceImpl2 appUserService)
    {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> addUser(@RequestBody @Valid CreateUserRequest createUserRequest)
    {
        log.info("inside addUser() createUserRequest : {}",createUserRequest);
        ResponseEntity<CreateUserResponse> response = appUserService.createUser(createUserRequest);
        log.info("inside addUser() : User created");
        return response;
    }

    @PutMapping
    public ResponseEntity<String> updateUser(){
        log.info("updateUser()");
        System.out.println("this is UserController --> updateUser()");
        ResponseEntity<String> response = appUserService.updateUser();
        return response;
    }

    @DeleteMapping
    ResponseEntity<String> deleteUser(String email,String mobile,String password){
        System.out.println("this is UserController --> deleteUser()");
        ResponseEntity<String> response = appUserService.deleteUser(email,mobile,password);
        return response;
    }

    @GetMapping
    ResponseEntity<List<AppUserDTO>> getAllUsers(){
        System.out.println("this is UserController --> getAllUsers()");
        ResponseEntity<List<AppUserDTO>> response = appUserService.getAllUsers();
        return response;
    }
    @GetMapping("/{userId}")
    ResponseEntity<AppUserDTO> getUserById(@PathVariable Long userId){
        log.info("getUserById()");
        ResponseEntity<AppUserDTO> response = appUserService.getUserById(userId);
        return response;
    }

    @GetMapping("/email/{email}")
    ResponseEntity<AppUserDTO> getUserByEmail(@PathVariable String email){
        log.info("getUserByEmail()");
        ResponseEntity<AppUserDTO> response = appUserService.getUserByEmail(email);
        return response;
    }
}