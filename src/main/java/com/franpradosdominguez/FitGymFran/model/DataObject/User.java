package com.franpradosdominguez.FitGymFran.model.DataObject;

import com.franpradosdominguez.FitGymFran.model.DAO.UserDAO;

public class User extends UserDAO {

	private String nickname;
	private String password;
	
	public User() {
		this("", "");
	}

	public User(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [nickname=" + nickname + ", password=" + password + "]";
	}

	
}
