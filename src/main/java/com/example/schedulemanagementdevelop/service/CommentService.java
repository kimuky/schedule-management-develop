package com.example.schedulemanagementdevelop.service;

import com.example.schedulemanagementdevelop.dto.comment.CommentRequestDto;
import com.example.schedulemanagementdevelop.dto.comment.CommentResponseDto;
import com.example.schedulemanagementdevelop.entity.Comment;
import com.example.schedulemanagementdevelop.entity.Schedule;
import com.example.schedulemanagementdevelop.entity.User;
import com.example.schedulemanagementdevelop.repository.CommentRepository;
import com.example.schedulemanagementdevelop.repository.ScheduleRepository;
import com.example.schedulemanagementdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponseDto saveComment(Long scheduleId, CommentRequestDto requestDto, String userEmail) {

        User findUser = userRepository.findUserByEmail(userEmail).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Schedule findSchedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Comment comment = new Comment(requestDto.getContents());
        comment.setUser(findUser);
        comment.setSchedule(findSchedule);
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }

    public List<CommentResponseDto> findCommentByScheduleId(Long scheduleId) {

        List<Comment> commentList = commentRepository.findByScheduleId(scheduleId);

        return commentList.stream().map(CommentResponseDto::toDto).toList();
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, String userEmail, String contents) {
        User findUser = userRepository.findUserByEmail(userEmail).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        if (!findUser.getId().equals(comment.getUser().getId())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "작성자가 아님");
        }
        
        comment.updateContents(contents);

        return CommentResponseDto.toDto(comment);
    }

    @Transactional
    public void deleteComment(Long scheduleId, Long commentId, String userEmail) {
        scheduleRepository.findById(scheduleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User findUser = userRepository.findUserByEmail(userEmail).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!findUser.getId().equals(comment.getUser().getId())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "작성자가 아님");
        }

        commentRepository.delete(comment);
    }
}
