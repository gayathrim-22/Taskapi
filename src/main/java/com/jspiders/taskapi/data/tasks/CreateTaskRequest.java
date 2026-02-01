package com.jspiders.taskapi.data.tasks;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateTaskRequest {

    @Length(min = 3,max = 45,message = "title of the task should be min-3 char and max-45 char only")
    @NotBlank(message = "Task title cannot be empty")
    private String title;

    @Length(min = 5,max = 500,message = "Add description")
    @NotBlank(message = "desc cannot be empty")
    private String description;

    @Length(max = 10)
    private String status;

    @NotBlank(message = "user Id is required")
    private Long   userId;
}