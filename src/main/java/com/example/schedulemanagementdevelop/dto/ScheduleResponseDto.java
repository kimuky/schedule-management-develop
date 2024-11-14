package com.example.schedulemanagementdevelop.dto;

import com.example.schedulemanagementdevelop.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
    }
}
