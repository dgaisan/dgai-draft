package com.onlymega.dgaisan.html5maker.actions;

import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.model.Banner;
import com.onlymega.dgaisan.html5maker.model.CloudData;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.service.BannerService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * TODO
 * 
 * @author Dmitri Gaisan
 *
 */
public class BannerAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(BannerAction.class.getName());

    private Map<String, Object> session;

    private String token;
    private String bannerId;

    private BannerService bannerService;

    /**
     * An action for creating/updating a banner.
     * 
     * @return result
     */
    @Override
    public String execute() {
        User currentUser = null;

        try {
            currentUser = (User) session.get(CommonData.USER_OBJECT);
            if (currentUser == null) {
                setToken("");
                setBannerId("");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ERROR;
        }

        if (getToken() == null || "".equals(getToken())) {
            setToken("0");
        }
        if (getBannerId() == null || "".equals(getBannerId())) {
            setBannerId("0");
        }

        return SUCCESS;
    }

    /**
     * Action for saving a new banner on the cloud.
     * 
     * @return action result
     */
    public String saveBannerAction() {
        System.out.println("saveBannerAction()");

        User currentUser = null;
        
        try {
            currentUser = (User) session.get(CommonData.USER_OBJECT);
            if (currentUser == null) {
             	logger.info("Attempt to save banner when not logged in");
                return ERROR;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ERROR;
        }

        Collection<String> dataIds = 
    		(Collection<String>) session.get(CommonData.DATA_ID);

    	if (dataIds != null && !dataIds.isEmpty()) {
    		// One or Multiple banners were created prior
    		// to user's sing in. Saving all these banners.
    		// TODO ...
    		return SUCCESS;
    	}

    	if (getBannerId() != null && !"".equals(getBannerId())) {
    		// An existing banner was edited
    		// TODO ...
    		return SUCCESS;
    	}
    	
    	if (getToken() != null && !"".equals(getToken())) {
    		// newly created banner
    		Banner b = new Banner();
    		CloudData c = new CloudData();
    		
    		System.out.println("Creating a new Banner " + getToken());
    		
    		// getBannerService().saveBanner(b, c);
    	}
        
    	logger.error(String.
    			format("User [%s] is calling banner save action w/o proper params", currentUser.getUserId()));
        return ERROR;
    }
    
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    /**
     * @return the bannerService
     */
    public BannerService getBannerService() {
        return bannerService;
    }

    /**
     * @param bannerService the bannerService to set
     */
    public void setBannerService(BannerService bannerService) {
        this.bannerService = bannerService;
    }
}
