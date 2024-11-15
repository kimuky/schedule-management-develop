package com.example.schedulemanagementdevelop.dto.comment;

import com.example.schedulemanagementdevelop.entity.Comment;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentResponseDto {

    private final Long id;

    private final Long userId;

    private final Long scheduleId;

    private final String contents;

    private final LocalDate createAt;

    private final LocalDate updateAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUser().getId();
        this.scheduleId = comment.getSchedule().getId();
        this.contents = comment.getContents();
        this.createAt = comment.getCreateAt().toLocalDate();
        this.updateAt = comment.getUpdateAt().toLocalDate();
    }

    public CommentResponseDto(Long id, Long userId, Long scheduleId, String contents, LocalDate createAt, LocalDate updateAt) {
        this.id = id;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.contents = contents;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment.getId(), comment.getSchedule().getId(), comment.getUser().getId(),comment.getContents(), comment.getCreateAt().toLocalDate(), comment.getUpdateAt().toLocalDate());
    }
}
