package com.onlymega.dgaisan.html5maker.dao.impl;


import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.onlymega.dgaisan.html5maker.dao.TempBannerDao;
import com.onlymega.dgaisan.html5maker.model.TempBanner;
import com.onlymega.dgaisan.html5maker.utils.HibernateUtil;


public class TempDataDaoImpl implements TempBannerDao {

	public void saveTempBanner(TempBanner data) throws Exception {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.getTransaction().begin();
			session.save(data);
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

	public TempBanner getTempBannerByToken(String token) throws Exception {
		Session session = null;
		TempBanner data = null;
		String queryString = 
			"from TempData d where d.dataToken = ?";

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			data = (TempBanner) session.createQuery(queryString)
										.setString(0, token)
										.uniqueResult();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}

		return data;
	}

	public TempBanner getTempBannerById(Long tempDataId) {
		Session session = null;
		TempBanner data = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			data = (TempBanner) session.get(TempBanner.class, tempDataId);
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}

		return data;
	}
}
