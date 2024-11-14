package com.example.schedulemanagementdevelop.dto;

import com.example.schedulemanagementdevelop.entity.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserAllResponseDto {

    private final Long id;

    private final String username;

    private final String contents;

    private final LocalDate createAt;

    private final LocalDate updateAt;

    public UserAllResponseDto(Long id, String username, String contents, LocalDate createAt, LocalDate updateAt) {
        this.id = id;
        this.username = username;
        this.contents = contents;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public static UserAllResponseDto toDto(User user) {
        return new UserAllResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreateAt().toLocalDate(), user.getUpdateAt().toLocalDate());
    }
}