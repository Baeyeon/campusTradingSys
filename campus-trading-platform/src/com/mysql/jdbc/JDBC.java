package com.mysql.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
	public static void main(String[] args) {
		try {
			// 加载MySQL驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("驱动器加载成功");
			// 连接数据库,获得连接对象 getConnection("具体数据库地址，此处是data数据库","数据库用户名","用户名密码")
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/trade?useSSL=false&serverTimezone=UTC", "root", "baeyeon159357");
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
