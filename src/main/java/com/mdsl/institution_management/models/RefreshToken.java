package com.mdsl.institution_management.models;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * RefreshToken is a model class representing the refresh_token table. It uses
 * Lombok annotations to generate boilerplate code such as getters, setters, and
 * toString method.
 */
@Data
public class RefreshToken {
	private Long refreshTokenId;
	private Long userId;
	private String token;
	private LocalDateTime expiryDate;
}
