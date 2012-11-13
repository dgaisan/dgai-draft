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
	
	/**
	 * TODO
	 * 
	 * @param recepient
	 * @param confirmationToken
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
		
		String url = "http://localhost:8080/draft-struts/registration/confirmation/";
		String msgBody = "Confirm your account:" 
			+ url + confirmationToken + ".html"; // TODO read from resource file.
		String msgSubject = "Account confirmation";
		
            Message msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("html5maker@onlymega.com", "html5maker@onlymega.com"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(recepient, recepient));
            msg.setSubject(msgSubject);
            msg.setText(msgBody);
            Transport.send(msg);

	}
}
