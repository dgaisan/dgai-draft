package com.onlymega.dgaisan.html5maker.dao;

import org.hibernate.HibernateException;

import com.onlymega.dgaisan.html5maker.model.Banner;

/**
 * Banner's DAO.
 * 
 * @author Dmitri Gaisan
 *
 */
public interface BannerDao {
    /**
     * Saves a {@link Banner} on the database.
     * 
     * @param b {@link Banner}
     */
    void save(Banner b) throws HibernateException;
    
    
}
