package com.onlymega.dgaisan.html5maker.service;

import com.onlymega.dgaisan.html5maker.model.Banner;
import com.onlymega.dgaisan.html5maker.model.CloudData;
import com.onlymega.dgaisan.html5maker.model.TempBanner;
import com.onlymega.dgaisan.html5maker.model.User;

import java.io.IOException;
import java.io.InputStream;

public interface BannerService {
	/**
	 * Saves temporary banner (banner files including zip file).
	 * 
	 * @param data {@link TempBanner}
	 * @param tempDir temporary directory
	 * 
	 * @return {@link String generated data token}
	 */
	String saveTempData(final TempBanner data, String tempDir) throws Exception;
	
	/**
	 * Saves an image.
	 * 
	 * @param stream {@link InputStream Image as a stream}
	 * @return {@link String newly generated image name}
	 */
	String saveImage(final InputStream stream) throws IOException;

        /**
         * A service call that saves a banner data into a database as
         * well as saving the banner itself on the cloud.
         * 
         * @param b {@link Banner}
         * @param c {@link CloudData}
         * 
         * @return newly generated {@link Banner} id. 
         */
	int saveBanner(Banner b, CloudData c);

	/**
	 * TODO
	 * 
	 * @param tempDataId
	 * @return
	 */
	public TempBanner getTempData(String tempDataId);
	
	/**
	 * A service call to check whether {@link User}'s account type is PREMIUM or not.
	 * 
	 * @param user {@link User}
	 * 
	 * @return {@code true} if {@link User} has PREMIUM account, {@code false} otherwise
	 * @throws Exception
	 */
	public boolean isPremiumAccount(User user) throws Exception;
	
	/**
	 * Counts the total number of banners for a user.
	 * 
	 * @param user {@link User}
	 * @return number of banners
	 * @throws Exception
	 */
	public int countBanners(User user) throws Exception;
	
	/**
	 * Retrieves a sign in token that was assigned to the current user.
	 * 
	 * @param user {@link User}
	 * @return a {@link String token} or empty {@link String}
	 */
	String getSignInToken(User user);
	
	/**
	 * Retrieves User's personal folder by its ID.
	 * 
	 * @param userId {@link User} ID.
	 * @return {@link String} folder name.
	 */
	String getUserFolder(String userId);
}
