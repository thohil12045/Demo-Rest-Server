package com.example.demo.api.user.controller;

import com.example.demo.api.user.exception.RecordNotFoundException;
import com.example.demo.api.user.model.UserRequestModel;
import com.example.demo.api.user.model.UserResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = "User")
public interface UserApiDoc {

  @ApiOperation(value = "createUser", notes = "create new user}")
  ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody UserRequestModel userRequestModel) throws RecordNotFoundException;

  @ApiOperation(value = "getUser", notes = "get user for given id}")
  ResponseEntity<UserResponseModel> getUser(@ApiParam(value = "userId", required = true, example = "1") @NotNull Long userId) throws RecordNotFoundException;

  @ApiOperation(value = "updateUser", notes = "update user for given id}")
  ResponseEntity<UserResponseModel> updateUser(@ApiParam(value = "userId", required = true, example = "1") @NotNull Long userId, @Valid @RequestBody UserRequestModel userRequestModel) throws RecordNotFoundException;

  @ApiOperation(value = "deleteUser", notes = "delete user with id}")
  ResponseEntity deleteUser(@ApiParam(value = "userId", required = true, example = "1") @NotNull Long userId) throws RecordNotFoundException;

  @ApiOperation(value = "listUsers", notes = "list users}")
  ResponseEntity<List<UserResponseModel>> listUsers(@ApiParam(value = "maxCount", required = false, example = "1000") @Min(1) Integer maxCount) throws RecordNotFoundException;
}
