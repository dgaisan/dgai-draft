package com.onlymega.dgaisan.html5maker.model;

import java.io.Serializable;
import java.util.Date;

public class TempData implements Serializable {
	private static final long serialVersionUID = 11812798128L;

	private int dataId;
	
	/**
	 * Token(Unique) to represent this data entity
	 */
	private String dataToken;
	
	/**
	 * Json file that holds meta data and image urls.
	 */
	private String config;
	
	/**
	 * html code of the banner.
	 */
	private String embedCode;
	private Date dateCreated;
	
	public TempData() { }
	
	public TempData(String dataToken, String json, String html, Date dateCreated) {
		this.config = json;
		this.embedCode = html;
		this.dataToken = dataToken;
		this.dateCreated = dateCreated;
	}
	
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public String getEmbedCode() {
		return embedCode;
	}
	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
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
}
