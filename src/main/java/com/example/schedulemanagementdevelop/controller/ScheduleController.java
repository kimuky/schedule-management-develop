package com.example.schedulemanagementdevelop.controller;

import com.example.schedulemanagementdevelop.dto.ScheduleRequestDto;
import com.example.schedulemanagementdevelop.dto.CreateScheduleResponseDto;
import com.example.schedulemanagementdevelop.dto.ScheduleAllResponseDto;
import com.example.schedulemanagementdevelop.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/post")
    public ResponseEntity<CreateScheduleResponseDto> saveSchedule(@RequestBody ScheduleRequestDto requestDto, HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession(false);
        String userEmail = (String) session.getAttribute("id");

        CreateScheduleResponseDto savedSchedule = scheduleService.saveSchedule(requestDto, userEmail);

        return new ResponseEntity<>(savedSchedule, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleAllResponseDto>> findAllSchedules() {
        List<ScheduleAllResponseDto> scheduleAllResponseDtoList = scheduleService.findAllSchedules();

        return new ResponseEntity<>(scheduleAllResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleAllResponseDto> findByScheduleId(@PathVariable Long scheduleId) {
        ScheduleAllResponseDto responseDto = scheduleService.findByScheduleId(scheduleId);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleAllResponseDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto requestDto) {
        ScheduleAllResponseDto updatedSchedule = scheduleService.updateSchedule(scheduleId, requestDto);

        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
