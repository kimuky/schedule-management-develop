package com.example.schedulemanagementdevelop.dto;

import lombok.Getter;

@Getter
public class CreateScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    private final String username;

    public CreateScheduleResponseDto(Long id, String title, String content, String username) {
        this.id = id;
        this.title = title;
        this.contents = content;
        this.username = username;
    }
}
