package com.onlymega.dgaisan.html5maker.service.exception;

import com.onlymega.dgaisan.html5maker.service.BannerService;

/**
 * {@link Exception} that occurs during {@link BannerService} transaction.
 * 
 * @author Dmitri Gaisan
 *
 */
public class BannerServiceException extends RuntimeException {
	private static final long serialVersionUID = 1471837L;

	public BannerServiceException() {}

	public BannerServiceException(String msg) { super(msg); }
}
