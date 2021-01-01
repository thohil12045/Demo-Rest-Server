package com.example.demo.api.user.controller;

import com.example.demo.api.user.model.UserRequestModel;
import com.example.demo.api.user.model.UserResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Api(tags = "User")
public interface UserApiDoc {

  @ApiOperation(value = "createUser", notes = "createUser}")
  UserResponseModel createUser(@Valid @RequestBody UserRequestModel userRequestModel);

  @ApiOperation(value = "getUser", notes = "getUser}")
  UserResponseModel getUser(@ApiParam(value = "userId", required = true) @NotBlank String userId);

  @ApiOperation(value = "updateUser", notes = "updateUser}")
  UserResponseModel updateUser(@ApiParam(value = "userId", required = true) @NotBlank String userId, @Valid @RequestBody UserRequestModel userRequestModel);

  @ApiOperation(value = "deleteUser", notes = "deleteUser}")
  void deleteUser(@ApiParam(value = "userId", required = true) @NotBlank String userId);

  @ApiOperation(value = "listUsers", notes = "listUsers}")
  List<UserResponseModel> listUsers();
}
