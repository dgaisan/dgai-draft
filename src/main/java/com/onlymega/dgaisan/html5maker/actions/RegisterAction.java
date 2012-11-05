package com.onlymega.dgaisan.html5maker.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.onlymega.dgaisan.html5maker.dao.UserDao;
import com.onlymega.dgaisan.html5maker.model.ActiveStatusEnum;
import com.onlymega.dgaisan.html5maker.model.MembershipTypesEnum;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.model.UserRoles;
import com.onlymega.dgaisan.html5maker.model.VerifiedStatusEnum;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RegisterAction extends ActionSupport implements ModelDriven<User> {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RegisterAction.class.getName());
	
	private UserDao userService;
	private User user = new User();
	
	private Collection<String> availableMemberships;
	private String membership;
	
	private String registrationConfirmationToken;
	
	public void validateExecute() {
		
		logger.log(Level.FINEST, user.toString());
		
		if (user.getLogin() == null || "".equals(user.getLogin())){
			addFieldError("login", "error.missing_login");
		}
		if (user.getPass() == null || "".equals(user.getPass())) {
			addFieldError("password", "error.missing_pass");
		}
		if (user.getUserName() == null || "".equals(user.getUserName())) {
			addFieldError("username", "error.missing_username");
		}
		
		super.validate();
	}
	
	public String initPage() throws Exception {
		availableMemberships = new ArrayList<String>();
		
		for(MembershipTypesEnum t : MembershipTypesEnum.values()) {
			availableMemberships.add(t.name());
		}
		
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		try {
			int userRole = 0;
			
			switch (MembershipTypesEnum.valueOf(getMembership())) {
				case PREMIUM: 
					userRole = UserRoles.PREMIUM_MEMBER.getValue();
					break;
				case LIMITED: 
					userRole = UserRoles.LIMITED_MEMBER.getValue();
			}
			
			user.setRole(userRole);
			user.setDateCreated(new Date());
			user.setPass(getEncriptedPassword(user.getPass()));
			user.setActive(ActiveStatusEnum.INACTIVE.getValue());
			user.setVerified(VerifiedStatusEnum.NOT_VERIFIED.getValue());
			userService.saveUser(user);
		} catch (Exception ex) {
			// log the errors
			addActionError(ex.getMessage());
			addActionError(ex.getCause().toString());
			return ERROR;
		}
		
		return SUCCESS;
	}

	/**
	 * Action for confirming regisration.
	 */
	public void confirmRegistrationAction() {
		logger.log(Level.SEVERE, "confirmRegistrationAction was called!");
		System.out.println("syso()");
		
	}
	
	public Collection<String> getAvailableMemberships() {
		return availableMemberships;
	}
	
	public void setMembership(String membership) {
		this.membership = membership;
	}
	
	public String getMembership() {
		return membership;
	}
	
	public User getModel() {
		return user;
	}
	
	public void setUserService(UserDao userService) {
		this.userService = userService;
	}
	
	private String getEncriptedPassword(String pass) {
		// encript password
		return pass;
	}

	public String getRegistrationConfirmationToken() {
		return registrationConfirmationToken;
	}
	
	public void setConfirmToken(String confirmToken) {
		System.out.println("confirmtoken = " + confirmToken);
		this.registrationConfirmationToken = confirmToken;
	}
}
