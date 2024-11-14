package com.example.schedulemanagementdevelop.service;

import com.example.schedulemanagementdevelop.dto.CreateScheduleRequestDto;
import com.example.schedulemanagementdevelop.dto.CreateScheduleResponseDto;
import com.example.schedulemanagementdevelop.dto.ScheduleAllResponseDto;
import com.example.schedulemanagementdevelop.entity.Schedule;
import com.example.schedulemanagementdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public CreateScheduleResponseDto save(CreateScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponseDto(savedSchedule);
    }

    public List<ScheduleAllResponseDto> findAll() {

        return scheduleRepository.findAll().stream().map(ScheduleAllResponseDto::toDto).toList();
    }
}
