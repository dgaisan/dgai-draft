package com.onlymega.dgaisan.html5maker.dao.impl;

import org.hibernate.HibernateException;
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
		
		System.out.println("UserDaoImpl.saveUser()");
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.getTransaction().begin();
			session.save(toBeSaved);
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			session.getTransaction().rollback();
			throw ex;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		
		System.out.println("generated ID: " + toBeSaved.getUserId());
		return toBeSaved.getUserId(); 
	}

	public void updateUser(User user) throws HibernateException {
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.getTransaction().begin();
			session.update(user);
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			session.getTransaction().rollback();
			throw ex;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
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
				System.out.println("UserDaoImpl.getUser()");
				System.out.println("get ret == null");
				
				ret = (User) session.load(User.class, userId);
				
				if (ret == null) {
					System.out.println("load ret == null");
				}
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
				session.flush();
				session.close();
			}
		}
		
		System.out.println("UserDaoImpl.userExists()");
		if (user == null) {
			System.out.println("User doesn't exist");
		} else {
			System.out.println("user " + user.getLogin() + " exists");
		}
		
		return user != null;
	}
	                        
}