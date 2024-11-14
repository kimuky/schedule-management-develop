package com.example.schedulemanagementdevelop.dto;

import com.example.schedulemanagementdevelop.entity.Schedule;
import lombok.Getter;

@Getter
public class CreateScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    public CreateScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
    }
}
