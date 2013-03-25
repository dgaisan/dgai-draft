package com.onlymega.dgaisan.html5maker.dao.impl;


import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.onlymega.dgaisan.html5maker.dao.TempDataDao;
import com.onlymega.dgaisan.html5maker.model.TempData;
import com.onlymega.dgaisan.html5maker.utils.HibernateUtil;


public class TempDataDaoImpl implements TempDataDao {

	public void saveData(TempData data) throws Exception {
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

	public TempData getDataByToken(String token) throws Exception {
		Session session = null;
		TempData data = null;
		String queryString = "from TempData d where d.dataToken = ?";
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			data = (TempData) session.createQuery(queryString)
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

	public TempData getDataById(Long tempDataId) {
		Session session = null;
		TempData data = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			data = (TempData) session.get(TempData.class, tempDataId);
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}

		return data;
		
	}

}
