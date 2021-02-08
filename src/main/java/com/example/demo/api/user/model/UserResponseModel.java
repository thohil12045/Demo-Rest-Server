package com.example.demo.api.user.model;

import com.example.demo.base.model.BaseResponseModel;
import io.swagger.v3.oas.annotations.media.Schema;
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

  @Schema(name = "userId", example="4711")
  private Long userId;

  @Schema(name = "firstName", example="Max")
  private String firstName;

  @Schema(name = "lastName", example="Mustermann")
  private String lastName;

  @Schema(name = "zipCode", example="12345")
  private String zipCode;

  @Schema(name = "city", example="Berlin")
  private String city;

  @Schema(name = "street", example="Hauptstrasse")
  private String street;

  @Schema(name = "houseNumber", example="12b")
  private String houseNumber;

  @Schema(name = "phoneNumber", example="0171345678")
  private String phoneNumber;

  @Schema(name = "birthday", example="06.11.1986")
  private LocalDate birthday;

  @Schema(name = "email", example="max.mustermann@gmx.de")
  private String email;
}
