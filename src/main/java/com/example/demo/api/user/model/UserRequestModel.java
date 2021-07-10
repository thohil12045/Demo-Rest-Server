package com.example.demo.api.user.model;

import com.example.demo.base.model.BaseRequestModel;
import io.swagger.v3.oas.annotations.media.Schema;
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


  @Schema(name = "lastName", example="Max")
  @NotBlank
  @Size(max = 50)
  private String firstName;

  @Schema(name = "lastName", example="Mustermann")
  @NotBlank
  @Size(max = 50)
  private String lastName;

  @Schema(name = "zipCode", example="12345")
  @NotBlank
  @Size(max = 5)
  private String zipCode;

  @Schema(name = "city", example="Berlin")
  @NotBlank
  @Size(max = 50)
  private String city;

  @Schema(name = "street", example="Hauptstrasse")
  @NotBlank
  @Size(max = 50)
  private String street;

  @Schema(name = "houseNumber", example="12b")
  @NotBlank
  @Size(max = 5)
  private String houseNumber;

  @Schema(name = "phoneNumber", example="0171345678")
  @Size(max = 20)
  private String phoneNumber;

  @Schema(type = "string", name = "birthday", example="21.11.1986")
  private LocalDate birthday;

  @Schema(name = "email", example="max.mustermann@gmx.de")
  @NotBlank
  @Size(max = 50)
  private String email;
}
