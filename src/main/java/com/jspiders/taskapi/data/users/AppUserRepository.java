package com.jspiders.taskapi.data.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    boolean existsByEmailOrMobile(String email,String mobile);
    boolean existsByMobile(String mobile);
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByEmailAndUserId(String email,Long userId);
    Optional<AppUser> findByName(String name,Long userId);
    AppUser findByMobile(String mobile);
    AppUser findByName(String name);
    boolean existsByEmailAndPassword(String email,String password);
    boolean existsByMobileAndPassword(String mobile,String password);

}