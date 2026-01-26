package com.jspiders.taskapi.data.users;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "appusers")
public class AppUser {
    @Id
    @Column(name="userid",nullable=false,unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mysql will create and manage id
    private Long userId;
    @Column(name = "name",nullable = false,length = 45)
    private String name;
    @Column(name = "email",nullable = false,unique = true, length = 50)
    private String email;
    @Column(name = "mobile",nullable = false,unique = true,length = 10)
    private String mobile;
    @Column(name = "password",nullable = false,length = 10)
    private String password;
    @Column(name = "isActive",nullable = false)
    private boolean isActive;
}
