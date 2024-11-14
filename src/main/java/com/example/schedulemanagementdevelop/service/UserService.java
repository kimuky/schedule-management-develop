package com.example.schedulemanagementdevelop.service;

import com.example.schedulemanagementdevelop.dto.UserResponseDto;
import com.example.schedulemanagementdevelop.dto.UserRequestDto;
import com.example.schedulemanagementdevelop.entity.User;
import com.example.schedulemanagementdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto saveUser(UserRequestDto requestDto) {

        User user = new User(requestDto.getUsername(), requestDto.getEmail());
        User SavedUser = userRepository.save(user);
        return new UserResponseDto(SavedUser);
    }
}
