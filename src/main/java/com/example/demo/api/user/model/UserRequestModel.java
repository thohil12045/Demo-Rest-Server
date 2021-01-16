package com.example.demo.api.user.model;

import com.example.demo.base.model.BaseRequestModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRequestModel extends BaseRequestModel {

  @ApiModelProperty(value = "firstName", example="Max")
  @NotBlank
  @Size(max = 50)
  private String firstName;

  @ApiModelProperty(value = "lastName", example="Mustermann")
  @NotBlank
  @Size(max = 50)
  private String lastName;

  @ApiModelProperty(value = "zipCode", example="12345")
  @NotBlank
  @Size(max = 5)
  private String zipCode;

  @ApiModelProperty(value = "city", example="Berlin")
  @NotBlank
  @Size(max = 50)
  private String city;

  @ApiModelProperty(value = "street", example="Hauptstrasse")
  @NotBlank
  @Size(max = 50)
  private String street;

  @ApiModelProperty(value = "houseNumber", example="12b")
  @NotBlank
  @Size(max = 5)
  private String houseNumber;

  @ApiModelProperty(value = "phoneNumber", example="0171345678")
  @Size(max = 20)
  private String phoneNumber;

  @ApiModelProperty(value = "birthday", example="06.11.1986")
  private LocalDate birthday;

  @ApiModelProperty(value = "email", example="max.mustermann@gmx.de")
  @NotBlank
  @Size(max = 50)
  private String email;
}
