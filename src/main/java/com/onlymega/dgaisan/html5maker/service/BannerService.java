package com.onlymega.dgaisan.html5maker.service;

import com.onlymega.dgaisan.html5maker.model.Banner;
import com.onlymega.dgaisan.html5maker.model.CloudData;
import com.onlymega.dgaisan.html5maker.model.Membership;
import com.onlymega.dgaisan.html5maker.model.TempBanner;
import com.onlymega.dgaisan.html5maker.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletInputStream;

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
	 * Saves an image on FS. Image is coming from an {@link ServletInputStream}.
	 * 
	 * @param stream {@link ServletInputStream} from which Image is coming
	 * @param folderToSave {@link String} path to the parent directory
	 * @param fileName {@link String} name for the image
	 * 
	 * @throws Exception
	 */
	void saveImage(ServletInputStream stream, String folderToSave, String fileName) 
		throws IOException, Exception;

    /**
     * A service call that saves a banner data into a database
     * 
     * @param b {@link Banner}
     * 
     * @return newly generated {@link Banner} id. 
     * @throws Exception
     */
	int saveBanner(Banner b) throws Exception;

	/**
	 * Retrieves an existing {@link TempBanner} by its token ID.
	 * 
	 * @param tempDataId {@link String token ID}
	 * @return {@link TempBanner}
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

	/**
	 * Retrieves a {@link User} by its id.
	 * 
	 * @param id {@link String id}
	 * @return a {@link User}, or {@code null}
	 */
	User getUserById(String id);
	
	/**
	 * Retrives a {@link Banner} by its Id.
	 * 
	 * @param bannerId {@link String} banner id
	 * @return {@link Banner} or {@code null}
	 */
	Banner getBannerById(String bannerId);
	
	/**
	 * Retrieves a {@link List} of {@link Banner}s that belong to a
	 * {@link User}.
	 * 
	 * @param user {@link User}
	 * @return a {@link List} of {@link Banner}s or empty {@link List}
	 */
	List<Banner> getBannersByUser(User user);
	
	/**
	 * Retrieves a {@link Membership} by its ID.
	 * 
	 * @param id
	 * @return {@link Membership} or {@code null}
	 */
	Membership getMembershipById(int id);
}
