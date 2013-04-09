package com.onlymega.dgaisan.html5maker.actions;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.dao.MembershipDao;
import com.onlymega.dgaisan.html5maker.dao.UserDao;
import com.onlymega.dgaisan.html5maker.model.ActiveStatusEnum;
import com.onlymega.dgaisan.html5maker.model.Membership;
import com.onlymega.dgaisan.html5maker.model.RegistrationConfirmation;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.model.VerifiedStatusEnum;
import com.onlymega.dgaisan.html5maker.utils.KeyGenerator;
import com.onlymega.dgaisan.html5maker.utils.MD5Util;
import com.onlymega.dgaisan.html5maker.utils.TokenUtil;
import com.onlymega.dgaisan.html5maker.utils.ValidationUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * User login/logout actions.
 * 
 * @author Dmitri Gaisan
 *
 */
public class LoginAction extends ActionSupport implements ServletRequestAware, SessionAware {
	private static final long serialVersionUID = 2111660865197084628L;
	private static final Logger logger = Logger.getLogger(LoginAction.class.getName());

	private User user;
	
	// TODO replacesDAOs with BasserService
	private UserDao userDao;
	private MembershipDao membershipDao;

	private String login;
	private String password;

	private String token;

	private HttpServletRequest request;
	private Map<String, Object> session;

	
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Validating a user
	 */
	public void validateExecute() {
		clearErrors();

		if (login == null || "".equals(login)) {
			addFieldError("login", getText("login.error.missing_login"));
		} else if (!ValidationUtil.isValidEmailAddress(login)) {
			addFieldError("login", getText("register.error.email_format"));
		}

		if (password == null || "".equals(password)) {
			addFieldError("password", getText("login.error.missing_pass"));
		} else if (password.length() < 6) {
			addFieldError("password", getText("register.error.pass_size"));
		} 
	}

    @SuppressWarnings("unchecked")
	public String execute() throws Exception {
    	Integer loginAttempts = 0;
//    	String userToken = "";
//    	RegistrationConfirmation userWithToken = null;

    	System.out.println("LoginAction.execute()"); // XXX remov eme
    	
    	try {
    		loginAttempts = 
    			(Integer) session.get(CommonData.MULTIPLE_LOGIN_ATTEMPTS);

    		if (loginAttempts != null && loginAttempts > 1) {
    			// Validate reCaptcha
    			String remoteAddr = request.getRemoteAddr();
    			ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
    			reCaptcha.setPrivateKey(getText("login.reCaptcha.private.key"));

    			String challenge = request.getParameter("recaptcha_challenge_field");
    			String uresponse = request.getParameter("recaptcha_response_field");
    			ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

    			if (!reCaptchaResponse.isValid()) {
    				getActionErrors().clear();
    				addActionError(getText("login.error.reCaptcha.invalid"));
    				return INPUT;
    			} 
    		}

    		// retrieve a user
    		this.user = userDao.getUserByLoginPass(this.login, MD5Util.convertIntoMD5(this.password));

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

	    	// at this point user is logged in
	    	// TODO look at this functionality
	    	/*
	    	userWithToken = membershipDao.getSignInTokenByUser(user);
	    	if (userWithToken == null) {
		    	// add this user to the logged in user list
		    	// on the database assigning a user token to it.
		    	userToken = KeyGenerator.generateRegistrationConfirmationCode();
		    	userWithToken = new RegistrationConfirmation(userToken, 1, user, new Date());
		    	membershipDao.saveRegistrationConfirmationCode(userWithToken);	    		
		    	session.put(CommonData.USER_ONLINE_TOKEN, userToken);
	    	}
	    	 */
	    	Collection<String> dataIds = 
	    		(Collection<String>) session.get(CommonData.DATA_ID);

	    	if (dataIds != null && !dataIds.isEmpty()) {
	    		// User has pending banners that need to be saved
	    		// TODO: save banner in cloud
	    		return CommonData.SAVE_BANNERS;
	    	}

		} catch (Exception ex) {
			ex.printStackTrace(); // XXX remove me
			logger.error(ex.getMessage(), ex);
			addActionError(getText("error.unknown"));

			return ERROR;
		}
		System.out.println("Returing SUCCESS!"); // XXX remove me
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

    	try {
    		session = ServletActionContext.getContext().getSession();
    		user  = (User) session.get(CommonData.USER_OBJECT);

    		if (user != null) {
    			session.remove(CommonData.USER_OBJECT);
    			session.remove(CommonData.LOGGED_IN);
    			membershipDao.removeRegistrationConfirmationsByUser(user);
    			user = null;
    		}
    	} catch (Exception ex) {
    		ex.printStackTrace(); // XXX remove me
    		logger.error(ex.getMessage(), ex);
    		return ERROR;
    	}

    	return SUCCESS;
    }

    /**
     * Action that takes a user to the proper Dash board.
     */
    public String home() {
    	System.out.println("LoginAction.home()"); // XXX remove me

    	Map<String, Object> session = null;
    	User user = null;
    	List<Membership> availableMemberships = null;

    	try {
    		session = ServletActionContext.getContext().getSession();
    		user = (User) session.get(CommonData.USER_OBJECT);

    		if (user == null) {
    			System.out.println("user == null"); // XXX remove me!
    			return ERROR;
    		}

    		setToken(TokenUtil.getnerateToken("", String.valueOf(user.getUserId())));

    		// TODO Update this logic to call bannerService.isPremium()...
    		availableMemberships = membershipDao.getAvailableMemberships();

    		for (Membership m : availableMemberships) {
    			if (m.getId() == user.getMembershipType() && m.getName().equals(CommonData.FREE_MEMBERSHIP)) {
    				return "FREE";
    			}
    		}

    		return "PREMIUM";
		} catch (Exception e) {
			e.printStackTrace(); // XXX remove me
			return ERROR;
		}
    }

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setMembershipDao(MembershipDao membershipDao) {
		this.membershipDao = membershipDao;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}