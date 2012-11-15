package com.onlymega.dgaisan.html5maker.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Email service.
 * 
 * @author Dmitri Gaisan
 *
 */
public class EmailService {
	
	private static final String applicationContext = 
		"http://localhost:8080/draft-struts/";
	
	private static final String registrationService = 
		"registration/confirmation/";
	private static final String restorePasswordService = 
		"restore/password/update/";
	
	/**
	 * Send out an email to confirm registration.
	 * 
	 * @param recepient {@link String} email
	 * @param confirmationToken {@link String} confirmation code
	 */
	public static void sendRegistrationConfirmationEmail(String recepient, String confirmationToken) 
		throws AddressException, MessagingException, Exception {
		
		Properties prop = new Properties();
		
		System.out.println("EmailService.sendRegistrationConfirmationEmail()"); // debug
		
		 prop.put("mail.transport.protocol", "smtp");
		 prop.put("mail.smtp.host", "smtp.emailsrvr.com");
		 prop.put("mail.smtp.auth", true);
		 prop.put("mail.smtp.starttls.enable", "false");
		
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("html5maker@onlymega.com", "Jitajita1");
			}
		});
		
		String url = applicationContext + registrationService;
		String msgBody = "Confirm your account:" 
			+ url + confirmationToken + ".html"; // TODO read from resource file.
		String msgSubject = "New Account Service";
		
            Message msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("html5maker@onlymega.com", "html5maker@onlymega.com"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(recepient, recepient));
            msg.setSubject(msgSubject);
            msg.setText(msgBody);
            Transport.send(msg);

	}
	
	/**
	 * Send out an email to restore a lost password.
	 * 
	 * @param recepient {@link String} email
	 * @param activationToken {@link String} activation code
	 */
	public static void sendPasswordRestorEmail(String recepient, String activationToken) 
		throws AddressException, MessagingException, Exception {
		
		Properties prop = new Properties();
		
		System.out.println("EmailService.sendPasswordRestorEmail()");
		
		 prop.put("mail.transport.protocol", "smtp");
		 prop.put("mail.smtp.host", "smtp.emailsrvr.com");
		 prop.put("mail.smtp.auth", true);
		 prop.put("mail.smtp.starttls.enable", "false");
		
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("html5maker@onlymega.com", "Jitajita1");
			}
		});
		
		String url = applicationContext + restorePasswordService;
		String msgBody = "Click the link to create your new password " 
			+ url + activationToken + ".html"; // TODO read from resource file.
		String msgSubject = "Password Restore Service";
		
            Message msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("html5maker@onlymega.com", "html5maker@onlymega.com"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(recepient, recepient));
            msg.setSubject(msgSubject);
            msg.setText(msgBody);
            Transport.send(msg);

	}

	/**
	 * Send out an email to restore a lost password.
	 * 
	 * @param recepient {@link String} email
	 * @param activationToken {@link String} activation code
	 */
	public static void sendPasswordChangeNotice(String recepient) 
		throws AddressException, MessagingException, Exception {
		
		Properties prop = new Properties();
		
		System.out.println("EmailService.sendPasswordRestorEmail()");
		
		 prop.put("mail.transport.protocol", "smtp");
		 prop.put("mail.smtp.host", "smtp.emailsrvr.com");
		 prop.put("mail.smtp.auth", true);
		 prop.put("mail.smtp.starttls.enable", "false");
		
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("html5maker@onlymega.com", "Jitajita1");
			}
		});
		
		String msgBody = "Your password was changed." ;
		String msgSubject = "Password change notice";
		
            Message msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("html5maker@onlymega.com", "html5maker@onlymega.com"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(recepient, recepient));
            msg.setSubject(msgSubject);
            msg.setText(msgBody);
            Transport.send(msg);

	}

	
}
