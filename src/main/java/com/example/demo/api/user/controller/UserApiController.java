package com.example.demo.api.user.controller;

import com.example.demo.api.user.dto.UserDto;
import com.example.demo.api.user.exception.RecordNotFoundException;
import com.example.demo.api.user.model.UserRequestModel;
import com.example.demo.api.user.model.UserResponseModel;
import com.example.demo.api.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/users")
@Validated
@Slf4j
public class UserApiController implements UserApiDoc {

  private final UserService userService;

  public UserApiController(UserService userService) {
    this.userService = userService;
  }

  /**
   * create user
   */
  @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<UserResponseModel> createUser(@RequestBody UserRequestModel userRequestModel) throws RecordNotFoundException {
    log.debug("start createUser for userRequestModel: " + userRequestModel);

    ModelMapper modelMapper = new ModelMapper();
    UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);

    UserDto createdUser = userService.createUser(userDto);
    UserResponseModel returnValue = modelMapper.map(createdUser, UserResponseModel.class);
    returnValue.add(linkTo(methodOn(UserApiController.class).getUser(createdUser.getUserId())).withSelfRel());

    return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
  }

  /**
   * read user
   */
  @GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<UserResponseModel> getUser(@PathVariable Long userId) throws RecordNotFoundException {
    log.debug("start getUser for userId: " + userId);

    UserDto selectedUser = userService.getUser(userId);
    UserResponseModel returnValue = null;
    if(selectedUser != null) {
      returnValue = new ModelMapper().map(selectedUser, UserResponseModel.class);
      returnValue.add(linkTo(methodOn(UserApiController.class).getUser(userId)).withSelfRel());
    }

    return ResponseEntity.status(HttpStatus.OK).body(returnValue);
  }

  /**
   * update user
   */
  @PutMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<UserResponseModel> updateUser(@PathVariable Long userId, @RequestBody UserRequestModel userRequestModel) throws RecordNotFoundException {
    log.debug("start updateUser for userId: " + userId);
    log.debug("userRequestModel: " + userRequestModel);

    ModelMapper modelMapper = new ModelMapper();
    UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);

    UserDto updatedUser = userService.updateUser(userId, userDto);
    UserResponseModel returnValue = null;
    if(updatedUser != null) {
      returnValue = modelMapper.map(updatedUser, UserResponseModel.class);
      returnValue.add(linkTo(methodOn(UserApiController.class).getUser(userId)).withSelfRel());
    }

    return ResponseEntity.status(HttpStatus.OK).body(returnValue);
  }

  /**
   * delete user
   */
  @DeleteMapping(path = "/{userId}")
  public ResponseEntity deleteUser(@PathVariable(value = "userId") Long userId) throws RecordNotFoundException {
    log.debug("start deleteUser for userId: " + userId);
    userService.deleteUser(userId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  /**
   * list users
   */
  @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<List<UserResponseModel>> listUsers(@RequestParam(value = "maxCount", required = false) Integer maxCount) throws RecordNotFoundException {
    log.debug("start listUsers");

    ModelMapper modelMapper = new ModelMapper();
    List<UserDto> users = userService.listUsers(maxCount);

    List<UserResponseModel> returnValue = new ArrayList<>();
    for (UserDto user : users) {
      UserResponseModel userResponseModel = modelMapper.map(user, UserResponseModel.class);
      userResponseModel.add(linkTo(methodOn(UserApiController.class).getUser(user.getUserId())).withSelfRel());
      returnValue.add(userResponseModel);
    }

    return ResponseEntity.status(HttpStatus.OK).body(returnValue);
  }
}
