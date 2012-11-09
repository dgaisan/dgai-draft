package com.onlymega.dgaisan.html5maker.actions;

import com.opensymphony.xwork2.ActionSupport;


/**
 * Action that saves a newly generated banner in the cloud
 * (Instead of just providing a file for downloading).
 * 
 * @author Dmitri Gaisan
 *
 */
public class SaveBannerAction extends ActionSupport {
	
	private static final long serialVersionUID = 82736819181L;

	@Override
	public String execute() throws Exception {
		// TODO
		System.out.println("SaveBannerAction.execute()");
		return super.execute();
	}
}
