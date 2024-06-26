package com.mdsl.institution_management.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

@Component
public class JwtUtil {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(String username) {
		logger.info("Generating token for username: {}", username);
		return JWT.create().withSubject(username).withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + expiration)).sign(Algorithm.HMAC512(secret));
	}

	public Boolean validateToken(String token, String username) {
		try {
			logger.info("Validating token for username: {}", username);
			Algorithm algorithm = Algorithm.HMAC512(secret);
			JWTVerifier verifier = JWT.require(algorithm).withSubject(username).build();
			DecodedJWT jwt = verifier.verify(token);
			return true;
		} catch (JWTVerificationException exception) {
			logger.error("Token validation failed: {}", exception.getMessage());
			return false;
		}
	}

	public String extractUsername(String token) {
		try {
			logger.info("Extracting username from token");
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getSubject();
		} catch (JWTDecodeException exception) {
			logger.error("Token decoding failed: {}", exception.getMessage());
			throw new JWTDecodeException("The token was expected to have 3 parts, but got 0.");
		}
	}

	public Date extractExpiration(String token) {
		logger.info("Extracting expiration date from token");
		DecodedJWT jwt = JWT.decode(token);
		return jwt.getExpiresAt();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
}
