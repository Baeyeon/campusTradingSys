package com.mysql.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCU {
	public static String xingming;

	public static String JDBCU(String ownerId) {
		// 加载驱动

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("驱动加载成功");

			// 连接数据库,获得连接对象
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/trade?useSSL=false&serverTimezone=UTC", "root", "baeyeon159357");
			System.out.println("数据库连接成功");

			// 创建执行环境
			Statement statement = conn.createStatement();
			// 执行sql语句，得到结果集

			ResultSet result = statement.executeQuery("SELECT 用户名 FROM 用户基本信息 WHERE 账号=" + ownerId);
			while (result.next()) {
				xingming = result.getString("用户名");
				System.out.println("从数据库获得的用户名为" + xingming);
			}

			/*
			 * while(result.next()){ System.out.println(result.getString("用户名"));
			 * System.out.println(result.getString("性别"));
			 * System.out.println(result.getString("密码"));
			 * System.out.println(result.getString("联系电话"));
			 * System.out.println(result.getString("账号")); }
			 * 
			 * 
			 */

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("驱动加载失败");
		}
		return xingming;
	}

}
