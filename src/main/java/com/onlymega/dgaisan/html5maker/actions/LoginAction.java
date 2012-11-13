package com.onlymega.dgaisan.html5maker.actions;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.dao.UserDao;
import com.onlymega.dgaisan.html5maker.model.ActiveStatusEnum;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.model.VerifiedStatusEnum;
import com.onlymega.dgaisan.html5maker.utils.MD5Util;
import com.onlymega.dgaisan.html5maker.utils.StaticDebugger;
import com.opensymphony.xwork2.ActionSupport;



/**
 * User login/logout actions.
 * 
 * @author Dmitri Gaisan
 *
 */
public class LoginAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 2111660865197084628L;
	private static final Logger logger = Logger.getLogger(LoginAction.class.getName());
	
	private User user;
	private UserDao userService;
	
	private String login;
	private String password;
	
	private HttpServletRequest request;
	
	/**
	 * logger initializer
	 */
	static {
		//DOMConfigurator.configure("log4j.xml");
	}
	
	/**
	 * .ctor()
	 * Initialize user
	 */
	public LoginAction() {
		user = null;
		userService = null;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Validating a user
	 */
	public void validateExecute() {
		clearActionErrors();
		clearFieldErrors();
		
		if (login == null || "".equals(login)) {
			addFieldError("login", getText("login.error.missing_login"));
		}
		if (password == null || "".equals(password)) {
			addFieldError("password", getText("login.error.missing_pass"));
		}
	}
	
    @SuppressWarnings("unchecked")
	public String execute() throws Exception {
    	Map<String, Object> session = null;
    	
    	clearActionErrors();
    	System.out.println("LoginAction.execute()"); // debug
    	
    	try {
    		session = ServletActionContext.getContext().getSession();
    		
    		Integer loginAttempts = 
    			(Integer) session.get(CommonData.MULTIPLE_LOGIN_ATTEMPTS);

    		
    		if (loginAttempts != null && loginAttempts > 1) {
    			// Validate reCaptcha
    			String remoteAddr = request.getRemoteAddr();
    			ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
    			reCaptcha.setPrivateKey(getText("login.reCaptcha.private.key"));

    			String challenge = request.getParameter("recaptcha_challenge_field");
    			String uresponse = request.getParameter("recaptcha_response_field");
    			ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

    			System.out.println("challenge: " + challenge); // debug
    			System.out.println("uresponse: " + uresponse);
    			
    			if (!reCaptchaResponse.isValid()) {
    				getActionErrors().clear();
    				addActionError(getText("login.error.reCaptcha.invalid"));
    				return INPUT;
    			} 
    		}
    		
    		// retrieve a user
			this.user = userService.getUserByLoginPass(this.login, MD5Util.convertIntoMD5(this.password));

			// Handling of non-existing user.
			if (this.user == null) {
				addActionError(getText("login.error.user.not_exists"));
								
				if (loginAttempts == null) {
					loginAttempts = 0;
				}
				loginAttempts++;
				session.put(CommonData.MULTIPLE_LOGIN_ATTEMPTS, loginAttempts);
				
				return INPUT;
			} 
			
			// handling of inactive user
			if (user.getActive() == ActiveStatusEnum.INACTIVE.getValue() ||
					user.getVerified() == VerifiedStatusEnum.NOT_VERIFIED.getValue()) {
				
				addActionError(getText("login.error.user.inactive_user"));

				return INPUT;
			}
			
			session.remove(CommonData.MULTIPLE_LOGIN_ATTEMPTS);
	    	session.put(CommonData.USER_OBJECT, this.user);
	    	session.put(CommonData.LOGGED_IN, user.getRole());
	    	
	    	if (this.user.getRole() == 0) {
	    		return (CommonData.ADMIN);
	    	}
	    	
	    	Collection<String> dataIds = 
	    		(Collection<String>) session.get(CommonData.DATA_ID);
	    	
	    	if (dataIds != null && !dataIds.isEmpty()) {
	    		// User has pending banners that need to be saved
	    		// TODO: save banner in cloud
	    		return CommonData.SAVE_BANNERS;
	    	}

		} catch (Exception ex) {
			logger.logp(Level.SEVERE, LoginAction.class.getName(), "execute()", ex.getMessage(), ex);
			
			addActionError(getText("error.unknown"));
			addActionError(ex.toString());
			
			StaticDebugger.consoleLog(ex); // debug
			
			return ERROR;
		}
    	
        return SUCCESS;
    }
    
    /**
     * logout action.
     * 
     * @return SUCCESS or ERROR
     */
    public String logout() {
    	Map<String, Object> session = null;
    	User user = null;
    	
    	System.out.println("LoginAction.logout()"); // debug
    	
    	try {
    		session = ServletActionContext.getContext().getSession();
    		user  = (User) session.get("theUser");
    		
    		if (user != null) {
    			session.remove(CommonData.USER_OBJECT);
    			session.remove(CommonData.LOGGED_IN);
    			user = null;
    		}
    	} catch (Exception ex) {
    		StaticDebugger.consoleLog(ex);
    		return ERROR;
    	}
    	
    	return SUCCESS;
    }

    public String home() {
    	return SUCCESS;
    }
    
    public void setUserService(UserDao userService) {
		this.userService = userService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}