package com.onlymega.dgaisan.html5maker.dao.impl;

import org.hibernate.Session;

import com.onlymega.dgaisan.html5maker.dao.UserDao;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.utils.HibernateUtil;


/**
 * @see UserDao.
 */
public class UserDaoImpl implements UserDao {

	public User getUserByLoginPass(String login, String pass) throws Exception {
		User user = null;
		Session session = null;
		String query = "from User u where u.login = ? and u.pass = ?";

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			user = (User) session.createQuery(query)
							.setString(0, login)
							.setString(1, pass)
							.uniqueResult();
		} catch(Exception ex) {
			throw ex;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		
		return user;
	}

	public void saveUser(User user) throws Exception {
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.getTransaction().begin();
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception ex) {
			// log exception
			throw ex;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}
	                        
}