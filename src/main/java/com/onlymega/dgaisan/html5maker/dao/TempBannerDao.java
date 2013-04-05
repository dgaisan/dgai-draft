package com.onlymega.dgaisan.html5maker.dao;

import com.onlymega.dgaisan.html5maker.model.TempBanner;

/**
 * DAO for processing {@link TempBanner}s.
 * 
 * @author Dmitri Gaisan
 *
 */
public interface TempBannerDao {

	/**
	 * Saves a new {@link TempBanner}.
	 * 
	 * @param data {@link TempBanner} to be saved.
	 * @throws Exception
	 */
	void saveTempBanner(TempBanner data) throws Exception;

	/**
	 * Retrieves a {@link TempBanner} by its {@link String token}
	 * 
	 * @param token {@link String}
	 * @return {@link TempBanner} or {@code null}
	 * @throws Exception
	 */
	TempBanner getTempBannerByToken(String token) throws Exception;

	/**
	 * Retrieves a {@link TempBanner} by its {@link Long ID}.
	 * 
	 * @param tempBannerId {@link Long}
	 * @return a {@link TempBanner} or {@code null}
	 */
	TempBanner getTempBannerById(Long tempBannerId);
}
