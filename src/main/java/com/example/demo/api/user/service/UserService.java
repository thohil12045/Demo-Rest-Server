package com.example.demo.api.user.service;

import com.example.demo.api.user.dto.UserDto;

import java.util.List;

public interface UserService {
  int createUsers(List<UserDto> userDtoList);
  UserDto createUser(UserDto userDto);
  UserDto getUser(Long userId);
  UserDto updateUser(Long userId, UserDto userDto);
  void deleteUser(Long userId);
  List<UserDto> listUsers(Integer maxCount);
}
