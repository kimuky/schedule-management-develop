package com.example.schedulemanagementdevelop.controller;

import com.example.schedulemanagementdevelop.dto.CreateScheduleRequestDto;
import com.example.schedulemanagementdevelop.dto.CreateScheduleResponseDto;
import com.example.schedulemanagementdevelop.dto.ScheduleAllResponseDto;
import com.example.schedulemanagementdevelop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedules")
public class scheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CreateScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto requestDto) {

        CreateScheduleResponseDto savedSchedule = scheduleService.save(requestDto);

        return new ResponseEntity<>(savedSchedule, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleAllResponseDto>> findAll () {
        List<ScheduleAllResponseDto> scheduleAllResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleAllResponseDtoList, HttpStatus.OK);
    }
}
