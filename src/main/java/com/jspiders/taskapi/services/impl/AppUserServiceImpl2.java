package com.jspiders.taskapi.services.impl;

import com.jspiders.taskapi.data.users.*;
import com.jspiders.taskapi.errors.DuplicateUserException;
import com.jspiders.taskapi.services.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
@Slf4j
public class AppUserServiceImpl2 implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest) {

        //Data verification
        boolean exists = appUserRepository.existsByEmailOrMobile(createUserRequest.getEmail(),createUserRequest.getMobile());

        if(exists == true){
            throw new DuplicateUserException("User with given email/mobile already exists");
        }

        //Convert request to ENTITY
        AppUser appUser = mapper.convertValue(createUserRequest, AppUser.class);

        //Set the required values depeneding on the requirement
        appUser.setActive(true);

        //Save the entity to the Database and get the stored data
        AppUser appUserInDb = appUserRepository.save(appUser);

        //Get required values for the table
        long userId = appUserInDb.getUserId();

        //build the response object
        CreateUserResponse response = new CreateUserResponse();
        response.setUserId(userId);
        response.setMessage("User Created");

        //return the response object with ResponseEntity
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<String> updateUser() {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteUser(String email, String mobile, String password) {
        return null;
    }

    @Override
    public ResponseEntity<List<AppUserDTO>> getAllUsers(Long userId) {
        //verify the user
        boolean isPresent = appUserRepository.existsById(userId);
        if(isPresent==false)
        {
            throw new IllegalArgumentException("Security ERROR : USERID is not VALID");
        }

        //BL --- database operations(GET ALL USERS FROM DB)
        List<AppUser> appUserList=appUserRepository.findAll();
        List<AppUserDTO> appUserDTOList=new ArrayList<>();

        //business logics(Remove password data from response)
        //build the response
        for (AppUser appUser:appUserList){
            AppUserDTO appUserDTO=mapper.convertValue(appUser,AppUserDTO.class);
            appUserDTOList.add(appUserDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(appUserDTOList);
    }

    @Override
    public ResponseEntity<AppUserDTO> getUserById(Long userId) {
        log.info("getUserById()");
        //perform db operations(GET USER FROM DB)
        //AppUser appUser = userDb.get(userId);

        Optional<AppUser> optional = appUserRepository.findById(userId);
        AppUser appUser = optional.get();
        AppUserDTO response = mapper.convertValue(appUser,AppUserDTO.class);
        //return response object
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<AppUserDTO> getUserByEmail(String email) {
        Optional<AppUser> optional = appUserRepository.findByEmail(email);
        AppUser appUser = optional.get();
        AppUserDTO appUserDTO = mapper.convertValue(appUser, AppUserDTO.class);
        log.info("appUserDTO {}",appUserDTO);
        return ResponseEntity.ok().body(appUserDTO);
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        log.info("inside login()");
        String userId;
        LoginResponse loginResponse;
        //check if user with email and password exists in DB
        boolean isPresent =  appUserRepository.existsByEmailAndPassword
                (loginRequest.getEmail(), loginRequest.getPassword());

        if(isPresent==true)
        {
            //if user with email and password exists get userid
            Optional<AppUser> userOptional = appUserRepository.findByEmail(loginRequest.getEmail());
            AppUser appUser = userOptional.get();
            loginResponse = mapper.convertValue(appUser, LoginResponse.class);
            loginResponse.setMessage("Login success");
            //userId = String.valueOf(appUser.getUserId());
        }
        else {
            //if user with email and password DO NOT exist throw exception
            throw new IllegalArgumentException("Invalid Username / Password");
        }
        //return userId of the given user
        return ResponseEntity.ok(loginResponse);
    }

    @Override
    public ResponseEntity<String> updateUserEmail(Long userId, UpdateUserEmailRequest updateUserEmailRequest) {
        log.info("this is updateUserEmail");
        //verify the user
        boolean isPresent=appUserRepository.existsById(userId);
        if (isPresent==false){
            throw new IllegalArgumentException("SECURITY ERROR : UserId is not valid");
        }
        Optional<AppUser> appUserOptional = appUserRepository.findByEmailAndUserId(updateUserEmailRequest.getOldEmail(), updateUserEmailRequest.getUserId());
        if(appUserOptional.isEmpty()){
            throw new IllegalArgumentException("user with email and userid is not found");
        }
        else {
            AppUser appUser=appUserOptional.get();
            appUser.setEmail(updateUserEmailRequest.getNewEmail());
            appUserRepository.save(appUser);
        }
        return ResponseEntity.ok("User Email updated");
    }

    @Override
    public ResponseEntity<String> updateUserName(Long userId, UpdateUserNameReq updateUserNameReq) {
        log.info("This is updateUserEmail");
        //verify the user
        boolean isPresent=appUserRepository.existsById(userId);
        if (isPresent==false){
            throw new IllegalArgumentException("SECURITY ERROR : UserId is not valid");
        }
        Optional<AppUser> appUserOptional = appUserRepository.findByNameAndUserId(updateUserNameReq.getOldUname(), updateUserNameReq.getUserId());
        if(appUserOptional.isEmpty()){
            throw new IllegalArgumentException("user with username and userid is not found");
        }
        else {
            AppUser appUser=appUserOptional.get();
            appUser.setName(updateUserNameReq.getNewUname());
            appUserRepository.save(appUser);
        }
        return ResponseEntity.ok("Username updated");
    }

    @Override
    public ResponseEntity<String> updateUserMobile(Long userId, UpdateUserMobileReq updateUserMobileReq) {
        log.info("This is  updateUserMobile() ");
        boolean isExist=appUserRepository.existsById(userId);
        if (isExist==false){
            throw new IllegalArgumentException(("SECURITY ERROR : User Id is not valid"));
        }
        Optional<AppUser> appUserOptional=appUserRepository.findByMobileAndUserId(updateUserMobileReq.getOldMobileNum(),updateUserMobileReq.getUserId());
        if (appUserOptional.isEmpty()){
            throw new IllegalArgumentException("user with mobile  no and userId is not found");
        }
        else {
            AppUser appUser=appUserOptional.get();
            appUser.setMobile(updateUserMobileReq.getNewMobileNum());
            appUserRepository.save(appUser);
        }
        return ResponseEntity.ok("Mobile number updated successfully");
    }

}