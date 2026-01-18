package com.kxprem.RESTAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddStudentRequestDTO {
    @NotBlank(message = "name required")
    @Size(min = 3, max = 50)
    private String name;

    @Email
    @NotBlank(message = "email required")
    private String email;
}
