package com.onlymega.dgaisan.html5maker.dao.impl;

import com.onlymega.dgaisan.html5maker.dao.BannerDao;
import com.onlymega.dgaisan.html5maker.model.Banner;
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
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.getTransaction();
            session.save(b);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) { 
                tx.rollback(); 
            }
            throw ex;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    } 
}
