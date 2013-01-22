package com.onlymega.dgaisan.html5maker.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.onlymega.dgaisan.html5maker.model.Membership;
import com.onlymega.dgaisan.html5maker.model.RegistrationConfirmation;
import com.onlymega.dgaisan.html5maker.model.User;

/**
 * This service is responsible for membership-aws related functionality.
 * 
 * 
 * @author Dmitri Gaisan
 *
 */
public interface MembershipDao {
	
	/**
	 * Retrieves all available membership types.
	 * 
	 * @return {@link List} of available {@link Membership} types.
	 * @throws Exception
	 */
	List<Membership> getAvailableMemberships() throws Exception;
	
	/**
	 * This method associates a user with a registration confirmation code.
	 * 
	 * @param userId valid id of a user
	 * @param code confirmation code
	 */
	void saveRegistrationConfirmationCode(RegistrationConfirmation reg) throws HibernateException;
	
	/**
	 * Gets a {@link RegistrationConfirmation} by its the code. 
	 * 
	 * @param code
	 * @return {@link RegistrationConfirmation} or {@code null}
	 * @throws HibernateException
	 */
	RegistrationConfirmation getRegisterationConfirmationByCode(String code) throws HibernateException;
	
	/**
	 * Removes a {@link RegistrationConfirmation} association.
	 * 
	 * @param reg {@link RegistrationConfirmation}
	 * @throws HibernateException
	 */
	void removeRegistrationConfirmation(RegistrationConfirmation reg) throws HibernateException;
	
	/**
	 * gets a random sign in token (data_type = 1) for the provided user;
	 * 
	 * @param user {@link User}
	 * @return {@link RegistrationConfirmation}
	 * @throws HibernateException
	 */
	RegistrationConfirmation getSignInTokenByUser(User user) throws HibernateException;
	
	/**
	 * Removes a registration confirmation/login token by user.
	 * 
	 * @param user {@link User}
	 * @throws HibernateException
	 */
	void removeRegistrationConfirmationsByUser(User user) throws HibernateException;
	
	//void renameObject(String bucketName, String direcotry, String objectName)
	
	
	
}
