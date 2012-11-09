package com.onlymega.dgaisan.html5maker.actions;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.struts2.ServletActionContext;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.dao.UserDao;
import com.onlymega.dgaisan.html5maker.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * User login/logout actions.
 * 
 * @author Dmitri
 *
 */
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 2111660865197084628L;
	private static final Logger logger = Logger.getLogger(LoginAction.class.getName());
	
	private User user;
	private UserDao userService;
	
	private String login;
	private String password;
	
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
		if (login == null || "".equals(login)) {
			addFieldError("login", "error.missing_login");
		}
		if (password == null || "".equals(password)) {
			addFieldError("password", "error.missing_pass");
		}
	}
	
    @SuppressWarnings("unchecked")
	public String execute() throws Exception {
    	Map<String, Object> session = null;
    	
    	System.out.println("LoginAction.execute()"); // debug
    	
    	try {
			this.user = userService.getUserByLoginPass(this.login, this.password);
			if (this.user == null) {
				addActionError(getText("error.user_not_exists"));
				return INPUT;
			}
			
			session = ServletActionContext.getContext().getSession();
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
			
			getActionErrors().clear();
			addActionError(getText("error.unknown"));
			addActionError(ex.toString());
			addActionError(ex.getMessage());
			
			for( StackTraceElement elem : ex.getStackTrace()) {
				addActionError(elem.toString());
			}
			
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
    	
    	System.out.println("LoginAction.logout()");
    	
    	try {
    		session = ServletActionContext.getContext().getSession();
    		user  = (User) session.get("theUser");
    		
    		if (user != null) {
    			session.remove(CommonData.USER_OBJECT);
    			session.remove(CommonData.LOGGED_IN);
    			user = null;
    		}
    	} catch (Exception ex) {
    		// TODO log stack trace
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
}