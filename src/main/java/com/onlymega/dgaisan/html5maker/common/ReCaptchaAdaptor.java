package com.onlymega.dgaisan.html5maker.common;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;

/**
 * An adaptor that creates new {@link ReCaptcha}.
 * 
 * @author Dmitri Gaisan
 *
 */
public class ReCaptchaAdaptor {
	private static final String privateKey = "6LfSAdkSAAAAAMA0Dt8UPLsX1a57PbFvu1d5Ixgq";
	private static final String publicKey = "6LfSAdkSAAAAAPgPNxUiPdBmybokZOLzBoiHEp-Z";
	
	public String getHtml() {
		return ReCaptchaFactory
			.newReCaptcha(publicKey, privateKey, false)
			.createRecaptchaHtml(null, null);
	}
}
