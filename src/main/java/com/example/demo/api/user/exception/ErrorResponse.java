package com.example.demo.api.user.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

  private String type;
  private Integer code;
  private String message;
  private StackTraceElement[] stacktrace;
}
