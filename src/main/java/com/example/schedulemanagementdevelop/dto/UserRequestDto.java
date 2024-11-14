package com.example.schedulemanagementdevelop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotBlank
    private final String username;

    @NotBlank
    @Pattern(regexp = "^[\\\\w!#$%&'*+/=?`{|}~^.-]+@[\\\\w.-]+\\\\.[a-zA-Z]{2,6}$")
    private final String email;

    @NotBlank
    @Size(min = 6)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$")
    private final String password;

    public UserRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
