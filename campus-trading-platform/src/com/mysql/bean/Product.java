package com.mysql.bean;

public class Product {
	private String pro_name;
	private String pro_url;
	private String kind;
	private String pro_owner;
	private String pro_price;
	private String pro_intro;
	private String pro_count;
	private String pro_commt;

	public Product(String pro_name, String pro_owner, String pro_commt) {
		super();
		this.pro_name = pro_name;
		this.pro_owner = pro_owner;
		this.pro_commt = pro_commt;
	}

	public Product(String pro_name, String pro_url, String kind, String pro_owner, String pro_price, String pro_intro,
			String pro_count) {
		super();
		this.pro_name = pro_name;
		this.pro_url = pro_url;
		this.kind = kind;
		this.pro_owner = pro_owner;
		this.pro_price = pro_price;
		this.pro_intro = pro_intro;
		this.pro_count = pro_count;
	}

	public String getPro_commt() {
		return pro_commt;
	}

	public String getPro_count() {
		return pro_count;
	}

	public String getPro_name() {
		return pro_name;
	}

	public String getPro_url() {
		return pro_url;
	}

	public String getKind() {
		return kind;
	}

	public String getpro_owner() {
		return pro_owner;
	}

	public String getPro_price() {
		return pro_price;
	}

	public String getPro_intro() {
		return pro_intro;
	}
}
