package com.jspiders.taskapi.data.users;

import com.jspiders.taskapi.data.tasks.Task;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "appusers")
public class AppUser {
    @Id
    @Column(name = "userid",nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Mysql will create and manage ID
    private Long userId;

    @Column(name = "name",nullable = false,length = 45)
    private String name;

    @Column(name = "email",nullable = false,unique = true,length = 50)
    private String email;

    @Column(name = "mobile",nullable = false,unique = true,length = 10)
    private String mobile;

    @Column(name = "password",nullable = false,length = 20)
    private String password;

    @Column(name = "isActive",nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "appUser")
    private List<Task> taskList;
}
