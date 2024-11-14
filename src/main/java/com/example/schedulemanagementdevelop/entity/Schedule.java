package com.example.schedulemanagementdevelop.entity;

import com.example.schedulemanagementdevelop.dto.CreateScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "Text")
    private String contents;

    public Schedule() {
    }

    public Schedule(CreateScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

}
