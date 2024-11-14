package com.example.schedulemanagementdevelop.repository;


import com.example.schedulemanagementdevelop.entity.Comment;
import com.example.schedulemanagementdevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByScheduleId(Long findSchedule);

    List<Comment> findBySchedule(Schedule findSchedule);
}