package com.example.schedulemanagementdevelop.service;

import com.example.schedulemanagementdevelop.dto.UserAllResponseDto;
import com.example.schedulemanagementdevelop.dto.UserRequestDto;
import com.example.schedulemanagementdevelop.dto.UserResponseDto;
import com.example.schedulemanagementdevelop.entity.User;
import com.example.schedulemanagementdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto saveUser(UserRequestDto requestDto) {

        User user = new User(requestDto.getUsername(), requestDto.getEmail());
        User SavedUser = userRepository.save(user);
        return new UserResponseDto(SavedUser);
    }

    public List<UserAllResponseDto> findAllUsers() {

       return userRepository.findAll().stream().map(UserAllResponseDto::toDto).toList();
    }
}
