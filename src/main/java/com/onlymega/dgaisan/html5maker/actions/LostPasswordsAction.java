package com.onlymega.dgaisan.html5maker.actions;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.onlymega.dgaisan.html5maker.dao.RestoredPasswordDao;
import com.onlymega.dgaisan.html5maker.dao.UserDao;
import com.onlymega.dgaisan.html5maker.model.RestoredPassword;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.utils.*;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action that's responsible for restoring lost passwords
 * 
 * @author Dmitri Gaisan
 *
 */
public class LostPasswordsAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1371937230L;

	private RestoredPasswordDao restoredPasswordService;
	private UserDao userService;
	
	private User user;
	private HttpServletRequest request;
	
	private String login;
	private String password;
	private String passwordRep;
	private String activationCode;

	/*
	public void validateSendActivationCode() {
		getFieldErrors().clear();
		getActionErrors().clear();
		
		System.out.println("LostPasswordsAction.validateSendActivationCode()");
		System.out.println("login: " + getLogin());
		
		if (getLogin() == null || "".equals(getLogin())) {
			System.out.println("login == null");
			addFieldError("login", getText("restore.error.missing_login"));
		} else if (!ValidationUtil.isValidEmailAddress(getLogin())) {
			System.out.println("Invalid email");
			addFieldError("login", getText("restore.error.email_format"));
		}
		System.out.println("here!");
		System.out.println(getActionErrors());
		System.out.println(getFieldErrors());
	}
	*/
	
	/**
	 * This action creates an activation code and emails it to the 
	 * email that's provided in the email field.
	 * 
	 * @return
	 */
	public String sendActivationCode() {
		String code = "";
		RestoredPassword restorePass = null;
		
		getActionErrors().clear();
		getFieldErrors().clear();
		
		System.out.println("LostPasswordsAction.sendActivationCode()");
		
		try {
			if (invalidRecaptcha()) {
				addActionError(getText("register.error.reCaptcha.invalid"));
				return INPUT;
			}
			
			if (!userService.isLoginDuplicate(getLogin())) {
				addActionError(getText("restore.error.email.unknown"));
				return INPUT;
			}
			
			code = KeyGenerator.generateRegistrationConfirmationCode();
			user = userService.getUserByLogin(getLogin());
			restorePass = new RestoredPassword(code, user, new Date());
			restoredPasswordService.saveActivationCode(restorePass);
			EmailService.sendPasswordRestorEmail(getLogin(), code);
			
		} catch (AddressException ae) {
			// resend email
			try {
				EmailService.sendPasswordRestorEmail(getLogin(), code);
			} catch (Exception e) {
				// couldn't send confirmation email
			}
		} catch (MessagingException me) {
			// resend email
			try {
				EmailService.sendPasswordRestorEmail(getLogin(), code);
			} catch (Exception e) {
				// couldn't send confirmation email
			}
		} catch (Exception e) {
			System.out.println("LostPasswordsAction.sendActivationCode()");
			StaticDebugger.consoleLog(e);
			
			addActionError(getText("error.unknown"));
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	private boolean invalidRecaptcha() {
		String remoteAddr = request.getRemoteAddr();
		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
		reCaptcha.setPrivateKey(getText("login.reCaptcha.private.key"));

		String challenge = request.getParameter("recaptcha_challenge_field");
		String uresponse = request.getParameter("recaptcha_response_field");
		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

		System.out.println("challenge: " + challenge); // debug
		System.out.println("uresponse: " + uresponse);
		
		return !reCaptchaResponse.isValid();
	}

	public void validateUpdatePasswordPage() {
		clearActionErrors();
		
		if (getActivationCode() == null || "".equals(getActivationCode())) {
			addActionError(getText("restore.error.activationcode.unknown"));
		}
	}
	
	/**
	 * Action that validates the activation code.
	 * 
	 * @return 
	 */
	public String updatePasswordPage() {
		RestoredPassword rp = null;
		
		System.out.println("LostPasswordsAction.updatePasswordPage()");
		
		try {
			rp = restoredPasswordService.getRestoredPassword(activationCode);
			if (rp == null) {
				addActionError(getText("restore.error.activationcode.unknown"));
				return INPUT;
			}
		} catch (Exception e) {
			// log an exception
			StaticDebugger.consoleLog(e);
			addActionError(getText("error.unknown"));
		}
		
		return SUCCESS;
	}
	
	public void validate() {
		clearActionErrors();
		clearFieldErrors();
		
		if (getPassword() == null || "".equals(getPassword())) {
			addFieldError("password", getText("restore.error.missing_login"));
		} else if (getPassword().length() < 6) {
			addFieldError("password", getText("restore.error.pass_size"));
		}
		if (getPasswordRep() == null || "".equals(getPasswordRep())) {
			addFieldError("passwordRep", getText("restore.error.missing_login"));
		} else if (getPasswordRep().length() < 6) {
			addFieldError("passwordRep", getText("restore.error.pass_size"));
		} else if (!getPassword().equals(getPasswordRep())) {
			addFieldError("passwordRep", getText("restore.error.password_not_matching = Password is not matching"));
		}
	}
	
	/**
	 * Action 
	 */
	@Override
	public String execute() throws Exception {
		System.out.println("LostPasswordsAction.execute()");
		
		RestoredPassword rp = null;
		User user = null;
		
		if (getActivationCode() == null || "".equals(getActivationCode())) {
			addActionError(getText("restore.error.activationcode.unknown"));
			return ERROR;
		}
		if (invalidRecaptcha()) {
			addActionError(getText("register.error.reCaptcha.invalid"));
			return INPUT;
		}
		
		try {
			rp = restoredPasswordService.getRestoredPassword(activationCode);
			if (rp == null) {
				addActionError(getText("restore.error.activationcode.unknown"));
				return ERROR;
			}
			user = rp.getUser();
			System.out.println("User id: " + user.getUserId());
			
			user.setPass(MD5Util.convertIntoMD5(getPassword()));
			userService.saveUser(user);
			restoredPasswordService.removeRestoredPassword(rp);
			EmailService.sendPasswordChangeNotice(user.getLogin());
			
		} catch (Exception e) {
			addActionError(getText("error.unknown"));
			return ERROR;
		}
		
		return SUCCESS;
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

	public String getPasswordRep() {
		return passwordRep;
	}

	public void setPasswordRep(String passwordRep) {
		this.passwordRep = passwordRep;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getActivationCode() {
		return activationCode;
	}
	
	public void setUserService(UserDao userService) {
		this.userService = userService;
	}

	public UserDao getUserService() {
		return userService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public RestoredPasswordDao getRestoredPasswordService() {
		return restoredPasswordService;
	}

	public void setRestoredPasswordService(
			RestoredPasswordDao restoredPasswordService) {
		this.restoredPasswordService = restoredPasswordService;
	}
	
}
