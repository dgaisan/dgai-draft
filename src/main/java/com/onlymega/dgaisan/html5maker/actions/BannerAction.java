package com.onlymega.dgaisan.html5maker.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.model.Banner;
import com.onlymega.dgaisan.html5maker.model.CloudData;
import com.onlymega.dgaisan.html5maker.model.Membership;
import com.onlymega.dgaisan.html5maker.model.TempBanner;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.service.BannerService;
import com.onlymega.dgaisan.html5maker.utils.KeyGenerator;
import com.onlymega.dgaisan.html5maker.utils.TokenUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action for working with banners. 
 * 
 * @author Dmitri Gaisan
 *
 */
public class BannerAction extends ActionSupport implements 
	SessionAware, ServletContextAware {
    
	private static final long serialVersionUID = 3819374929293L;
    private static final Logger logger = Logger.getLogger(BannerAction.class.getName());

    private static final String PREMIUM_DASHBOARD = "PREMIUM";
    private static final String LIMITED_DASHBOARD = "FREE";

    private Map<String, Object> session;
    private ServletContext context;

    private String token;
    private String totalBanners;
    private String usedTraffic;
    private String totalTraffic;
    private String membershipPlan;

	private List<Banner> banners;    

    private BannerService bannerService;

    /**
     * Dashboard action
     */
    @Override
    public String execute() {
    	System.out.println("BannerAction.execute()"); // XXX remove me

    	try {
    		User user = (User) session.get(CommonData.USER_OBJECT);

    		if (user == null) {
    			System.out.println("user == null"); // XXX remove me!

    			getActionErrors().clear();
    			getActionErrors().add("Unable to identify user");

    			return ERROR;
    		}

    		setToken(TokenUtil.getnerateToken("", String.valueOf(user.getUserId())));
    		setBanners(getBannerService().getBannersByUser(user));

    		System.out.println("# of banners: " + getBanners().size());

    		int countBanners = getBannerService().countBanners(user);
    		Membership membershipType = getBannerService().getMembershipById(user.getMembershipType());

    		if (countBanners > 0) {
    			setTotalBanners(String.valueOf(countBanners));
    		} else {
    			setTotalBanners("0");
    		}
    		setTotalTraffic(String.valueOf(membershipType.getTotalTraffic()));
    		setUsedTraffic(""); // TODO CACLULATE
    		setMembershipPlan(membershipType.getName());
    		
    		
    		if (getBannerService().isPremiumAccount(user)) {
    			// ...
    			return PREMIUM_DASHBOARD;
    		}
    		    		
    		
    		return LIMITED_DASHBOARD;
 
		} catch (Exception e) {
			e.printStackTrace(); // XXX remove me
			return ERROR;
		}
    }

    /**
     * An action for creating a new banner.
     * 
     * @return result
     */

    public String createNew() {
        User currentUser = null;

        System.out.println("BannerAction.createNew()"); // XXX remove me

        try {
            currentUser = (User) session.get(CommonData.USER_OBJECT);

            if (currentUser == null) {
            	setToken("");
            } else {
            	setToken(TokenUtil.getnerateToken("", String.valueOf(currentUser.getUserId())));
            }

            System.out.println("user token = " + getToken()); // XXX remove me
        } catch (Exception e) {
        	System.out.println("Exception " + e.getMessage()); // XXX remove me

            logger.error(e.getMessage(), e);
            return ERROR;
        }

        return SUCCESS;

    }

    /**
     * Action for editing an existing banner
     * 
     * @return
     */
    public String editBanner() {
    	// TODO
    	
		return SUCCESS;
	}
    
    private String bannerId;
    
    public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	
	private InputStream fileInputStream;
	 
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
 
	/**
     * Action responsible for downloading user's banner
     * 
     * @return
     */
	/*
    public String download()  {
    	System.out.println("BannerAction.download()"); // XXX remove me
    	System.out.println("bannerId = " + getBannerId()); 

    	
    	
    	User user = (User) session.get(CommonData.USER_OBJECT);

    	try {
			if (getBannerService().isPremiumAccount(user)) {
				return PREMIUM_DASHBOARD;
			}
		} catch (Exception e) {
			e.printStackTrace(); // XXX remove me
			return ERROR;
		}

		return LIMITED_DASHBOARD;
	}
    */
	
    public String download() {
    	System.out.println("BannerAction.download()");
    	System.out.println("bannerId = " + getBannerId());

    	try {
    		User user = (User) session.get(CommonData.USER_OBJECT);
    		String userFolderName = user.getUserFolder();

    		String userFolder = context.getRealPath("/") + CommonData.USER_FILE_FOLDER
				+ File.separator + userFolderName;
    		Banner banner = retrieveBannerById(getBannerId());
    		String fileName = KeyGenerator.generateKey();
    		
			fileInputStream = new FileInputStream(new File("C:\\downloadfile.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return SUCCESS;
	}
    
    private Banner retrieveBannerById(String bannerId) {
    	int bId = Integer.valueOf(bannerId);
    	for (Banner b : getBanners()) {
    		if (b.getId() == bId) {
    			return b;
    		}
    	}
    	
    	return null;
    }
    
    /**
     * Action for saving a new banner on the cloud.
     * 
     * @return action result
     */
    public String saveBannerAction() {
        System.out.println("saveBannerAction()"); // XXX remove me
        System.out.println("token = " + getToken());

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
        	System.out.println("Exception in Save BannerAction!!!"); // XXX remove me
        	System.out.println(e.getMessage());

            logger.error(e.getMessage(), e);

            return ERROR;
        }
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
    	
//    	if (getBannerId() != null && !"".equals(getBannerId())) {
//    		// An existing banner was edited
//    		// TODO ...
//    		return SUCCESS;
//    	}

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
    	System.out.println("BannerAction.getBannersFromSession()");
    	Collection<String> dataIds = 
     		(Collection<String>) session.get(CommonData.DATA_ID);
    	List<TempBanner> ret = null; 

    	if (dataIds != null && !dataIds.isEmpty()) {
    		ret = new ArrayList<TempBanner>();
     		for (String tempDataId : dataIds) {
     			ret.add(bannerService.getTempData(tempDataId));
     		}

     		System.out.println("ret.size" + ret.size());

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

	public List<Banner> getBanners() {
		return banners;
	}

	public void setBanners(List<Banner> banners) {
		this.banners = banners;
	}
	
    public String getTotalBanners() {
		return totalBanners;
	}

	public void setTotalBanners(String totalBanners) {
		this.totalBanners = totalBanners;
	}

	public String getUsedTraffic() {
		return usedTraffic;
	}

	public void setUsedTraffic(String usedTraffic) {
		this.usedTraffic = usedTraffic;
	}

	public String getTotalTraffic() {
		return totalTraffic;
	}

	public void setTotalTraffic(String totalTraffic) {
		this.totalTraffic = totalTraffic;
	}

	public String getMembershipPlan() {
		return membershipPlan;
	}

	public void setMembershipPlan(String membershipPlan) {
		this.membershipPlan = membershipPlan;
	}

	public void setServletContext(ServletContext context) {
		this.context  = context;
	}

}
