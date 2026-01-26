package com.jspiders.taskapi.data.users;

import lombok.Data;

@Data
public class AppUserDTO {
    private Long userId;
    private String name;
    private String email;
    private String mobile;
    private boolean isActive;
}