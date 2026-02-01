package com.jspiders.taskapi.data.comments;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CommentDTO {
    private Long commentId;
    private String text;
    private LocalDate createdAt;
    private String status;

}
