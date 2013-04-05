package com.onlymega.dgaisan.html5maker.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.model.Banner;
import com.onlymega.dgaisan.html5maker.model.CloudData;
import com.onlymega.dgaisan.html5maker.model.TempBanner;
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
    
    /**
     * Used when editing a banner
     */
    private String bannerId;

    private BannerService bannerService;

    /**
     * An action for creating a new banner.
     * 
     * @return result
     */
    @Override
    public String execute() {
        User currentUser = null;

        System.out.println("BannerAction.execute()"); // XXX remove me

        try {
            currentUser = (User) session.get(CommonData.USER_OBJECT);

            String t = 
            	currentUser == null ? "0" 
            			: bannerService.getSignInToken(currentUser);

            System.out.println("user token = " + t); // XXX remove me
            
            setBannerId("0");
            setToken(t);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * Action for editing an existing banner
     * @return
     */
    public String editBanner() {
    	// TODO
    	
		return SUCCESS;
	}
    
    /**
     * Action for saving a new banner on the cloud.
     * 
     * @return action result
     */
    public String saveBannerAction() {
        System.out.println("saveBannerAction()");
        System.out.println("bannerId = " + bannerId);
        
        User currentUser = null;
        
        try {
            currentUser = (User) session.get(CommonData.USER_OBJECT);
            
            if (currentUser == null) {
             	logger.info("Attempt to save banner when not logged in");
                return ERROR;
            }

            if (bannerService.isPremiumAccount(currentUser)) {
            	return handlePremiumAccount(currentUser);
            }

            return handleFreeAccount(currentUser);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ERROR;
        }
        
    	//logger.error(String.
    		//	format("User [%s] is calling banner save action w/o proper params", currentUser.getUserId()));
    }

    private String handleFreeAccount(User user) throws Exception {
    	System.out.println("BannerAction.handleFreeAccount()");
    	List<TempBanner> bannersFromSession = getBannersFromSession();

    	if (bannersFromSession != null) {
    		TempBanner onlyBannerForSafe = bannersFromSession.get(0);

    		if (bannerService.countBanners(user) == 0) {
    			// if user doesn't have banners then let
    			// him save it
    			
    			// TODO
    		}

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

    		b.setActive(1);
    		b.setDateCreated(new Date());
    		b.setName(getToken());
    		b.setUser(user);
    		
    		b.setBannerFile("");
    		b.setZipFile(getToken() + ".zip");
    		
    		c.setBucketName("");
    		c.setFilename("");
    		//c.setInputFile("");
    		c.setPath("");
    		
    		// getBannerService().saveBanner(b, c);
    	}

    	
    	return ERROR;
    }
    
    private String handlePremiumAccount(User user) throws Exception {
    	List<TempBanner> bannersFromSession = getBannersFromSession(); 
    	
    	if (bannersFromSession != null) {
    		// TODO for bannersFromSession...
    		// b = new Banner();
    		// c = new Cloud();
    		// bannerservice.saveBanner(b, c);
    		
    		return SUCCESS;
    	}

       	// TODO ...
       	
       	return ERROR;
    }
    
    @SuppressWarnings("unchecked")
	private List<TempBanner> getBannersFromSession() {
    	Collection<String> dataIds = 
     		(Collection<String>) session.get(CommonData.DATA_ID);
    	List<TempBanner> ret = null; 
    	
    	if (dataIds != null && !dataIds.isEmpty()) {
    		ret = new ArrayList<TempBanner>();
     		for (String tempDataId : dataIds) {
     			ret.add(bannerService.getTempData(tempDataId));
     		}

     		dataIds.clear();
     		session.remove(CommonData.DATA_ID);
    	}
    	
        return ret;	
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
