package com.jspiders.taskapi.services;

import com.jspiders.taskapi.data.users.AppUser;
import com.jspiders.taskapi.data.users.AppUserDTO;
import com.jspiders.taskapi.data.users.CreateUserRequest;
import com.jspiders.taskapi.data.users.CreateUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AppUserService {
    ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest);
    ResponseEntity<String> updateUser();
    ResponseEntity<String> deleteUser(String email,String mobile,String password);
    ResponseEntity<List<AppUserDTO>> getAllUsers();
    ResponseEntity<AppUserDTO> getUserById(Long userId);
    ResponseEntity<AppUserDTO> getUserByEmail(String email);
}