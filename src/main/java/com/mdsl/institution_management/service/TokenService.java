package com.mdsl.institution_management.service;

import com.mdsl.institution_management.mapper.RefreshTokenMapper;
import com.mdsl.institution_management.model.RefreshToken;
import com.mdsl.institution_management.model.User;
import com.mdsl.institution_management.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service class for managing tokens. Provides methods for issuing and
 * validating JWTs and refresh tokens.
 */
@Service
public class TokenService {

	private final RefreshTokenMapper refreshTokenMapper;
	private final JwtUtil jwtUtil;

	@Autowired
	public TokenService(RefreshTokenMapper refreshTokenMapper, JwtUtil jwtUtil) {
		this.refreshTokenMapper = refreshTokenMapper;
		this.jwtUtil = jwtUtil;
	}

	/**
	 * Generates a JWT token for a given user.
	 *
	 * @param user the user for whom the token is generated.
	 * @return the generated JWT token.
	 */
	public String generateToken(User user) {
		return jwtUtil.generateToken(user.getUsername());
	}

	/**
	 * Generates a new refresh token for a given user.
	 *
	 * @param user the user for whom the refresh token is generated.
	 * @return the generated refresh token.
	 */
	public RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setUserId(user.getUserId());
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setExpiryDate(LocalDateTime.now().plusDays(30));

		refreshTokenMapper.createRefreshToken(refreshToken);
		return refreshToken;
	}

	/**
	 * Validates a refresh token.
	 *
	 * @param token the refresh token to validate.
	 * @return the refresh token if valid, null otherwise.
	 */
	public RefreshToken validateRefreshToken(String token) {
		RefreshToken refreshToken = refreshTokenMapper.getRefreshTokenByToken(token);
		if (refreshToken != null && refreshToken.getExpiryDate().isAfter(LocalDateTime.now())) {
			return refreshToken;
		}
		return null;
	}

	/**
	 * Deletes a refresh token by its ID.
	 *
	 * @param id the ID of the refresh token to delete.
	 */
	public void deleteRefreshToken(long id) {
		refreshTokenMapper.deleteRefreshToken(id);
	}

	/**
	 * Validates a JWT token and checks if it matches the username.
	 *
	 * @param token    the JWT token.
	 * @param username the username to validate against.
	 * @return true if the token is valid, false otherwise.
	 */
	public boolean validateToken(String token, String username) {
		return jwtUtil.validateToken(token, username);
	}

	/**
	 * Extracts the username from a JWT token.
	 *
	 * @param token the JWT token.
	 * @return the username contained in the token.
	 */
	public String extractUsername(String token) {
		return jwtUtil.extractUsername(token);
	}
}
