package com.onlymega.dgaisan.html5maker.actions;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.onlymega.dgaisan.html5maker.dao.TempDataDao;
import com.onlymega.dgaisan.html5maker.model.TempData;
import com.onlymega.dgaisan.html5maker.utils.KeyGenerator;

import com.opensymphony.xwork2.ActionSupport;

/**
 * A service that gets json and html raw data from the client
 * and saved it on the database. It also returns a data token
 * that's assigned to this data entry on the database.
 * 
 * @author Dmitri Gaisan
 *
 */
public class SaveConfigAction extends ActionSupport implements ServletResponseAware {
	private static final long serialVersionUID = 1732828372L;
	
	private String json;
	private String html;
	
	private HttpServletResponse response;
	private TempDataDao tempDataService;
	
	public void validate() {
		System.out.println("validating SaveConfigAction");
		//TODO validate incoming data
	}
	
	@Override
	public String execute() throws Exception {
		TempData data = null;
		String dataToken = KeyGenerator.generateKey();
		String ret = "";
			
		try {
			data = new TempData(dataToken, json, html, new Date());
			getTempDataService().saveData(data);
			ret = dataToken;
		} catch (Exception e) {
			ret = ERROR;
			ret += ": " + e.getMessage();
			
			for (StackTraceElement el : e.getStackTrace()) {
				ret += el.toString();
			}
		} finally {
			try {
				response.getWriter().print(ret);
				response.getWriter().close();
			} catch (Exception e) {
				// TODO log this exception
				// TODO add error details
				addActionError(e.getMessage());
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

	public void setTempDataService(TempDataDao tempDataService) {
		this.tempDataService = tempDataService;
	}

	public TempDataDao getTempDataService() {
		return tempDataService;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}
