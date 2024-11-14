package com.example.schedulemanagementdevelop.controller;

import com.example.schedulemanagementdevelop.dto.CreateScheduleRequestDto;
import com.example.schedulemanagementdevelop.dto.ScheduleResponseDto;
import com.example.schedulemanagementdevelop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedules")
public class scheduleController {

    private final ScheduleService scheduleService;


    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto requestDto) {

        ScheduleResponseDto savedSchedule = scheduleService.save(requestDto);

        return new ResponseEntity<>(savedSchedule, HttpStatus.CREATED);
    }
}
