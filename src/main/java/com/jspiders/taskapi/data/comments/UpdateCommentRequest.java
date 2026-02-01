package com.jspiders.taskapi.data.comments;

import lombok.Data;

@Data
public class UpdateCommentRequest {
    private Long commentId;
    private String text;
    private String status;
}
