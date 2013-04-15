package com.onlymega.dgaisan.html5maker.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.onlymega.dgaisan.html5maker.model.Banner;
import com.onlymega.dgaisan.html5maker.model.User;

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
    
    /**
     * Retrieves a number of {@link Banner}s that belong to a specified {@link User}.
     * 
     * @param userId {@link String} {@link User}'s Id
     * @return number of {@link Banner}s
     * @throws Exception
     */
    int countBannersByUser(String userId) throws Exception;
    
    /**
     * Retrieves a {@link Banner} by its Id.
     * 
     * @param bannerId
     * @return
     */
    Banner getBannerById(String bannerId);

    /**
     * Retrieve a {@link List} of {@link Banner}s that belong to a user {@link User}.
     * 
     * @param user {@link User} 
     * @return a {@link List} of {@link Banner}s
     * @throws Exception
     */
	List<Banner> getBannersByUser(User user) throws Exception;
}
