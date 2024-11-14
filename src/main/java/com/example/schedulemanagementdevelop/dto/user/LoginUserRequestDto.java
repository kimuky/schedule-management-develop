package com.example.schedulemanagementdevelop.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginUserRequestDto {

    @NotBlank
    private final String email;

    @NotBlank
    private final String password;

    public LoginUserRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
