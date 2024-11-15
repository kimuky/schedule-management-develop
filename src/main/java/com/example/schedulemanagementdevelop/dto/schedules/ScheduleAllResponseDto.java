package com.example.schedulemanagementdevelop.dto.schedules;

import com.example.schedulemanagementdevelop.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleAllResponseDto {
    private final Long id;

    private final String title;

    private final String contents;

    private final String username;

    private final LocalDate createAt;

    private final LocalDate updateAt;

    public ScheduleAllResponseDto(Long id, String title, String contents, String username, LocalDate createAt, LocalDate updateAt) {

        this.id = id;
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }


    public static ScheduleAllResponseDto toDto(Schedule schedule) {
        return new ScheduleAllResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getUser().getUsername(), schedule.getCreateAt().toLocalDate(), schedule.getUpdateAt().toLocalDate());
    }
}
