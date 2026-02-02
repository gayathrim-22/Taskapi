package com.jspiders.taskapi.data.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jspiders.taskapi.data.comments.Comment;
import com.jspiders.taskapi.data.users.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "task")
@ToString(exclude = {"appUser","commentList"})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "status",nullable = false)
    private String status;

    @Column(name = "createdAt",nullable = false)
    private String createdAt;

    @Column(name = "updatedAt",nullable = false)
    private String updatedAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUser appUser;

    @OneToMany
    @JsonIgnore
    private List<Comment> commentList;
}