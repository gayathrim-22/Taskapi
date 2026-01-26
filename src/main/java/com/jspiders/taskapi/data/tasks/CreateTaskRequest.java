package com.jspiders.taskapi.data.tasks;

import lombok.Data;

@Data
public class CreateTaskRequest {
    private String title;
    private String description;
}
