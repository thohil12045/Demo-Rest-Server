package com.example.demo.api.user.service;

import com.example.demo.api.user.dto.UserDto;
import com.example.demo.api.user.exception.RecordNotFoundException;

import java.util.List;

public interface UserService {
  int createUsers(List<UserDto> userDtoList);
  UserDto createUser(UserDto userDto);
  UserDto getUser(Long userId) throws RecordNotFoundException;
  UserDto updateUser(Long userId, UserDto userDto) throws RecordNotFoundException;
  void deleteUser(Long userId) throws RecordNotFoundException;
  List<UserDto> listUsers(Integer maxCount);
}
