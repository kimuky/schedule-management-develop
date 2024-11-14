package com.example.schedulemanagementdevelop.entity;

import com.example.schedulemanagementdevelop.dto.ScheduleRequestDto;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule() {
    }

    public void setUser (User user ) {
        this.user = user;
    }

    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void updateTitle (String title) {
        this.title = title;
    }

    public void updateContents (String contents) {
        this.contents = contents;
    }

}
