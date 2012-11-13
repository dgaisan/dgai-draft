package com.onlymega.dgaisan.html5maker.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.onlymega.dgaisan.html5maker.model.Membership;
import com.onlymega.dgaisan.html5maker.model.RegistrationConfirmation;

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
	
	RegistrationConfirmation getRegisterationConfirmationByCode(String code) throws HibernateException;
	
	void removeRegistrationConfirmationentry(RegistrationConfirmation reg) throws HibernateException;
	
	//void renameObject(String bucketName, String direcotry, String objectName)
	
	
	
}
