package com.example.schedulemanagementdevelop.service;

import com.example.schedulemanagementdevelop.dto.CreateScheduleRequestDto;
import com.example.schedulemanagementdevelop.dto.ScheduleResponseDto;
import com.example.schedulemanagementdevelop.entity.Schedule;
import com.example.schedulemanagementdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(CreateScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }
}
