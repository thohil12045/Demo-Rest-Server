package com.example.demo.api.user.model;

import com.example.demo.base.model.BaseResponseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserResponseModel extends BaseResponseModel {

  @ApiModelProperty(value = "userId", example="4711")
  private Long userId;

  @ApiModelProperty(value = "firstName", example="Max")
  private String firstName;

  @ApiModelProperty(value = "lastName", example="Mustermann")
  private String lastName;

  @ApiModelProperty(value = "zipCode", example="12345")
  private String zipCode;

  @ApiModelProperty(value = "city", example="Berlin")
  private String city;

  @ApiModelProperty(value = "street", example="Hauptstrasse")
  private String street;

  @ApiModelProperty(value = "houseNumber", example="15")
  private String houseNumber;

  @ApiModelProperty(value = "phoneNumber", example="0171345678")
  private String phoneNumber;

  @ApiModelProperty(value = "birthday", example="06.11.1986")
  private LocalDate birthday;

  @ApiModelProperty(value = "email", example="max.mustermann@crealogix.com")
  private String email;


}
