package com.example.demo.api.user.model;

import com.example.demo.base.model.BaseRequestModel;
import com.fasterxml.jackson.annotation.JsonFormat;
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

  @NotBlank
  @Size(max = 50)
  private String firstName;

  @NotBlank
  @Size(max = 50)
  private String lastName;

  @NotBlank
  @Size(max = 5)
  private String zipCode;

  @NotBlank
  @Size(max = 50)
  private String city;

  @NotBlank
  @Size(max = 50)
  private String street;

  @NotBlank
  @Size(max = 5)
  private String houseNumber;

  @Size(max = 20)
  private String phoneNumber;

  @Size(max = 10)
  private LocalDate birthday;

  @NotBlank
  @Size(max = 50)
  private String email;
}
