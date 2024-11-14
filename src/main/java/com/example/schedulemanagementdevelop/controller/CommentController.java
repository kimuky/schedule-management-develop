package com.example.schedulemanagementdevelop.controller;

import com.example.schedulemanagementdevelop.dto.comment.CommentRequestDto;
import com.example.schedulemanagementdevelop.dto.comment.CommentResponseDto;
import com.example.schedulemanagementdevelop.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{scheduleId}/comment")
    public ResponseEntity<CommentResponseDto> saveComment(@PathVariable Long scheduleId, @Valid @RequestBody CommentRequestDto requestDto, HttpServletRequest servletRequest) {

        HttpSession session = servletRequest.getSession(false);
        String userEmail = (String) session.getAttribute("email");

        CommentResponseDto commentResponseDto = commentService.saveComment(scheduleId, requestDto, userEmail);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{scheduleId}/comment")
    public ResponseEntity<List<CommentResponseDto>> findCommentByScheduleId(@PathVariable Long scheduleId) {

        List<CommentResponseDto> commentResponseDtoList = commentService.findCommentByScheduleId(scheduleId);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    // 설계가 많이 잘못 된 것 같습니다.
    @PatchMapping("/{scheduleId}/comment/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long scheduleId, @Valid @PathVariable Long commentId, HttpServletRequest servletRequest, @RequestBody CommentRequestDto requestDto) {

        HttpSession session = servletRequest.getSession(false);
        String userEmail = (String) session.getAttribute("email");

        CommentResponseDto commentResponseDto = commentService.updateComment(commentId, userEmail, requestDto.getContents());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId, HttpServletRequest servletRequest) {

        HttpSession session = servletRequest.getSession(false);
        String userEmail = (String) session.getAttribute("email");

        commentService.deleteComment(scheduleId, commentId, userEmail);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
