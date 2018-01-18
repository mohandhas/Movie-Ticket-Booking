package com.mtr.pojo;

public class Admin {
	private String id;
	private String password;
	
	public Admin() {
		super();
	}

	public Admin(String name, String password) {
		super();
		this.id = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", password=" + password + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String userName) {
		this.id = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
