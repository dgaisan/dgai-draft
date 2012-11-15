package com.onlymega.dgaisan.html5maker.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.onlymega.dgaisan.html5maker.dao.RestoredPasswordDao;
import com.onlymega.dgaisan.html5maker.model.RestoredPassword;
import com.onlymega.dgaisan.html5maker.utils.HibernateUtil;

public class RestoredPasswordDaoImpl implements RestoredPasswordDao {

	public RestoredPassword getRestoredPassword(String activationCode)
			throws HibernateException {
		Session session = null;
		RestoredPassword pass = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			pass = (RestoredPassword) session.createQuery("from RestoredPassword r where r.activationCode = ?")
				.setString(0, activationCode).uniqueResult();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return pass;
	}

	public void removeRestoredPassword(RestoredPassword restoredPass)
			throws HibernateException {
		
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.getTransaction().begin();
			session.delete(restoredPass);
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

	public void saveActivationCode(RestoredPassword code)
			throws HibernateException {

		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.getTransaction().begin();
			session.save(code);
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			// log exception ?
			session.getTransaction().rollback();
			throw ex;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

}
