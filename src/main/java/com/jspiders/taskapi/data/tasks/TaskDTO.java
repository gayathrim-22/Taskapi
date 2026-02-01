package com.jspiders.taskapi.data.tasks;

import com.jspiders.taskapi.data.users.AppUser;
import lombok.Data;

@Data
public class TaskDTO {

    private Long taskId;

    private String title;

    private String description;

    private String status;

    private String createdAt;

    private String updatedAt;

//    private AppUser appUser;
}
