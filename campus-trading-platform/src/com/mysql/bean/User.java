package com.mysql.bean;

import java.io.Serializable;

public class User implements Serializable {
	private String account;
	private String id;
	private String password;
	private String sex;
	private String tel;

	public User(String id) {
		this.id = id;
	}

	public User(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}

	public User(String account, String id, String password, String sex, String tel) {
		super();
		this.account = account;
		this.id = id;
		this.password = password;
		this.sex = sex;
		this.tel = tel;
	}

	public String getAccount() {
		return account;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getSex() {
		return sex;
	}

	public String getTel() {
		return tel;
	}
}
