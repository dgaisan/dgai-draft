package com.onlymega.dgaisan.html5maker.actions;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.dao.MembershipDao;
import com.onlymega.dgaisan.html5maker.dao.UserDao;
import com.onlymega.dgaisan.html5maker.model.ActiveStatusEnum;
import com.onlymega.dgaisan.html5maker.model.Membership;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.model.VerifiedStatusEnum;
import com.onlymega.dgaisan.html5maker.utils.EmailService;
import com.onlymega.dgaisan.html5maker.utils.KeyGenerator;
import com.onlymega.dgaisan.html5maker.utils.MD5Util;
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
	private String passRep;
	
	
	public void validateRegisterFree() throws Exception {
		System.out.println("RegisterAction.validateRegisterFree()");
		
		getFieldErrors().clear();
		validateExecute();
		
		if (getPassRep() == null || getPassRep().equals("")) {
			addFieldError("passRep", "register.error.missing_pass");
		}
		System.out.println(getPassRep());
		System.out.println(user.getPass());
		if (!getPassRep().equals(user.getPass())) {
			addFieldError("passRep", "register.error.password_not_matching");
		}
	}
	
	public void validateExecute() {
		System.out.println("RegisterAction.validateExecute()");
		logger.log(Level.FINEST, user.toString());
		
		getFieldErrors().clear();
		
		if (user.getLogin() == null || "".equals(user.getLogin())){
			addFieldError("login", "register.error.missing_login");
		}
		if (user.getPass() == null || "".equals(user.getPass())) {
			addFieldError("pass", "register.error.missing_pass");
		}
		
		super.validate();
	}
	
	/**
	 * This action is associated with the option that was chosen
	 * on membership page. The view is returned depending on 
	 * what type of account was picked (free or any of paid accounts).
	 * 
	 * @return <pre> <b>INPUT</b>, if provided membership type does not exists,
	 * <b>{@link CommonData#FREE_MEMBERSHIP}</b> if the a free membership
	 * options was picked,
	 * <b>SUCCESS</b> - otherwise (paid membership) </pre>
	 */
	public String preregister() {
		System.out.println("RegisterAction.preregister()");
		
		if (getMembershipId(membership) == 0) {
			System.out.println("Attempted to enter wrong membership type.");
			return INPUT;
		}
		
		if (CommonData.FREE_MEMBERSHIP.equals(membership)) {
			return CommonData.FREE_MEMBERSHIP;
		}
		
		return SUCCESS;
	}		
	
	/**
	 * This action is associated with Memberships page.
	 * 
	 * @return {@link String} result
	 * @throws Exception
	 */
	public String membershipPage() throws Exception {
		System.out.println("RegisterAction.membershipPage()");
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
	
	/**
	 * Action that's associated with free users.
	 * It saves a user as a not activated user 
	 * 
	 * @return result
	 */
	public String registerFree() {
		System.out.println("RegisterAction.registerFree()");
		
		try {
			user.setRole(1);
			user.setDateCreated(new Date());
			user.setLogin(user.getLogin());
			user.setPass(getEncriptedPassword(user.getPass()));
			user.setActive(ActiveStatusEnum.INACTIVE.getValue());
			user.setVerified(VerifiedStatusEnum.NOT_VERIFIED.getValue());
			user.setMembershipType(getMembershipId(getMembership()));
			
			userService.saveUser(user);
			
			System.out.println(user); //debug
			
			// TODO: generate new key, associate with new user and save it in DB
			System.out.println("registration code: " + 
					KeyGenerator.generateRegistrationConfirmationCode());
			
			EmailService.sendRegistrationConfirmationEmail("dima.gaisan@gmail.com", 
					KeyGenerator.generateRegistrationConfirmationCode());
			
		} catch (Exception e) {
			System.out.println("Exception: ");
			StaticDebugger.consoleLog(e);
			
			return ERROR;
		}
		
		
		return SUCCESS;
	}
	
	/**
	 * Action that's responsible for processing premium users
	 */
	@Override
	public String execute() throws Exception {
		try {
			user.setRole(1);
			user.setDateCreated(new Date());
			user.setPass(getEncriptedPassword(user.getPass()));
			user.setActive(ActiveStatusEnum.ACTIVE.getValue());
			user.setVerified(VerifiedStatusEnum.VERIFIED.getValue());
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

	public void setPassRep(String passRep) {
		this.passRep = passRep;
	}

	public String getPassRep() {
		return passRep;
	}
	
	private String getEncriptedPassword(String pass) throws Exception {
		return MD5Util.convertIntoMD5(pass);
	}

}
