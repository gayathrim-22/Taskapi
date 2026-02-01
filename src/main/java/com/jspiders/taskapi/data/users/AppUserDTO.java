package com.jspiders.taskapi.data.users;

import com.jspiders.taskapi.data.comments.Comment;
import lombok.Data;
import org.springframework.scheduling.config.Task;

import java.util.List;

@Data
public class AppUserDTO {
    private Long userId;
    private String name;
    private String email;
    private String mobile;
    private boolean isActive;
    private List<Task> taskList;
    private List<Comment> commentList;
}