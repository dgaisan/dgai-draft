package com.onlymega.dgaisan.html5maker.actions;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

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
import com.onlymega.dgaisan.html5maker.utils.ValidationUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Actions that are responsible for user registration process.
 * 
 * @author Dmitri Gaisan
 *
 */
public class RegisterAction extends ActionSupport implements 
		ModelDriven<User>, 
		ServletRequestAware,
		ServletContextAware {
	private static final long serialVersionUID = 2837183738L;
	private static final Logger logger = Logger.getLogger(RegisterAction.class.getName());

	private UserDao userService;
	private MembershipDao membershipService;
	
	private HttpServletRequest request;
	private ServletContext servletContext;

	private List<Membership> availableMemberships;

	private User user = new User();
	private String membership;
	private String passRep;
	private String registrationCode;

	
	public void validateRegisterFree() throws Exception {
		clearErrors();

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
		clearErrors();

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
		System.out.println("RegisterAction.preregister()"); // XXX remove me
		if (logger.isInfoEnabled()) {
			logger.info("Info Message!");
		}
		
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
		System.out.println("RegisterAction.membershipPage()"); // XXX remove me
		try {
			availableMemberships = membershipService.getAvailableMemberships();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
		System.out.println("RegisterAction.registerFree()"); // XXX remove me
		
		try {
			if (invalidRecaptcha()) {
				clearErrors();
				addActionError(getText("register.error.reCaptcha.invalid"));

				return INPUT;
			}
			if (userService.isLoginDuplicate(user.getLogin())) {
				logger.debug("The User already exists: " + user.getLogin());
				addActionError(getText("register.error.login.duplicate"));

				return INPUT;
			}

			String originalPass = user.getPass();
			RegistrationConfirmation reg = null;

			// save user
			user.setRole(1);
			user.setDateCreated(new Date());
			user.setPass(getEncriptedPassword(user.getPass()));
			user.setActive(ActiveStatusEnum.INACTIVE.getValue());
			user.setVerified(VerifiedStatusEnum.NOT_VERIFIED.getValue());
			user.setMembershipType(getMembershipId(getMembership()));
			String userFolder = KeyGenerator.generateKey(); 
			user.setUserFolder(userFolder);

			// save the user on DB
			user.setUserId(userService.saveUser(user));

			String file = servletContext.getRealPath("/") + KeyGenerator.generateKey();
			
			System.out.println("creating new user file: " + file); // XXX remove me
			
			// create user folder
			FileUtils.forceMkdir(new File(file));
			
			// sending registration confirmation email
			registrationCode = KeyGenerator.generateRegistrationConfirmationCode();
			reg = new RegistrationConfirmation(registrationCode, 0, user, new Date());
			membershipService.saveRegistrationConfirmationCode(reg);
			EmailService.sendRegistrationConfirmationEmail(user.getLogin(), 
					registrationCode);

			// setting original(non MD5) password on the session
			user.setPass(originalPass); 
		} catch (AddressException ae) {
			// resend email
			try {
				EmailService.sendRegistrationConfirmationEmail(user.getLogin(), 
						registrationCode);
			} catch (Exception e) {
				e.printStackTrace(); // XXX remove me
				if (logger.isInfoEnabled()) {
					logger.info("Sending registration confirmation email failed!");
					logger.info(e);
				}
			}
		} catch (MessagingException me) {
			// resend email
			try {
				EmailService.sendRegistrationConfirmationEmail(user.getLogin(), 
						registrationCode);
			} catch (Exception e) {
				e.printStackTrace(); // XXX remove me
				logger.error("Sending registration confirmation email failed!");
				logger.error(e);
			}
		} catch (Exception e) {
			e.printStackTrace(); // XXX remove me
			addActionError(getText("error.unknown"));
			logger.error(e.getMessage(), e);

			return ERROR;
		}
		
		return SUCCESS;
	}

	/**
	 * Action for re-sending confirmation email.
	 * 
	 * @return SUCCESS
	 */
	public String resendConfirmationEmail() {
		System.out.println("RegisterAction.resendConfirmationEmail()"); // XXX remove me
		if (user == null || user.getLogin() == null || "".equals(user.getLogin())) {
			return INPUT;
		}

		try {
			EmailService.sendRegistrationConfirmationEmail(user.getLogin(), 
					registrationCode);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return SUCCESS;
	}

	/*
	 * Action that's called whenever a new user clicks the registration
	 * confirmation url.
	 */
	public String confirmFreeRegistration() {
		System.out.println("RegisterAction.confirmFreeRegistration()"); // XXX remove me
		RegistrationConfirmation reg = null;
		User u = null;

		clearErrors();

		if (registrationCode == null || "".equals(registrationCode)) {
			return INPUT;
		}

		System.out.println("registration code = " + getConfirmationCode()); // XXX remove me
		
		try {
			reg = membershipService.getRegisterationConfirmationByCode(registrationCode);
			
			if (reg == null) {
				System.out.println("Couldn complete user registration: Registration code not found");
				addActionError("Registration code wasn't recognized");
				return ERROR;
			}
			
			u = userService.getUser(reg.getUser().getUserId());

			if (u == null) {
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
				membershipService.removeRegistrationConfirmation(reg);
				return SUCCESS;
			}

			userService.updateUser(u);
			membershipService.removeRegistrationConfirmation(reg);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("error.unknown"));
			logger.error(e.getMessage(), e);

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
			logger.error(ex.getMessage(), ex);
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

	public String getConfirmationCode() {
		return this.registrationCode;
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

		return !reCaptchaResponse.isValid();
	}

	public void setServletContext(ServletContext context) {
		servletContext = context;
		
	}
}
