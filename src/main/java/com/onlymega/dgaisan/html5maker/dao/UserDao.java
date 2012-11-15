package com.onlymega.dgaisan.html5maker.dao;

import org.hibernate.HibernateException;

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
	 * Gets a user by its login/email.
	 * 
	 * @param login {@link String}
	 * @return {@link User}
	 * @throws HibernateException
	 */
	User getUserByLogin(String login) throws HibernateException;
	
	/**
	 * Saves a new user.
	 * 
	 * @param user {@link User}
	 * @return newly generated user id.
	 * 
	 * @throws Exception
	 */
	int saveUser(User user) throws Exception;
	
	/**
	 * Check whether the provided login is already taken by another user.
	 * 
	 * @param login {@link String}
	 * @return {@code true} if login is taken, {@code false} otherwise
	 * @throws HibernateException
	 */
	boolean isLoginDuplicate(String login) throws HibernateException;
	
	/**
	 * Updates an existing user.
	 * 
	 * @param user {@link User}, can't be {@code null}
	 * @throws HibernateException
	 */
	void updateUser(User user) throws HibernateException;
	
	/**
	 * Gets a user by user id.
	 * 
	 * @param userId ID of a user
	 * @return {@link User} 
	 * @throws HibernateException
	 */
	User getUser(int userId) throws HibernateException;
}
