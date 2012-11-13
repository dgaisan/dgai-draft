package com.onlymega.dgaisan.html5maker.actions;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.dao.MembershipDao;
import com.onlymega.dgaisan.html5maker.dao.UserDao;
import com.onlymega.dgaisan.html5maker.model.ActiveStatusEnum;
import com.onlymega.dgaisan.html5maker.model.Membership;
import com.onlymega.dgaisan.html5maker.model.RegistrationConfirmation;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.model.VerifiedStatusEnum;
import com.onlymega.dgaisan.html5maker.utils.EmailService;
import com.onlymega.dgaisan.html5maker.utils.KeyGenerator;
import com.onlymega.dgaisan.html5maker.utils.MD5Util;
import com.onlymega.dgaisan.html5maker.utils.StaticDebugger;
import com.onlymega.dgaisan.html5maker.utils.ValidationUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RegisterAction extends ActionSupport implements ModelDriven<User>, ServletRequestAware {
	private static final long serialVersionUID = 2837183738L;
	private static final Logger logger = Logger.getLogger(RegisterAction.class.getName());
	
	private UserDao userService;
	private MembershipDao membershipService;
	
	private User user = new User();
	private HttpServletRequest request;
	
	private List<Membership> availableMemberships;
	
	private String membership;
	private String passRep;
	private String registrationCode;
	
	
	public void validateRegisterFree() throws Exception {
		System.out.println("RegisterAction.validateRegisterFree()");
		
		clearActionErrors();
		clearFieldErrors();
		
		System.out.println(getPassRep());
		System.out.println(user.getPass());
		System.out.println("user service: " + userService); // debug
		
		if (user.getLogin() == null || "".equals(user.getLogin())){
			addFieldError("login", getText("register.error.missing_login"));
		} else if (!ValidationUtil.isValidEmailAddress(user.getLogin())) {
			addFieldError("login", getText("register.error.email_format"));
		} 
		if (user.getPass() == null || "".equals(user.getPass())) {
			addFieldError("pass", getText("register.error.missing_pass"));
		} else if (user.getPass().length() < 6) {
			addFieldError("pass", getText("register.error.pass_size"));
		} 
		if (getPassRep() == null || getPassRep().equals("")) {
			addFieldError("passRep", getText("register.error.missing_pass"));
		} else if (!getPassRep().equals(user.getPass())) {
			addFieldError("passRep", getText("register.error.password_not_matching"));
		}
	}
	
	public void validateExecute() {
		System.out.println("RegisterAction.validateExecute()");
		logger.log(Level.FINEST, user.toString());
		
		clearActionErrors();
		clearFieldErrors();
		
		if (user.getLogin() == null || "".equals(user.getLogin())){
			addFieldError("login", getText("register.error.missing_login"));
		} else if (!ValidationUtil.isValidEmailAddress(user.getLogin())) {
			addFieldError("login", getText("register.error.email_format"));
		}
		if (user.getPass() == null || "".equals(user.getPass())) {
			addFieldError("pass", getText("register.error.missing_pass"));
		} else if (user.getPass().length() < 6) {
			addFieldError("pass", getText("register.error.pass_size"));
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
		
		System.out.println("Membership: " + membership);// debug
		
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
			// validate recaptcha
			if (invalidRecaptcha()) {
				getActionErrors().clear();
				getFieldErrors().clear();
				
				addActionError(getText("register.error.reCaptcha.invalid"));
				return INPUT;
			}
			
			
			if (userService.userExists(user.getLogin())) {
				// such user already exists
				addActionError(getText("register.error.login.duplicate"));
				return INPUT;
			}
			
			// save user
			user.setRole(1);
			user.setDateCreated(new Date());
			user.setPass(getEncriptedPassword(user.getPass()));
			user.setActive(ActiveStatusEnum.INACTIVE.getValue());
			user.setVerified(VerifiedStatusEnum.NOT_VERIFIED.getValue());
			user.setMembershipType(getMembershipId(getMembership()));
			
			// save the user on DB
			user.setUserId(userService.saveUser(user));
			
			// sending registration confirmation email
			registrationCode = KeyGenerator.generateRegistrationConfirmationCode();
			RegistrationConfirmation reg = new RegistrationConfirmation(registrationCode, user, new Date());
			
			membershipService.saveRegistrationConfirmationCode(reg);
			
			EmailService.sendRegistrationConfirmationEmail(user.getLogin(), 
					registrationCode);
			
		} catch (Exception e) {
			addActionError(getText("error.unknown"));
			
			System.out.println("Exception: ");
			StaticDebugger.consoleLog(e);
			
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/*
	 * Action that's called whenever a new user clicks the registration
	 * confirmation url.
	 */
	public String confirmFreeRegistration() {
		RegistrationConfirmation reg = null;
		User u = null;
		
		clearActionErrors();
		
		System.out.println("RegisterAction.confirmFreeRegistration()");
		System.out.println("confirmationCode: " + registrationCode);
		
		if (registrationCode == null || "".equals(registrationCode)) {
			return INPUT;
		}
		try {
			reg = membershipService.getRegisterationConfirmationByCode(registrationCode);	
			
			if (reg.getUser() == null) {
				System.out.println("reg.user == null");
			} else {
				System.out.println("user id: " + reg.getUser().getUserId());
			}
			
			u = userService.getUser(reg.getUser().getUserId());
			
			if (u == null) {
				System.out.println("user == null");
				addActionError("User wasn't found");
				return ERROR;
			}
			
			
			if (u.getActive() != ActiveStatusEnum.ACTIVE.getValue() 
					&& u.getVerified() != VerifiedStatusEnum.VERIFIED.getValue()) {
				u.setVerified(VerifiedStatusEnum.VERIFIED.getValue());
				u.setActive(ActiveStatusEnum.ACTIVE.getValue());
			} else {
				// user is already confirmed
				// just need to remove the registration confirm. code.
				membershipService.removeRegistrationConfirmationentry(reg);
				return SUCCESS;
			}
			
			userService.updateUser(u);
			membershipService.removeRegistrationConfirmationentry(reg);
			
		} catch (Exception e) {
			addActionError(getText("error.unknown"));
			
			StaticDebugger.consoleLog(e); // debug
			
			return ERROR;
		}
		// set user as active and verified
		// remove registration confirmation codes from DB
		// redirect user to login action
		
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
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setConfirmationCode(String code) {
		this.registrationCode = code;
	}
	
	private String getEncriptedPassword(String pass) throws Exception {
		return MD5Util.convertIntoMD5(pass);
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
}
