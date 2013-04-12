package com.onlymega.dgaisan.html5maker.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.dao.TempBannerDao;
import com.onlymega.dgaisan.html5maker.dao.UserDao;
import com.onlymega.dgaisan.html5maker.model.RegistrationConfirmation;
import com.onlymega.dgaisan.html5maker.model.TempBanner;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.service.BannerService;
import com.onlymega.dgaisan.html5maker.utils.KeyGenerator;
import com.onlymega.dgaisan.html5maker.utils.TokenUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * A service that gets json and html raw data from the client
 * and save it on the database. It also returns a data token
 * that's assigned to this data entry on the database.
 * 
 * @author Dmitri Gaisan
 *
 */
public class SaveConfigAction extends ActionSupport 
	implements 
		ServletResponseAware,
		SessionAware,
		ServletContextAware {
	private static final long serialVersionUID = 1732828372L;
	private static final Logger logger = Logger.getLogger(SaveConfigAction.class.getName());

	private String json;
	private String html;
	private String images_array;
	private String bn_width;
	private String bn_height;

	private String token;
	private User currentUser;

	private ServletContext servletContext;
	private HttpServletResponse response;
	private Map<String, Object> session;

//	private UserDao userDao;
	private BannerService bannerService;

	public void validate() {
		//TODO validate incoming data (image)
	}

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		System.out.println("SaveConfigAction.execute()"); // XXX remove me

		TempBanner tempBanner = null;
		Collection<String> tempBannerIds = null;
		String ret = "";

		try {
			String folderToSave = getSaveFolder();
			String dataToken = KeyGenerator.generateKey();
			System.out.println("folderToSave: " + folderToSave); // XXX remove me!
			
			if (getCurrentUser() != null) {
				System.out.println("currentUser != null"); // XXX remove me
				
			} else {
				System.out.println("currentUser == null");
				tempBanner = new TempBanner(dataToken, getJson(), getHtml(), 
						getImages_array(), Integer.valueOf(getBn_width()), 
						Integer.valueOf(getBn_height()), 0, new Date());
				getBannerService().saveTempData(tempBanner, folderToSave);

				tempBannerIds = (Collection<String>) session.get(CommonData.DATA_ID);
				if (tempBannerIds == null || tempBannerIds.isEmpty()) {
					tempBannerIds = new ArrayList<String>();
				}

				// TODO check if this works...
				tempBannerIds.add(Integer.toString(tempBanner.getBannerId()));
				session.put(CommonData.DATA_ID, tempBannerIds);
			}
			


			ret = dataToken;
		} catch (Exception ex) {
			System.out.println("Exception in SaveConfig"); // XXX remove me
			System.out.println(ex.getMessage());
			System.out.print(ex.getStackTrace()[ 0].getMethodName() + "  ");
			System.out.println(ex.getStackTrace()[ 0].getLineNumber());

			
			ret = ERROR;
			logger.error(ex.getMessage(), ex);
		} finally {
			try {
				response.getWriter().print(ret);
				response.getWriter().close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		return null;
	}

	
	

	/*
	 * Gets folder(temp or user's) in which banner data will be saved.
	 * Also sets currentUser if action is called by an authorized user.
	 */
	private String getSaveFolder() throws Exception {
		System.out.println("SaveImageAction.getSaveFolder()"); // XXX remove me
		String saveFolder = servletContext.getRealPath("/") + CommonData.TEMP_FOLDER;

		if (getToken() != null && !"".equals(getToken())) {
			String userId = TokenUtil.extractUserId(getToken());
			String userFolderName = getBannerService().getUserFolder(userId);

			if (userFolderName != null) {
				saveFolder = servletContext.getRealPath("/") + CommonData.USER_FILE_FOLDER
					+ File.separator + userFolderName;
				setCurrentUser(getBannerService().getUserById(userId));
			} else {
				throw new Exception("Invalid token was passed!");
			}
		}

		return saveFolder;
	}

	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	private BannerService getBannerService() {
		return bannerService;
	}

	public void setBannerService(BannerService bannerService) {
		this.bannerService = bannerService;
	}

	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

	public String getImages_array() {
		return images_array;
	}

	public void setImages_array(String imagesArray) {
		images_array = imagesArray;
	}

	public String getBn_width() {
		return bn_width;
	}

	public void setBn_width(String bnWidth) {
		bn_width = bnWidth;
	}

	public String getBn_height() {
		return bn_height;
	}

	public void setBn_height(String bnHeight) {
		bn_height = bnHeight;
	}

	private void setCurrentUser(User u) {
		currentUser = u;
	}

	private User getCurrentUser() {
		return currentUser;
	}
}
