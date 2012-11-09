package com.onlymega.dgaisan.html5maker.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.onlymega.dgaisan.html5maker.dao.MembershipDao;
import com.onlymega.dgaisan.html5maker.model.Membership;

/**
 * Default implementation of {@link MembershipDao}.
 * 
 * @author Dmitri Gaisan
 */
public class MembershipDaoImpl implements MembershipDao {

	public List<Membership> getAvailableMemberships() throws Exception {
		List<Membership> ret = new ArrayList<Membership>();
		
		ret.add(new Membership(1, "Free", "", 0.0));
		ret.add(new Membership(2, "Business Membership", "", 4.99));
		ret.add(new Membership(3, "VIP membership", "", 9.99));
		
		return ret;
	}

}
