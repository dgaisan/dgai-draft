package com.onlymega.dgaisan.html5maker.actions;

import java.io.File;
import java.io.FileOutputStream;
//import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.utils.KeyGenerator;
import com.opensymphony.xwork2.ActionSupport;

/**
 * An action for saving and image.
 * The file is saved into a temporary folder under the application 
 * folder. The action is working with raw Http request and response.
 * 
 * @author Dmitri Gaisan
 *
 */
public class SaveImageAction extends ActionSupport implements 
	ServletRequestAware, 
	ServletResponseAware, 
	ServletContextAware,
	CommonData {
	private static final long serialVersionUID = 173281342L;
//	private static final Logger logger = Logger.getLogger(SaveImageAction.class.getName());
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext servletContext;
	
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
			
			fullPath = servletContext.getRealPath("/") + TEMP_FOLDER + File.separator + newFileName;
			String dirFileName = servletContext.getRealPath("/") + TEMP_FOLDER;

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
			buffer = new byte[ BUFFER_SIZE];
		
			// TODO validate request
			
			while ((bytesRead = stream.read(buffer)) > 0) {
				fios.write(buffer, 0, bytesRead);
				tempFileLen += bytesRead;
			}
			fios.flush();
			fios.close();
		} catch (Exception ex) {
			// TODO Log the exception
			System.out.println("Exception! in SaveImageAction");
			for (StackTraceElement el : ex.getStackTrace()) {
				System.out.println(el.toString());
			}
		} finally {
			try {
				response.getWriter().print(newFileName);
				response.getWriter().close();
			} catch (Exception e) {
				// TODO Log the exception
				System.out.println("Error sending the response");
				System.out.println(e);
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
}
