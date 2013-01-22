package com.onlymega.dgaisan.html5maker.actions;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.dao.MembershipDao;
import com.onlymega.dgaisan.html5maker.dao.TempDataDao;
import com.onlymega.dgaisan.html5maker.dao.UserDao;
import com.onlymega.dgaisan.html5maker.model.RegistrationConfirmation;
import com.onlymega.dgaisan.html5maker.model.TempData;
import com.onlymega.dgaisan.html5maker.service.BannerService;
import com.onlymega.dgaisan.html5maker.utils.KeyGenerator;

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
		SessionAware {
	private static final long serialVersionUID = 1732828372L;
	private static final Logger logger = Logger.getLogger(SaveConfigAction.class.getName());

	private String json;
	private String html;

	private String bannerId;
	// user token is populated when user is logged in...
	private String userToken;

	private HttpServletResponse response;
	private Map<String, Object> session;

	private TempDataDao tempDataDao;
	private MembershipDao membershipDao;

//	private UserDao userDao;
//	private BannerService bannerService;

	static {
//		PropertyConfigurator.configure("log4j.properties");
	}

	public void validate() {
		//TODO validate incoming data
	}

	@Override
	public String execute() throws Exception {
		TempData data = null;
		String dataToken = KeyGenerator.generateKey();
		String ret = "";

		try {
			data = new TempData(dataToken, json, html, new Date());
			getTempDataDao().saveData(data); // TODO update to using BannerService
			ret = dataToken;
		} catch (Exception e) {
			ret = ERROR;
			logger.error(e.getMessage(), e);
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
	
	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public String getToken() {
		return userToken;
	}

	public void setToken(String token) {
		this.userToken = token;
	}

	public void setTempDataDao(TempDataDao tempDataDao) {
		this.tempDataDao = tempDataDao;
	}

	public TempDataDao getTempDataDao() {
		return tempDataDao;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	
	private BannerService getBannerService() {
		BannerService ret = null;
		String userToken = (String) session.get(CommonData.USER_ONLINE_TOKEN);
		RegistrationConfirmation tokenUser 
			= membershipDao.getRegisterationConfirmationByCode(userToken);
		
		// TODO
		
		
		return ret;
	}

}
