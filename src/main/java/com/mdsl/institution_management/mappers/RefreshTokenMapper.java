package com.mdsl.institution_management.mappers;

import org.apache.ibatis.annotations.*;
import com.mdsl.institution_management.models.RefreshToken;

/**
 * RefreshTokenMapper is an interface for MyBatis that provides methods to
 * interact with the refresh_token table. It includes methods to create,
 * retrieve, and delete refresh tokens.
 */
@Mapper
public interface RefreshTokenMapper {

	/**
	 * Creates a new refresh token in the refresh_token table.
	 * 
	 * @param refreshToken the RefreshToken object to be created.
	 */
	@Insert("INSERT INTO refresh_token (user_id, token, expiry_date) VALUES (#{userId}, #{token}, #{expiryDate})")
	@Options(useGeneratedKeys = true, keyProperty = "refreshTokenId")
	void createRefreshToken(RefreshToken refreshToken);

	/**
	 * Retrieves a refresh token by its token string.
	 * 
	 * @param token the token string of the refresh token.
	 * @return the RefreshToken object with the specified token string.
	 */
	@Select("SELECT * FROM refresh_token WHERE token = #{token}")
	@Results({ @Result(property = "refreshTokenId", column = "refresh_token_id"),
			@Result(property = "userId", column = "user_id"), @Result(property = "token", column = "token"),
			@Result(property = "expiryDate", column = "expiry_date") })
	RefreshToken getRefreshTokenByToken(@Param("token") String token);

	/**
	 * Deletes a refresh token from the refresh_token table by its ID.
	 * 
	 * @param id the ID of the refresh token to be deleted.
	 */
	@Delete("DELETE FROM refresh_token WHERE refresh_token_id = #{id}")
	void deleteRefreshToken(@Param("id") long id);
}
