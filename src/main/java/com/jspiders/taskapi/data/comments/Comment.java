package com.jspiders.taskapi.data.comments;

import com.jspiders.taskapi.data.users.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Entity
@ToString(exclude = "appUser")
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "text",nullable = false)
    private String text;

    @Column(name = "createdAt",nullable = false)
    private LocalDate createdAt;

    @Column(name = "status",nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUser appUser;

}
