package com.mtr.pojo;

public class User {

	public String id;
	public String name;
	public String email;
	public String phone;
	public String password;
	public String favoriteGenere;
	
	
	public User() {
		super();
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password=" + password
				+ ", favoriteGenere=" + favoriteGenere + "]";
	}

	public User(String id, String name, String email, String phone, String password, String favoriteGenere) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.favoriteGenere = favoriteGenere;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFavoriteGenere() {
		return favoriteGenere;
	}

	public void setFavoriteGenere(String favoriteGenere) {
		this.favoriteGenere = favoriteGenere;
	}
}
