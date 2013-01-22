package com.onlymega.dgaisan.html5maker.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.onlymega.dgaisan.html5maker.dao.UserDao;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.utils.HibernateUtil;


/**
 * @see {@link UserDao} .
 */
public class UserDaoImpl implements UserDao {

	public User getUserByLoginPass(String login, String pass) throws Exception {
		User user = null;
		Session session = null;
		String query = "from User u where u.login = ? and u.pass = ?";

		// TODO query user w/o password, no need to keep it in session.
		// instead query count(*) with login & pass and if it's > 0 then
		// query for the user by user name only.
		
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
	
	public User getUserByLogin(String login) throws HibernateException {
		User user = null;
		Session session = null;
		String query = "from User u where u.login = ?";

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			user = (User) session.createQuery(query)
							.setString(0, login)
							.uniqueResult();
		} catch(HibernateException ex) {
			throw ex;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		
		return user;
	}

	public int saveUser(final User user) throws HibernateException {
		Session session = null;
		User toBeSaved = new User(user);
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			session.save(toBeSaved);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) { tx.rollback(); }
			throw ex;
		} finally {
                    if (session != null && session.isOpen()) {
			session.close();
                    }
		}

		return toBeSaved.getUserId(); 
	}

	public void updateUser(User user) throws HibernateException {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			session.update(user);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) { tx.rollback(); }
			throw ex;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	public User getUser(int userId) throws HibernateException {
		Session session = null;
		User ret = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			ret  = (User) session.get(User.class, userId);
			
			if (ret == null) {
				ret = (User) session.load(User.class, userId);
			}
			
		} catch (HibernateException ex) {
			throw ex;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return ret;
	}

	public boolean isLoginDuplicate(String login) throws HibernateException {
		Session session = null;
		User user = null;
		String query = "from User u where u.login = ?";

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			user = (User) session.createQuery(query)
							.setString(0, login)
							.uniqueResult();
		} catch(HibernateException ex) {
			throw ex;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return user != null;
	}
}