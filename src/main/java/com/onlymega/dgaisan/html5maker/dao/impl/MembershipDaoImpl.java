package com.onlymega.dgaisan.html5maker.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.onlymega.dgaisan.html5maker.common.CommonData;
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
		
		ret.add(new Membership(1, CommonData.FREE_MEMBERSHIP, "", 0.0));
		ret.add(new Membership(2, "Plus", "", 4.99));
		ret.add(new Membership(3, "Premium", "", 9.49));
		ret.add(new Membership(3, "Ultimate", "", 18.99));
		
		return ret;
	}

}
