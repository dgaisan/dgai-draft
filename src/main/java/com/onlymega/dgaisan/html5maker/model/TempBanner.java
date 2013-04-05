package com.onlymega.dgaisan.html5maker.model;

import java.io.Serializable;
import java.util.Date;

public class TempBanner implements Serializable {
	private static final long serialVersionUID = 11812798128L;

	private int bannerId;

	/**
	 * Token(Unique) to represent this data entity
	 */
	private String dataToken;

	/**
	 * Json file that holds meta data and image urls.
	 */
	private String bannerConfig;

	/**
	 * html code of the banner.
	 */
	private String htmlCode;
	private String imagesArray;
	private int bannerWidth;
	private int bannerHeight;
	private int bannerSize;
	private Date dateCreated;

	public TempBanner() { }

	public TempBanner(String dataToken, String config, String html, 
			String iArray, int bwidth, int bHeight, int bSize, Date dateCreated) {
		this.bannerConfig = config;
		this.htmlCode = html;
		this.dataToken = dataToken;
		this.imagesArray = iArray;
		this.bannerWidth = bwidth;
		this.bannerHeight = bHeight;
		this.bannerSize = bSize;
		this.dateCreated = dateCreated;
	}

	public int getBannerId() {
		return bannerId;
	}

	public void setBannerId(int banneerId) {
		this.bannerId = banneerId;
	}

	public String getBannerConfig() {
		return bannerConfig;
	}

	public void setBannerConfig(String config) {
		this.bannerConfig = config;
	}
	
	public String getHtmlCode() {
		return htmlCode;
	}

	public void setHtmlCode(String embedCode) {
		this.htmlCode = embedCode;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDataToken(String dataToken) {
		this.dataToken = dataToken;
	}

	public String getDataToken() {
		return dataToken;
	}

	public String getImagesArray() {
		return imagesArray;
	}

	public void setImagesArray(String imagesArray) {
		this.imagesArray = imagesArray;
	}

	public int getBannerWidth() {
		return bannerWidth;
	}

	public void setBannerWidth(int bannerWidth) {
		this.bannerWidth = bannerWidth;
	}

	public int getBannerHeight() {
		return bannerHeight;
	}

	public void setBannerHeight(int bannerHeight) {
		this.bannerHeight = bannerHeight;
	}

	public int getBannerSize() {
		return bannerSize;
	}

	public void setBannerSize(int bannerSize) {
		this.bannerSize = bannerSize;
	}
}
