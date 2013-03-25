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
import com.onlymega.dgaisan.html5maker.dao.TempDataDao;
import com.onlymega.dgaisan.html5maker.dao.UserDao;
import com.onlymega.dgaisan.html5maker.model.Banner;
import com.onlymega.dgaisan.html5maker.model.CloudData;
import com.onlymega.dgaisan.html5maker.model.Membership;
import com.onlymega.dgaisan.html5maker.model.RegistrationConfirmation;
import com.onlymega.dgaisan.html5maker.model.TempData;
import com.onlymega.dgaisan.html5maker.model.User;
import com.onlymega.dgaisan.html5maker.service.BannerService;
import com.onlymega.dgaisan.html5maker.service.exception.BannerServiceException;
import com.onlymega.dgaisan.html5maker.utils.KeyGenerator;
import com.onlymega.dgaisan.html5maker.utils.ZipPackage;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
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
   
    private TempDataDao tempDataDao;
    private BannerDao bannerDao;
    private CloudDao cloudDao;
    private MembershipDao membershipDao;
    private UserDao userDao;

    @Transactional
    public String saveTempData(TempData data, String tempDir)throws Exception {
    	System.out.println("BasicBannerService.saveTempData()");
        getTempDataDao().saveData(data);
        new ZipPackage(data, tempDir, data.getDataToken()).create();
        
        return data.getDataToken();
    }

    public String saveImage(InputStream stream) throws IOException {
        File dir = null;
        File file = null;
        FileOutputStream fios = null;
        String newFileName = "";
        String fullPath = "";
        int bytesRead = 0;
        byte[] buffer = null;

        int tempFileLen = 0;

        newFileName = KeyGenerator.generateNameHash() + ".png";

        //fullPath = servletContext.getRealPath("/") + CommonData.TEMP_FOLDER + File.separator + newFileName;
        //String dirFileName = servletContext.getRealPath("/") + CommonData.TEMP_FOLDER;

        String dirFileName = "";

        dir = new File(dirFileName);
        file = new File(dirFileName, newFileName);
        System.out.println("fullPath: " + fullPath);

        if (!dir.exists()) {
                dir.mkdir();
        }
        if (!file.exists()) {
                file.createNewFile();
        }

        fios = new FileOutputStream(file);
        buffer = new byte[ CommonData.BUFFER_SIZE];

        // TODO validate request!

        while ((bytesRead = stream.read(buffer)) > 0) {
                fios.write(buffer, 0, bytesRead);
                tempFileLen += bytesRead;
        }
        fios.flush();
        fios.close();

        return newFileName;
    }

    @Transactional
    public int saveBanner(Banner b, CloudData c) throws HibernateException, AmazonServiceException, BannerServiceException {
        b.setDateCreated(new Date());

        try {
        	bannerDao.save(b);
        	cloudDao.save(c.getBucketName(), c.getPath(), c.getFilename(), c.getInputFile());
        } catch (Exception e) {
        	e.printStackTrace(); // XXX remove me
			log.error(String.format("Couln't save the banner (%s, %s) for user with ID: ", 
					String.valueOf(b.getId()), b.getUser().getUserId()));
			throw new BannerServiceException();
		}
        
        return b.getId();
    }

    public TempData getTempData(String tempDataId) {
		return tempDataDao.getDataById(Long.valueOf(tempDataId));
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

    public void setTempDataDao(TempDataDao tempDataDao) {
        this.tempDataDao = tempDataDao;
    }

    public TempDataDao getTempDataDao() {
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

}
