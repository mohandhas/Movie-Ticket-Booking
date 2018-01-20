package com.mtr.pojo;

public class Addons {
	
	private String itemName;
	private double cost;
	
	public Addons(String itemName, double cost) {
		super();
		this.itemName = itemName;
		this.cost = cost;
	}
	public Addons() {
		super();
	}
	@Override
	public String toString() {
		return "Addons [itemName=" + itemName + ", cost=" + cost + "]";
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
}
