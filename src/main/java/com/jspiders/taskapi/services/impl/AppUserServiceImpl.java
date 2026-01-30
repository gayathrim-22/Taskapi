package com.jspiders.taskapi.services.impl;

import com.jspiders.taskapi.data.users.*;
import com.jspiders.taskapi.errors.InvalidNameException;
import com.jspiders.taskapi.services.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.*;

//@Component
@Service
@Slf4j
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService
{
    private static Map<Long,AppUser> userDb= new HashMap<>();
    private final ObjectMapper mapper;

    private final AppUserRepository appUserRepository;


    @Override
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest)
    {
        log.info("inside createUser() {}",createUserRequest);
        //execute business logics

        //save data to database
//        Long userId = saveUser(createUserRequest);
        AppUser appUser  =  mapper.convertValue(createUserRequest,AppUser.class);
        appUser.setActive(true);

        AppUser appUser1 = appUserRepository.save(appUser);
        Long userId = appUser1.getUserId();

        //build response object
        CreateUserResponse response = new CreateUserResponse();
        response.setMessage("User created");
        response.setUserId(userId);

        log.info("inside createUser() : User created");

        //return response
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<String> updateUser() {
        log.info("this is AppUserServiceImpl --> updateUser()");
        //logics
        return ResponseEntity.status(HttpStatus.OK).body("User updated");
    }

    @Override
    public ResponseEntity<String> deleteUser(String email, String mobile, String password)
    {
        log.info("this is AppUserServiceImpl --> deleteUser()");

        //logics
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }

    @Override
    public ResponseEntity<List<AppUserDTO>> getAllUsers(Long userId)
    {
        log.info("this is AppUserServiceImpl --> getAllUsers()");

        //database ops (GET ALL USERS FROM DB)
        Collection<AppUser> values = userDb.values();
        List<AppUser> appUserList = new ArrayList<>(values);
        List<AppUserDTO> appUserDTOList = new ArrayList<>();

        //business logics(REMOVE PASSWORD DATA FROM RESPONSE)
        //build the response
        for (AppUser appUser:appUserList) {
            AppUserDTO appUserDTO = mapper.convertValue(appUser, AppUserDTO.class);
            appUserDTOList.add(appUserDTO);
        }

        //return the response
        return ResponseEntity.status(HttpStatus.OK).body(appUserDTOList);
    }

    @Override
    public ResponseEntity<AppUserDTO> getUserById(Long userId)
    {
        log.info("getUserById()");

        //execute business logics

        //perform db operations(GET USER FROM DB)
        AppUser appUser = userDb.get(userId);

        //build response object
//        AppUserDTO response = new AppUserDTO();
//
//        response.setName(appUser.getName());
//        response.setEmail(appUser.getEmail());
//        response.setMobile(appUser.getMobile());
//        response.setUserId(appUser.getUserId());
//        response.setActive(appUser.isActive());
        AppUserDTO response = mapper.convertValue(appUser,AppUserDTO.class);

        //return response object
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<AppUserDTO> getUserByEmail(String email) {
        return null;
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateUserEmail(Long userId, UpdateUserEmailRequest updateUserEmailRequest) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateUserName(Long userId, UpdateUserNameReq updateUserNameReq) {
        return null;
    }

    private void validateName(CreateUserRequest createUserRequest)
    {
        //Validation
        if(createUserRequest.getName() != null && createUserRequest.getName().length()<3)
        {
//            IllegalArgumentException ex = new IllegalArgumentException();
//            throw ex;

            InvalidNameException ex = new InvalidNameException("Invalid Name");
            throw ex;
        }
    }

    private void validateEmail(CreateUserRequest createUserRequest)
    {
        //Validation
        if(createUserRequest.getEmail() != null && createUserRequest.getEmail().length()<8)
        {
//            IllegalArgumentException ex = new IllegalArgumentException();
//            throw ex;

            InvalidNameException ex = new InvalidNameException("Invalid Name");
            throw ex;
        }
    }

    private Long saveUser(CreateUserRequest createUserRequest)
    {
        AppUser appUser = new AppUser();//create a row/record in db

        appUser.setName(createUserRequest.getName());
        appUser.setEmail(createUserRequest.getEmail());
        appUser.setMobile(createUserRequest.getMobile());
        appUser.setPassword(createUserRequest.getPassword());

        Random random = new Random();
        Long userId = random.nextLong();

        appUser.setUserId(userId);
        appUser.setActive(true);

        userDb.put(userId,appUser);// save data to db

        return userId;
    }
}