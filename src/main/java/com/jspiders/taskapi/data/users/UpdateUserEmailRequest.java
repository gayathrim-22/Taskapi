package com.jspiders.taskapi.data.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserEmailRequest {

    @NotBlank(message = "new email cannot be empty")
    private String newEmail;

    @NotBlank(message = "old email cannot be empty")
    private String oldEmail;

    @NotNull(message = "Requires userId")
    private Long userId;
}
