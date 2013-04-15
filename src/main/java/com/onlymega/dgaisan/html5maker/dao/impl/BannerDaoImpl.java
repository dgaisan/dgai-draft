package com.onlymega.dgaisan.html5maker.dao.impl;

import java.math.BigInteger;
import java.util.List;

import com.onlymega.dgaisan.html5maker.dao.BannerDao;
import com.onlymega.dgaisan.html5maker.model.Banner;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Basic implementation of {@link BannerDao}.
 * 
 * @author Dmitri Gaisan
 */
public class BannerDaoImpl implements BannerDao {

    public void save(Banner b) throws HibernateException {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.save(b);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            throw ex;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

	public int countBannersByUser(String userId) throws Exception {
		Session session = null;
        String q = "Select count(*) from banners where user_id = ?";
        int ret = 100;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            BigInteger bigInteger = 
            	(BigInteger) session.createSQLQuery(q).setString(0, userId).uniqueResult();
            ret = bigInteger.intValue();
        } catch (HibernateException ex) {
            throw ex;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return ret;
	}

	public Banner getBannerById(String bannerId) {
		Session session = null;
		Banner ret = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			ret  = (Banner) session.get(Banner.class, bannerId);

			if (ret == null) {
				ret = (Banner) session.load(Banner.class, bannerId);
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

	public List<Banner> getBannersByUser(User user) throws Exception {
		String query = "Select b from Banner b where b.user.userId = ?";
		List<Banner> banners = null;
		Session s = null;

		try {
			String userId = String.valueOf(user.getUserId());

			s = HibernateUtil.getSessionFactory().openSession();
			banners = 
				s.createQuery(query).setString(0, userId).list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (s != null && s.isOpen()) {
				s.flush();
				s.close();
			}
		}

		return banners;
	} 
}
