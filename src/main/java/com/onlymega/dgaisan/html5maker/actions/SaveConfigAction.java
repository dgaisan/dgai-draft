package com.onlymega.dgaisan.html5maker.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

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
		SessionAware,
		ServletContextAware {
	private static final long serialVersionUID = 1732828372L;
	private static final Logger logger = Logger.getLogger(SaveConfigAction.class.getName());

	private String json;
	private String html;

	private String bannerId;
	// user token is populated when user is logged in...
	private String userToken;

	private ServletContext context;
	private HttpServletResponse response;
	private Map<String, Object> session;

//	private UserDao userDao;
	private BannerService bannerService;

	public void validate() {
		//TODO validate incoming data
	}

	@Override
	public String execute() throws Exception {
		System.out.println("SaveConfigAction.execute()"); // XXX remove me
		TempData data = null;
		String dataToken = KeyGenerator.generateKey();
		String ret = "";
		String tempPath = context.getRealPath("/") + CommonData.TEMP_FOLDER;
		Collection<String> dataIds = null;

		System.out.println("temppath = " + tempPath);
		
		try {
			data = new TempData(dataToken, json, html, new Date());
			getBannerService().saveTempData(data, tempPath);

			
			dataIds = (Collection<String>) session.get(CommonData.DATA_ID);
			if (dataIds == null || dataIds.isEmpty()) {
				dataIds = new ArrayList<String>();
			}
			// TODO check if this works...
			dataIds.add(Integer.toString(data.getDataId()));
			session.put(CommonData.DATA_ID, dataIds);

			ret = dataToken;
		} catch (Exception e) {
			e.printStackTrace(); // XXX remove me
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
		this.context = context;
	}
}
