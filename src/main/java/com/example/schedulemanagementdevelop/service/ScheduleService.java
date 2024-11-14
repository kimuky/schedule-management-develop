package com.example.schedulemanagementdevelop.service;

import com.example.schedulemanagementdevelop.dto.CreateScheduleResponseDto;
import com.example.schedulemanagementdevelop.dto.ScheduleAllResponseDto;
import com.example.schedulemanagementdevelop.dto.ScheduleRequestDto;
import com.example.schedulemanagementdevelop.entity.Schedule;
import com.example.schedulemanagementdevelop.entity.User;
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
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public CreateScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto, String userEmail) {

        User findUser = userRepository.findUserByEmail(userEmail).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Schedule schedule = new Schedule(requestDto);
        schedule.setUser(findUser);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponseDto(
                savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents(), savedSchedule.getUser().getUsername());
    }

    public List<ScheduleAllResponseDto> findAllSchedules() {

        return scheduleRepository.findAll().stream().map(ScheduleAllResponseDto::toDto).toList();
    }

    public ScheduleAllResponseDto findByScheduleId(Long scheduleId) {

        Schedule findSchedule = findByIdOrElseThrow(scheduleId);

        return ScheduleAllResponseDto.toDto(findSchedule);
    }

    @Transactional
    public ScheduleAllResponseDto updateSchedule(Long scheduleId, ScheduleRequestDto requestDto) {

        Schedule findSchedule = findByIdOrElseThrow(scheduleId);

        // 일정 제목 검증
        if (requestDto.getTitle() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "필수 값 입니다.");
        }

        if (requestDto.getContents() != null) {
            findSchedule.updateContents(requestDto.getContents());
        }
        findSchedule.updateTitle(requestDto.getTitle());

        return ScheduleAllResponseDto.toDto(findSchedule);
    }

    public void deleteSchedule(Long scheduleId) {

        Schedule findSchedule = findByIdOrElseThrow(scheduleId);

        scheduleRepository.delete(findSchedule);
    }

    private Schedule findByIdOrElseThrow (Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "오류"));
    }
}
