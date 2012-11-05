package com.onlymega.dgaisan.html5maker.dao;

import com.onlymega.dgaisan.html5maker.model.User;

/**
 * Class representing User DAO operation.
 * 
 * @author Dmitri Gaisan
 *
 */
public interface UserDao {
	/**
	 * Gets a user by its login and pass.
	 * 
	 * @param login user login (usually email)
	 * @param pass
	 * @return {@link User}, or null
	 * @throws Exception
	 */
	User getUserByLoginPass(String login, String pass) throws Exception;
	
	/**
	 * Saves a new user.
	 * 
	 * @param user {@link User}
	 * @throws Exception
	 */
	void saveUser(User user) throws Exception;
}
