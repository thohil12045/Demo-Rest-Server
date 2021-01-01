package com.example.demo.api.user.controller;

import com.example.demo.api.user.dto.UserDto;
import com.example.demo.api.user.model.UserRequestModel;
import com.example.demo.api.user.model.UserResponseModel;
import com.example.demo.api.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
@Slf4j
@ResponseStatus(HttpStatus.OK)
public class UserApiController implements UserApiDoc {

  private final UserService userService;

  public UserApiController(UserService userService) {
    this.userService = userService;
  }

  /**
   * create user
   */
  @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
  public UserResponseModel createUser(@RequestBody UserRequestModel userRequestModel) {
    log.debug("start createUser for userRequestModel: " + userRequestModel);

    ModelMapper modelMapper = new ModelMapper();
    UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);

    UserDto createdUser = userService.createUser(userDto);
    return modelMapper.map(createdUser, UserResponseModel.class);
  }

  /**
   * read user
   */
  @GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE })
  public UserResponseModel getUser(@PathVariable String userId) {
    log.debug("start getUser for userId: " + userId);

    UserDto selectedUser = userService.getUser(Long.valueOf(userId));
    UserResponseModel returnValue = null;
    if(selectedUser != null) {
      returnValue = new ModelMapper().map(selectedUser, UserResponseModel.class);
    }

    return returnValue;
  }

  /**
   * update user
   */
  @PutMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
  public UserResponseModel updateUser(@PathVariable String userId, @RequestBody UserRequestModel userRequestModel) {
    log.debug("start updateUser for userId: " + userId);
    log.debug("userRequestModel: " + userRequestModel);

    ModelMapper modelMapper = new ModelMapper();
    UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);

    UserDto updatedUser = userService.updateUser(Long.valueOf(userId), userDto);
    UserResponseModel returnValue = null;
    if(updatedUser != null) {
      returnValue = modelMapper.map(updatedUser, UserResponseModel.class);
    }

    return returnValue;
  }

  /**
   * delete user
   */
  @DeleteMapping(path = "/{userId}")
  public void deleteUser(@PathVariable(value = "userId") String userId) {
    log.debug("start deleteUser for userId: " + userId);
    userService.deleteUser(Long.valueOf(userId));
  }

  /**
   * list users
   */
  @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
  public List<UserResponseModel> listUsers() {
    log.debug("start listUsers");

    ModelMapper modelMapper = new ModelMapper();
    List<UserDto> users = userService.listUsers();

    List<UserResponseModel> returnValue = new ArrayList<>();
    users.forEach(user -> returnValue.add(modelMapper.map(user, UserResponseModel.class)));

    return returnValue;
  }
}
