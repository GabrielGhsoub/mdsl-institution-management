package com.mdsl.institution_management.controller;

import com.mdsl.institution_management.model.RefreshToken;
import com.mdsl.institution_management.model.User;
import com.mdsl.institution_management.service.TokenService;
import com.mdsl.institution_management.service.CustomUserDetailsService;
import com.mdsl.institution_management.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for managing authentication.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	private final CustomUserDetailsService userDetailsService;
	private final TokenService tokenService;
	private final UserMapper userMapper;

	@Autowired
	public AuthController(CustomUserDetailsService userDetailsService, TokenService tokenService,
			UserMapper userMapper) {
		this.userDetailsService = userDetailsService;
		this.tokenService = tokenService;
		this.userMapper = userMapper;
	}

	/**
	 * POST /api/auth/login : authenticate the user and return a JWT token.
	 *
	 * @param loginRequest the login request containing username and password.
	 * @return the ResponseEntity with status 200 (OK) and the JWT token if
	 *         authentication is successful.
	 */
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
		String username = loginRequest.get("username");
		String password = loginRequest.get("password");

		logger.info("Login attempt for username: {}", username);

		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		if (userDetails != null && userDetailsService.passwordEncoder().matches(password, userDetails.getPassword())) {
			User user = userMapper.findByUsername(username);
			String token = tokenService.generateToken(user);
			String refreshToken = tokenService.createRefreshToken(user).getToken();

			logger.info("Login successful for username: {}", username);

			Map<String, String> response = new HashMap<>();
			response.put("token", token);
			response.put("refreshToken", refreshToken);

			return ResponseEntity.ok(response);
		} else {
			logger.warn("Login failed for username: {}", username);
			return ResponseEntity.status(401).build();
		}
	}

	/**
	 * POST /api/auth/refresh : refresh the JWT token using a refresh token.
	 *
	 * @param refreshTokenRequest the request containing the refresh token.
	 * @return the ResponseEntity with status 200 (OK) and the new JWT token if the
	 *         refresh token is valid.
	 */
	@PostMapping("/refresh")
	public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> refreshTokenRequest) {
		String refreshToken = refreshTokenRequest.get("refreshToken");
		logger.info("Refresh token attempt with token: {}", refreshToken);

		RefreshToken validRefreshToken = tokenService.validateRefreshToken(refreshToken);

		if (validRefreshToken != null) {
			User user = userMapper.findByUsernameById(validRefreshToken.getUserId());

			if (user != null) {
				String newToken = tokenService.generateToken(user);
				Map<String, String> response = new HashMap<>();
				response.put("token", newToken);

				logger.info("Refresh token successful for userId: {}", user.getUserId());

				return ResponseEntity.ok(response);
			} else {
				logger.warn("User not found for refresh token: {}", refreshToken);
				return ResponseEntity.status(401).build();
			}
		} else {
			logger.warn("Invalid refresh token: {}", refreshToken);
			return ResponseEntity.status(401).build();
		}
	}
}
