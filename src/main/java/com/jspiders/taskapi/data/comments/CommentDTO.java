package com.jspiders.taskapi.data.comments;

import com.jspiders.taskapi.data.users.AppUser;

import java.time.LocalDate;

public class CommentDTO {
    private Long commentId;
    private String text;
    private LocalDate createdAt;
    private String status;

}
