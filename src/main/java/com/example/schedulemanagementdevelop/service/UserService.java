package com.example.schedulemanagementdevelop.service;

import com.example.schedulemanagementdevelop.dto.*;
import com.example.schedulemanagementdevelop.entity.User;
import com.example.schedulemanagementdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto saveUser(UserRequestDto requestDto) {

        User user = new User(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
        User SavedUser = userRepository.save(user);

        return new UserResponseDto(SavedUser);
    }

    public List<UserAllResponseDto> findAllUsers() {

        return userRepository.findAll().stream().map(UserAllResponseDto::toDto).toList();
    }

    public UserAllResponseDto findByUserId(Long userId) {

        return UserAllResponseDto.toDto(findByIdOrElseThrow(userId));
    }

    @Transactional
    public UserAllResponseDto updateUser(Long userId, UserRequestDto requestDto) {

        User findUser = findByIdOrElseThrow(userId);

        if (requestDto.getEmail() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "필수 값 입니다.");
        }

        if (requestDto.getUsername() != null) {
            findUser.updateUsername(requestDto.getUsername());
        }

        findUser.updateEmail(requestDto.getEmail());

        return UserAllResponseDto.toDto(findUser);
    }

    public void deleteUser(Long userId) {

        User findUser = findByIdOrElseThrow(userId);

        userRepository.delete(findUser);
    }

    private User findByIdOrElseThrow(Long userId) {

        return userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음"));
    }

    public LoginUserResponseDto login(LoginUserRequestDto requestDto) {

        User findUser = userRepository.findUserByEmail(requestDto.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저못 찾음"));

        if (!requestDto.getPassword().equals(findUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 실패");
        }

        return new LoginUserResponseDto(findUser);
    }
}
