package edu.poly.entity;

public class BeanLogin {
	private String userID;
	private String password;
	private boolean remember;
	
	
	public BeanLogin() {
		super();
	}


	public BeanLogin(String userID, String password, boolean remember) {
		super();
		this.userID = userID;
		this.password = password;
		this.remember = remember;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isRemember() {
		return remember;
	}


	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	
}
