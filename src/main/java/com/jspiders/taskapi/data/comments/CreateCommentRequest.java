package com.jspiders.taskapi.data.comments;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateCommentRequest {

    @Length(min = 5,max = 500,message = "Add comments")
    @NotBlank(message = "comments cannot be empty")
    private String text;

    private String status;

    @NotBlank(message = "user Id is required")
    private Long   userId;
}
