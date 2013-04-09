package com.onlymega.dgaisan.html5maker.actions;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

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
		System.out.println("SaveImageAction.execute()"); 

		ServletInputStream stream = null;
		String ret = "";

		try {
			int tempFileLen = 0;
			int bytesRead = 0;
			String folderToSave = getSaveFolder();
			System.out.println("folderToSave: " + folderToSave); // XXX remove me!
			String newFileName = KeyGenerator.generateNameHash() + ".png";
			File file = new File(folderToSave, newFileName);

			if (!file.exists()) {
				file.createNewFile();
			} else {
				// an unlikely scenario
				throw new Exception("File with the save name already exists!");
			}

			FileOutputStream fios = new FileOutputStream(file);
			byte[] buffer = new byte[ CommonData.BUFFER_SIZE];

			stream = request.getInputStream();

			// TODO validate request!

			while ((bytesRead = stream.read(buffer)) > 0) {
				fios.write(buffer, 0, bytesRead);
				tempFileLen += bytesRead;
			}
			fios.flush();
			fios.close();
			ret = newFileName;
		} catch (Exception ex) {
			System.out.println("Exception in SaveImage!"); // XXX remove me!
			System.out.println(ex.toString());
			System.out.println(ex.getMessage());
			System.out.print(ex.getStackTrace()[ 0].getMethodName() + "  ");
			System.out.println(ex.getStackTrace()[ 0].getLineNumber());

			logger.error(ex.getMessage(), ex);
			ret = "ERROR";
		} finally {
			try {
				System.out.println("ret = " + ret); // XXX remove me
				response.getWriter().print(ret);
				response.getWriter().close();
				stream.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		return null;
	}
	
	private String getSaveFolder() throws Exception {
		System.out.println("SaveImageAction.getSaveFolder()"); // XXX remove me
		String saveFolder = servletContext.getRealPath("/") + CommonData.TEMP_FOLDER;

		if (getToken() != null && !"".equals(getToken())) {
			String userId = TokenUtil.extractUserId(getToken());
			String userFolderName = getBannerService().getUserFolder(userId);

			if (userFolderName != null) {
				//fullPath = servletContext.getRealPath("/") + folderName + File.separator + newFileName;
				saveFolder = servletContext.getRealPath("/") + CommonData.USER_FILE_FOLDER
					+ File.separator + userFolderName;
			} else {
				throw new Exception("Invalid token was passed!");
			}
		}

		return saveFolder;
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
