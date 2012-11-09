package com.onlymega.dgaisan.html5maker.actions;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.onlymega.dgaisan.html5maker.dao.MembershipDao;
import com.onlymega.dgaisan.html5maker.dao.UserDao;
import com.onlymega.dgaisan.html5maker.model.ActiveStatusEnum;
import com.onlymega.dgaisan.html5maker.model.Membership;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.model.VerifiedStatusEnum;
import com.onlymega.dgaisan.html5maker.utils.StaticDebugger;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RegisterAction extends ActionSupport implements ModelDriven<User> {
	private static final long serialVersionUID = 2837183738L;
	private static final Logger logger = Logger.getLogger(RegisterAction.class.getName());
	
	private UserDao userService;
	private MembershipDao membershipService;
	
	private User user = new User();
	
	private List<Membership> availableMemberships;
	private String membership;
	
	public void validatePreregister() {
		System.out.println("RegisterAction.validatePreregister()");
		validateExecute();
	}
	
	public void validateExecute() {
		System.out.println("RegisterAction.validateExecute()");
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
	
	public String preregister() {
		System.out.println("RegisterAction.preregister()");

		try {
			availableMemberships = membershipService.getAvailableMemberships();
		} catch (Exception e) {
			// TODO: log this exception
			System.out.println("Exception: ");
			StaticDebugger.consoleLog(e); // debug
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String initPage() throws Exception {
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		try {
			user.setRole(1);
			user.setDateCreated(new Date());
			user.setPass(getEncriptedPassword(user.getPass()));
			user.setActive(ActiveStatusEnum.INACTIVE.getValue());
			user.setVerified(VerifiedStatusEnum.NOT_VERIFIED.getValue());
			user.setMembershipType(getMembershipId(getMembership()));
			
			System.out.println(user); //debug
			//setUp account on the cloud
			
			userService.saveUser(user);
			
			// TODO send registration confirmation email!
			
		} catch (Exception ex) {
			// log the errors
			addActionError(ex.getMessage());
			addActionError(ex.getCause().toString());
			return ERROR;
		}
		
		return SUCCESS;
	}

	private int getMembershipId(String membershipName) {
		if (membershipName == null || membershipName.equals("")) {
			return 0;
		}
		for (Membership m : availableMemberships) {
			if (m.getName().equals(membershipName)) {
				return m.getId();
			}
		}
		return 0;
	}

	public List<Membership> getAvailableMemberships() {
		return availableMemberships;
	}
	
	public void setMembership(String membership) {
		this.membership = membership;
	}
	
	public String getMembership() {
		return membership;
	}
	
	public String getPassword() {
		return user == null 
			? "" 
			: user.getPass();
	}
	
	public User getModel() {
		return user;
	}
	
	public void setUserService(UserDao userService) {
		this.userService = userService;
	}
	
	public void setMembershipService(MembershipDao membershipService) {
		this.membershipService = membershipService;
	}

	private String getEncriptedPassword(String pass) {
		// TODO encript password
		return pass;
	}

}
