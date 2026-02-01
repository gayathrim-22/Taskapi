package com.jspiders.taskapi.data.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jspiders.taskapi.data.comments.Comment;
import com.jspiders.taskapi.data.tasks.Task;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@ToString(exclude = "taskList")
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
    @JsonIgnore
    private List<Task> taskList;

    @OneToMany(mappedBy = "appUser")
    private List<Comment> commentList;


}
