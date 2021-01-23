package com.example.demo.api.user.controller;

import com.example.demo.api.user.exception.RecordNotFoundException;
import com.example.demo.api.user.model.UserRequestModel;
import com.example.demo.api.user.model.UserResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/users")
@Tag(name = "User-API", description = "A simple CRUD-API.")
public interface UserApiDoc {

  @Operation(summary = "createUser", description = "create new user")
  ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody UserRequestModel userRequestModel) throws RecordNotFoundException;

  @Operation(summary = "getUser", description = "get user for given id")
  ResponseEntity<UserResponseModel> getUser(@Parameter(name = "userId", required = true, example = "1") @NotNull Long userId) throws RecordNotFoundException;

  @Operation(summary = "updateUser", description = "update user for given id")
  ResponseEntity<UserResponseModel> updateUser(@Parameter(name = "userId", required = true, example = "1") @NotNull Long userId, @Valid @RequestBody UserRequestModel userRequestModel) throws RecordNotFoundException;

  @Operation(summary = "deleteUser", description = "delete user with id")
  ResponseEntity deleteUser(@Parameter(name = "userId", required = true, example = "1") @NotNull Long userId) throws RecordNotFoundException;

  @Operation(summary = "listUsers", description = "list users")
  ResponseEntity<List<UserResponseModel>> listUsers(@Parameter(name = "maxCount", required = false, example = "1000") @Min(1) Integer maxCount) throws RecordNotFoundException;
}
