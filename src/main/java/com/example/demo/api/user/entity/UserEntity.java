package com.example.demo.api.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "user")
@Data
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long userId;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String zipCode;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String street;

	@Column(nullable = false)
	private String houseNumber;

	private String phoneNumber;
	private LocalDate birthday;

	@Column(nullable = false)
	private String email;
}
