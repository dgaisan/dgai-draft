package com.onlymega.dgaisan.html5maker.dao;

import org.hibernate.HibernateException;

import com.onlymega.dgaisan.html5maker.model.RestoredPassword;

/**
 * This service is responsible for password restore functionality.
 * 
 * @author Dmitri Gaisan
 *
 */
public interface RestoredPasswordDao {
	
	/**
	 * Saves newly generated code associated with the user.
	 * 
	 * @param code {@link RestoredPassword}
	 * @throws HibernateException
	 */
	void saveActivationCode(RestoredPassword code) throws HibernateException;
	
	/**
	 * Returns a {@link RestoredPassword} associated with the activation code
	 * 
	 * @param activationCode {@link String}
	 * @return {@link RestoredPassword}
	 * @throws HibernateException
	 */
	RestoredPassword getRestoredPassword(String activationCode) throws HibernateException;

	/**
	 * Removes a restored password entry.
	 * 
	 * @param restoredPass {@link RestoredPassword}
	 * @throws HibernateException
	 */
	void removeRestoredPassword(RestoredPassword restoredPass) throws HibernateException;
	
}
