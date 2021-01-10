package com.example.demo.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
	
	private Long userId;
	private String firstName;
	private String lastName;
	private String zipCode;
	private String city;
	private String street;
	private String houseNumber;
	private String phoneNumber;
	private LocalDate birthday;
	private String email;

}
