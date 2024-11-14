package com.example.schedulemanagementdevelop.service;

import com.example.schedulemanagementdevelop.dto.CreateScheduleRequestDto;
import com.example.schedulemanagementdevelop.dto.CreateScheduleResponseDto;
import com.example.schedulemanagementdevelop.dto.ScheduleAllResponseDto;
import com.example.schedulemanagementdevelop.entity.Schedule;
import com.example.schedulemanagementdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public ScheduleAllResponseDto findById(Long id) {

        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "오류"));

        return ScheduleAllResponseDto.toDto(schedule);
    }
}
