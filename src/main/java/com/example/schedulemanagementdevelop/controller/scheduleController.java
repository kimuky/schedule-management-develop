package com.example.schedulemanagementdevelop.controller;

import com.example.schedulemanagementdevelop.dto.ScheduleRequestDto;
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
    public ResponseEntity<CreateScheduleResponseDto> save(@RequestBody ScheduleRequestDto requestDto) {

        CreateScheduleResponseDto savedSchedule = scheduleService.save(requestDto);

        return new ResponseEntity<>(savedSchedule, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleAllResponseDto>> findAll () {
        List<ScheduleAllResponseDto> scheduleAllResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleAllResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleAllResponseDto> findById (@PathVariable Long scheduleId) {
        ScheduleAllResponseDto responseDto = scheduleService.findById(scheduleId);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleAllResponseDto> updateSchedule (@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto requestDto) {
        ScheduleAllResponseDto updatedSchedule = scheduleService.updateSchedule(scheduleId, requestDto);

        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }
}
