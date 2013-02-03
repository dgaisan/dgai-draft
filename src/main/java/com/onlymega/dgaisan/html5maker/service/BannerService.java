package com.onlymega.dgaisan.html5maker.service;

import com.onlymega.dgaisan.html5maker.model.Banner;
import com.onlymega.dgaisan.html5maker.model.CloudData;
import com.onlymega.dgaisan.html5maker.model.TempData;
import java.io.IOException;
import java.io.InputStream;

public interface BannerService {
	/**
	 * Saves temporary banner (banner files including zip file).
	 * 
	 * @param data {@link TempData}
	 * @param tempDir temporary directory
	 * 
	 * @return {@link String generated data token}
	 */
	String saveTempData(final TempData data, String tempDir) throws Exception;
	
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

	
	
}
