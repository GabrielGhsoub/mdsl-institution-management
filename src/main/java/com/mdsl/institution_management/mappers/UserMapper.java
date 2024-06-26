package com.mdsl.institution_management.mappers;

import org.apache.ibatis.annotations.*;
import com.mdsl.institution_management.models.User;

/**
 * UserMapper is an interface for MyBatis that provides methods to interact with
 * the app_user table. It includes a method to validate user credentials for
 * login.
 */
@Mapper
public interface UserMapper {

	/**
	 * Validates a user's credentials.
	 * 
	 * @param username the username of the user.
	 * @param password the password of the user.
	 * @return the User object if the credentials are valid, null otherwise.
	 */
	@Select("SELECT * FROM app_user WHERE username = #{username} AND password = #{password}")
	@Results({ @Result(property = "userId", column = "user_id"), @Result(property = "username", column = "username"),
			@Result(property = "password", column = "password"), @Result(property = "enabled", column = "enabled") })
	User validateUser(@Param("username") String username, @Param("password") String password);
}
