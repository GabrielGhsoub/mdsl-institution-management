package com.mdsl.institution_management.models;

import lombok.Data;

/**
 * User is a model class representing the app_user table. It uses Lombok
 * annotations to generate boilerplate code such as getters, setters, and
 * toString method.
 */
@Data
public class User {
	private Long userId;
	private String username;
	private String password;
	private Boolean enabled;
}
