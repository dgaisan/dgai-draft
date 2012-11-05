package com.onlymega.dgaisan.html5maker.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.dao.TempDataDao;
import com.onlymega.dgaisan.html5maker.model.TempData;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport implements
	SessionAware,
	ServletContextAware,
	CommonData {
	
	private static final long serialVersionUID = 1L;
	private static final Collection<String> invalidTokens;
	
	private String dataToken;
	private TempDataDao tempDataService;
	
	private Map<String, Object> session;
	private ServletContext context;
	
	static {
		invalidTokens = new ArrayList<String>();
		invalidTokens.add("");
		invalidTokens.add("*");
	}
	
	public void validate() {
		if (dataToken == null || invalidTokens.contains(dataToken.trim())) {
			addActionError(getText("error.download.invalid.token"));
		}
		
		super.validate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		TempData data = null;
		Collection<String> dataIds;
		ZipPackage zip;
		
		try {
			data = getTempDataService().getDataByToken(getDataToken());
			if (data == null) {
				return ERROR;
			}
			
			dataIds = (Collection<String>) session.get(DATA_ID);
			if (dataIds == null || dataIds.isEmpty()) {
				dataIds = new ArrayList<String>();
			}
			
			session.put(DATA_ID, dataIds);
			
			
			// Generate a zip file
			zip = new ZipPackage("", new ArrayList<String>()); // TODO
			
			// get all pics from json
			
		} catch (Exception ex) {
			getActionErrors().clear();
			addActionError(ex.getMessage());
			// TODO log the error
			return ERROR;
		}
		
		return SUCCESS;
	}
 
	public void setDataToken(String dataToken) {
		this.dataToken = dataToken;
	}

	public String getDataToken() {
		return dataToken;
	}

	public void setTempDataService(TempDataDao tempDataService) {
		this.tempDataService = tempDataService;
	}

	public TempDataDao getTempDataService() {
		return tempDataService;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	private class ZipPackage {
		private String destFile;
		private List filesToZip;
		
		public ZipPackage(String destFile, List<String> filesToZip) {
			this.destFile = destFile;
			this.filesToZip = filesToZip;
		}
	}
}
