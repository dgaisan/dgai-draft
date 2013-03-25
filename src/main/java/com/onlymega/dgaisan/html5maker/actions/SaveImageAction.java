package com.onlymega.dgaisan.html5maker.actions;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.util.StringUtils;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.service.BannerService;
import com.onlymega.dgaisan.html5maker.utils.KeyGenerator;
import com.onlymega.dgaisan.html5maker.utils.TokenUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action for saving an image.
 * The file is saved into a temporary folder under the application's 
 * folder. The action is working with raw Http request and response.
 * 
 * @author Dmitri Gaisan
 *
 */
public class SaveImageAction extends ActionSupport 
	implements 
		ServletRequestAware, 
		ServletResponseAware, 
		ServletContextAware {
	
	private static final long serialVersionUID = 173281342L;
	private static final Logger logger = Logger.getLogger(SaveImageAction.class.getName());

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext servletContext;
	
	private BannerService bannerService;

	private String token;
	
	@Override
	public String execute() throws Exception {
		ServletInputStream stream = null;
		File dir = null;
		File file = null;
		FileOutputStream fios = null;
		String newFileName = "";
		String fullPath = "";
		int bytesRead = 0;
		byte[] buffer = null;

		try {
			int tempFileLen = 0;

			stream = request.getInputStream();
			newFileName = KeyGenerator.generateNameHash() + ".png";

			if (getToken() != null || !"".equals(getToken())) {
				String userId = TokenUtil.extractUserId(getToken());
				String folderName = getBannerService().getUserFolder(userId);

				fullPath = servletContext.getRealPath("/") + folderName + File.separator + newFileName;
			} else {
				fullPath = servletContext.getRealPath("/") + CommonData.TEMP_FOLDER + File.separator + newFileName;
			}
			String dirFileName = servletContext.getRealPath("/") + CommonData.TEMP_FOLDER;
			
			dir = new File(dirFileName);
			file = new File(dirFileName, newFileName);
			System.out.println("fullPath: " + fullPath);

			if (!dir.exists()) {
				dir.mkdir();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			
			fios = new FileOutputStream(file);
			buffer = new byte[ CommonData.BUFFER_SIZE];
		
			// TODO validate request!
			
			while ((bytesRead = stream.read(buffer)) > 0) {
				fios.write(buffer, 0, bytesRead);
				tempFileLen += bytesRead;
			}
			fios.flush();
			fios.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			try {
				response.getWriter().print(newFileName);
				response.getWriter().close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return null;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		System.out.println("SaveImageAction.setToken()"); // XXX remove me
		System.out.println("token == " +  token); // XXX remov eme
		this.token = token;
	}

	public BannerService getBannerService() {
		return bannerService;
	}

	public void setBannerService(BannerService bannerService) {
		this.bannerService = bannerService;
	}
}
