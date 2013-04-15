package com.onlymega.dgaisan.html5maker.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.dao.MembershipDao;
import com.onlymega.dgaisan.html5maker.model.Membership;
import com.onlymega.dgaisan.html5maker.model.RegistrationConfirmation;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.utils.HibernateUtil;

/**
 * Default implementation of {@link MembershipDao}.
 * 
 * @author Dmitri Gaisan
 */
public class MembershipDaoImpl implements MembershipDao {

	/**
	 * TODO Update this method. It shouldn't be static.
	 */
	public List<Membership> getAvailableMemberships() throws Exception {
		List<Membership> ret = new ArrayList<Membership>();

		ret.add(new Membership(1, CommonData.FREE_MEMBERSHIP, "", 0.0, 1, 100));
		ret.add(new Membership(2, "Plus", "", 4.99, 10, 15000));
		ret.add(new Membership(3, "Premium", "", 9.49, 100, 30000));
		ret.add(new Membership(4, "Ultimate", "", 18.99, 99999, 100000));

		return ret;
	}

	public void saveRegistrationConfirmationCode(RegistrationConfirmation reg) throws HibernateException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.getTransaction().begin();
			session.save(reg);
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			// log exception
			session.getTransaction().rollback();
			throw ex;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	public RegistrationConfirmation getRegisterationConfirmationByCode(
			String code) throws HibernateException {
		Session session = null;
		RegistrationConfirmation reg = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			reg = (RegistrationConfirmation) session.createQuery("from RegistrationConfirmation r where r.confirmationCode = ?")
				.setString(0, code).uniqueResult();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return reg;
	}

	public void removeRegistrationConfirmation(RegistrationConfirmation reg)
			throws HibernateException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.getTransaction().begin();
			session.delete(reg);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	public void removeRegistrationConfirmationsByUser(User user)
			throws HibernateException {

		Session session = null;
		String query = "delete from RegistrationConfirmation r " +
				"where r.user_id = ?";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.getTransaction().begin();
			session.createQuery(query).setInteger(0, user.getUserId()).executeUpdate();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	public RegistrationConfirmation getSignInTokenByUser(User user)
			throws HibernateException {
		System.out.println("MembershipDaoImpl.getSignInTokenByUser()"); // XXX remove me
		Session session = null;
		RegistrationConfirmation reg = null;
		String q = "from RegistrationConfirmation r " +
				"where r.user.userId = ? " +
				"and r.dataType = 1";

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			reg = (RegistrationConfirmation) session.createQuery(q)
				.setInteger(0, user.getUserId()).uniqueResult();
			if (reg == null) {
				System.out.println("reg == null");
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return reg;
	}

}
