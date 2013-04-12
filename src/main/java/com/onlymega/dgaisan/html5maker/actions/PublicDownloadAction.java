package com.onlymega.dgaisan.html5maker.actions;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.dao.TempBannerDao;
import com.onlymega.dgaisan.html5maker.model.TempBanner;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.utils.ZipPackage;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action for downloading banners. 
 * 
 * @author Dmitri Gaisan
 *
 */
public class PublicDownloadAction extends ActionSupport implements
	SessionAware,
	ServletRequestAware {

	private static final long serialVersionUID = 139372947L;
	private static final Collection<String> invalidTokens;

	private String dataToken;

	private TempBannerDao tempDataDao;

	private Map<String, Object> session;
	private HttpServletRequest request;

	static {
		invalidTokens = new ArrayList<String>();

		// TODO populate all invalid tokens
		invalidTokens.add("");
		invalidTokens.add("*");
	}

	@Override
	public void validate() {
		if (dataToken == null || invalidTokens.contains(dataToken.trim())) {
			addActionError(getText("error.download.invalid.token"));
		}

		super.validate();
	}

	@Override
	public String execute() throws Exception {
		System.out.println("PublicDownloadAction.execute()"); // XXX remove me
		User currentUser = null;

		try {			
            currentUser = (User) session.get(CommonData.USER_OBJECT);

            if (currentUser == null) {
             	return downloadPublicBanner();
            } else {
            	return downloadPremiumBanner();
            }
		} catch (Exception ex) {
			getActionErrors().clear();
			addActionError("Unable to download banner");

			ex.printStackTrace(); // XXX remove me

			return ERROR;
		}
	}

	private String downloadPublicBanner() throws Exception {
		System.out.println("PublicDownloadAction.downloadPublicBanner()"); // XXX remove me
		TempBanner tempBanner = 
			getTempDataDao().getTempBannerByToken(getDataToken());

		if (tempBanner == null) {
			return ERROR;
		}

		return SUCCESS;
	}

	private String downloadPremiumBanner() {
		System.out.println("PublicDownloadAction.downloadPremiumBanner()"); /// XXX remove me
		
		
		
		return SUCCESS;
	}
	
	public String getTestContext() {
		return request.getScheme() + "://" + request.getServerName() 
			+ (request.getServerPort() == 80 ? "" : ":"+request.getServerPort()) 
			+ request.getContextPath();
	}
	
	public String getZip() {
		// TODO will need to generate ZIP file here, instead of SaveConfig action
		return ZipPackage.getZipName(getDataToken());
	}

	public void setDataToken(String dataToken) {
		this.dataToken = dataToken;
	}

	public String getDataToken() {
		return dataToken;
	}

	public void setTempDataDao(TempBannerDao tempDataDao) {
		this.tempDataDao = tempDataDao;
	}

	public TempBannerDao getTempDataDao() {
		return tempDataDao;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
