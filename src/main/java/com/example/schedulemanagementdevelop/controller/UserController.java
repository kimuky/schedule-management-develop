package com.example.schedulemanagementdevelop.controller;


import com.example.schedulemanagementdevelop.dto.UserAllResponseDto;
import com.example.schedulemanagementdevelop.dto.UserRequestDto;
import com.example.schedulemanagementdevelop.dto.UserResponseDto;
import com.example.schedulemanagementdevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUser (@RequestBody UserRequestDto requestDto) {

        UserResponseDto userResponseDto = userService.saveUser(requestDto);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserAllResponseDto>> findAllUsers () {
        List<UserAllResponseDto> userAllResponseDtoList = userService.findAllUsers();

        return new ResponseEntity<>(userAllResponseDtoList, HttpStatus.OK);
    }
}
