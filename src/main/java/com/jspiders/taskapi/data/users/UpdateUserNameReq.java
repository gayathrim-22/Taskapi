package com.jspiders.taskapi.data.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserNameReq {
    @NotBlank(message = "new username cannot be empty")
    private String newUname;

    @NotBlank(message = "old username cannot be empty")
    private String oldUname;

    @NotNull(message = "Requires userId")
    private Long userId;
}
