package com.onlymega.dgaisan.html5maker.actions;


import java.util.ArrayList;
import java.util.Collection;

import com.onlymega.dgaisan.html5maker.dao.TempBannerDao;
import com.onlymega.dgaisan.html5maker.model.TempBanner;
import com.onlymega.dgaisan.html5maker.utils.ZipPackage;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action for downloading temporary banners. 
 * 
 * @author Dmitri Gaisan
 *
 */
public class PublicDownloadAction extends ActionSupport {	
	private static final long serialVersionUID = 1L;
	private static final Collection<String> invalidTokens;

	private String dataToken;
	private TempBannerDao tempDataDao;

	static {
		invalidTokens = new ArrayList<String>();

		// TODO populate all invalid tokens
		invalidTokens.add("");
		invalidTokens.add("*");
	}

	@Override
	public void validate() {
		if (dataToken == null || invalidTokens.contains(dataToken.trim())) {
			addActionError(getText("error.download.invalid.token"));
		}

		super.validate();
	}

	@Override
	public String execute() throws Exception {
		System.out.println("PublicDownloadAction.execute()"); // XXX remove me

		try {
			TempBanner data = getTempDataDao().getTempBannerByToken(getDataToken());
			if (data == null) {
				return ERROR;
			}
		} catch (Exception ex) {
			getActionErrors().clear();
			addActionError("Unable to download banner");
			ex.printStackTrace(); // XXX remove me

			return ERROR;
		}

		return SUCCESS;
	}

	public String getZip() {
		// TODO will need to generate ZIP file here, instead of SaveConfig action
		return ZipPackage.getZipName(getDataToken());
	}

	public void setDataToken(String dataToken) {
		this.dataToken = dataToken;
	}

	public String getDataToken() {
		return dataToken;
	}

	public void setTempDataDao(TempBannerDao tempDataDao) {
		this.tempDataDao = tempDataDao;
	}

	public TempBannerDao getTempDataDao() {
		return tempDataDao;
	}
}
