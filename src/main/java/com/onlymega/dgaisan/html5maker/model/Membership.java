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
	
	public Membership() {}
	
	public Membership(int id, String name, String desc, double price) {
		this.id = id;
		this.name = name;
		this.description = desc;
		this.price = price;
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
	
	
}
