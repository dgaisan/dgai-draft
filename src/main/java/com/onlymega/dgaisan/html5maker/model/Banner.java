package com.onlymega.dgaisan.html5maker.model;

import java.util.Date;

/**
 * Banner model class.
 * 
 * @author Dmitri Gaisan
 */
public class Banner {
    private int id;
    private int active;
    private String name;
    private String zipFile;
    private String folder;
    private String bannerFile;
    private Date dateCreated;
    private User user;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the zipFile
     */
    public String getZipFile() {
        return zipFile;
    }

    /**
     * @param zipFile the zipFile to set
     */
    public void setZipFile(String zipFile) {
        this.zipFile = zipFile;
    }

    /**
     * @return the folder
     */
    public String getFolder() {
        return folder;
    }

    /**
     * @param folder the folder to set
     */
    public void setFolder(String folder) {
        this.folder = folder;
    }

    /**
     * @return the bannerFile
     */
    public String getBannerFile() {
        return bannerFile;
    }

    /**
     * @param bannerFile the bannerFile to set
     */
    public void setBannerFile(String bannerFile) {
        this.bannerFile = bannerFile;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the active
     */
    public int getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(int active) {
        this.active = active;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
