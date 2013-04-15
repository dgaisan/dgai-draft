package com.onlymega.dgaisan.html5maker.model;

/**
 * Model describing a membership.
 * 
 * @author Dmitri Gaisan
 *
 */
public class Membership {
	private int id;
	// Unique name
	private String name;
	private String description;
	private double price;
	private int bannersLimit;
	private int totalTraffic;

	private MembershipTypesEnum membershipType;

	public Membership() {}

	public Membership(int id, String name, String desc, double price) {
		this.id = id;
		this.name = name;
		this.description = desc;
		this.price = price;
	}

	public Membership(int id, String name, String desc, double price, 
			int bannersLimit, int totalTraffic) {
		this(id, name, desc, price);
		this.bannersLimit = bannersLimit;
		this.totalTraffic = totalTraffic;
	}
	
	public Membership(String name, String desc, double price) {
		this(0, name, desc, price);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBannersLimit() {
		return bannersLimit;
	}

	public void setBannersLimit(int bannersLimit) {
		this.bannersLimit = bannersLimit;
	}

	public MembershipTypesEnum getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(MembershipTypesEnum membershipType) {
		this.membershipType = membershipType;
	}

	public int getTotalTraffic() {
		return totalTraffic;
	}

	public void setTotalTraffic(int totalTraffic) {
		this.totalTraffic = totalTraffic;
	}	

}
