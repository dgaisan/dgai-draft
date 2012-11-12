package com.onlymega.dgaisan.html5maker.dao;

import java.util.List;

import com.onlymega.dgaisan.html5maker.model.Membership;

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
	
	
	//void renameObject(String bucketName, String direcotry, String objectName)
	
	
	
}
