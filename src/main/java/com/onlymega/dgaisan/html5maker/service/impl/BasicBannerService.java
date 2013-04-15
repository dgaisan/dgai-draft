package com.onlymega.dgaisan.html5maker.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import com.amazonaws.AmazonServiceException;
import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.dao.BannerDao;
import com.onlymega.dgaisan.html5maker.dao.CloudDao;
import com.onlymega.dgaisan.html5maker.dao.MembershipDao;
import com.onlymega.dgaisan.html5maker.dao.TempBannerDao;
import com.onlymega.dgaisan.html5maker.dao.UserDao;
import com.onlymega.dgaisan.html5maker.model.Banner;
import com.onlymega.dgaisan.html5maker.model.CloudData;
import com.onlymega.dgaisan.html5maker.model.Membership;
import com.onlymega.dgaisan.html5maker.model.RegistrationConfirmation;
import com.onlymega.dgaisan.html5maker.model.TempBanner;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.service.BannerService;
import com.onlymega.dgaisan.html5maker.service.exception.BannerServiceException;
import com.onlymega.dgaisan.html5maker.utils.KeyGenerator;
import com.onlymega.dgaisan.html5maker.utils.ZipPackage;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletInputStream;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.mapping.Collection;
import org.springframework.transaction.annotation.Transactional;

/**
 * An implementation of {@link BannerService} for public(not authenticated)
 * users. It saves all data in temporary folders on the server.
 * 
 * @author Dmitri Gaisan
 *
 */
public class BasicBannerService implements BannerService, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(BasicBannerService.class.getName());
   
    private TempBannerDao tempDataDao;
    private BannerDao bannerDao;
    private CloudDao cloudDao;
    private MembershipDao membershipDao;
    private UserDao userDao;

    @Transactional
    public String saveTempData(TempBanner data, String tempDir)throws Exception {
    	System.out.println("BasicBannerService.saveTempData()"); /// XXX remove me
        getTempDataDao().saveTempBanner(data);
        new ZipPackage(data, tempDir, data.getDataToken()).create();
        
        return data.getDataToken();
    }

    public void saveImage(ServletInputStream stream, String folderToSave, String fileName) throws IOException, Exception {
    	System.out.println("BasicBannerService.saveImage()"); // XXX remove me
    	int tempFileLen = 0;
		int bytesRead = 0;
		File file = new File(folderToSave, fileName);

		if (!file.exists()) {
			file.createNewFile();
		} else {
			// an unlikely scenario
			throw new Exception("File with the save name already exists!");
		}

		FileOutputStream fios = new FileOutputStream(file);
		byte[] buffer = new byte[ CommonData.BUFFER_SIZE];

		// TODO validate request!

		while ((bytesRead = stream.read(buffer)) > 0) {
			fios.write(buffer, 0, bytesRead);
			tempFileLen += bytesRead;
		}
		fios.flush();
		fios.close();
    }

    @Transactional
    public int saveBanner(Banner b) throws Exception {
    	System.out.println("BasicBannerService.saveBanner()"); // XXX remove me
        b.setDateCreated(new Date());
        bannerDao.save(b);
        
        return b.getId();
    }

    public TempBanner getTempData(String tempDataId) {
		return tempDataDao.getTempBannerById(Integer.valueOf(tempDataId));
	}

	public boolean isPremiumAccount(User user) throws Exception {
		List<Membership> availableMemberships = membershipDao.getAvailableMemberships();

		for (Membership m : availableMemberships) {
			if (m.getId() == user.getMembershipType() 
					&& m.getName().equals(CommonData.FREE_MEMBERSHIP)) {
				return false;
			}
		}
		return true;
	}
    
	public int countBanners(User user) throws Exception {
		System.out.println("BasicBannerService.countBanners()"); //XXX remove me

		int ret = bannerDao
			.countBannersByUser(String.valueOf(user.getUserId()));

		System.out.println("count = " + ret); // XXX remove me

		return ret;
	}

	public String getSignInToken(User user) {
		RegistrationConfirmation reg = membershipDao.getSignInTokenByUser(user);

		if (reg != null) {
			return reg.getConfirmationCode();
		}

		return "";
	}
	
    /**
     * @return the bannerDao
     */
    public BannerDao getBannerDao() {
        return bannerDao;
    }

    /**
     * @param bannerDao the bannerDao to set
     */
    public void setBannerDao(BannerDao bannerDao) {
        this.bannerDao = bannerDao;
    }

    /**
     * @return the cloudDao
     */
    public CloudDao getCloudDao() {
        return cloudDao;
    }

    /**
     * @param cloudDao the cloudDao to set
     */
    public void setCloudDao(CloudDao cloudDao) {
        this.cloudDao = cloudDao;
    }

    public void setTempDataDao(TempBannerDao tempDataDao) {
        this.tempDataDao = tempDataDao;
    }

    public TempBannerDao getTempDataDao() {
        return tempDataDao;
    }

	public MembershipDao getMembershipDao() {
		return membershipDao;
	}

	public void setMembershipDao(MembershipDao membershipDao) {
		this.membershipDao = membershipDao;
	}

	public String getUserFolder(String userId) {
		if (userId == null || userId.isEmpty()) {
			return null;
		}
		User u = userDao.getUser(Integer.valueOf(userId));

		return u.getUserFolder();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getUserById(String id) {
		try {
			return getUserDao().getUser(Integer.valueOf(id));
		} catch (Exception e) {
			log.error("Problem retrieving user by id");
			return null;
		}
	}

	public Banner getBannerById(String bannerId) {
		return getBannerDao().getBannerById(bannerId);
	}

	public List<Banner> getBannersByUser(User user) {
		System.out.println("BasicBannerService.getBannersByUser()"); // XXX remove me
		List<Banner> ret = null;

		try {
			ret = getBannerDao().getBannersByUser(user);
			if (ret == null) {
				ret = Collections.emptyList();
			}
		} catch (Exception e) {
			e.printStackTrace(); // XXX remove me
			log.error(e);
		}

		return ret; 
	}

	public Membership getMembershipById(int id) {
		try {
			List<Membership> memberships = 
				getMembershipDao().getAvailableMemberships();

			for (Membership m : memberships) {
				if (m.getId() == id) {
					return m;
				}
			}
		} catch (Exception e) {
			log.error("Can't retrieve Memberships");
			return null;
		}

		return null;
	}
}
