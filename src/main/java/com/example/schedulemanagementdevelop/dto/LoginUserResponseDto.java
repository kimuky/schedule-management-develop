package com.example.schedulemanagementdevelop.dto;

import com.example.schedulemanagementdevelop.entity.User;
import lombok.Getter;

@Getter
public class LoginUserResponseDto {

    private final Long id;

    private final String username;

    private final String email;

    private final String password;

    public LoginUserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
