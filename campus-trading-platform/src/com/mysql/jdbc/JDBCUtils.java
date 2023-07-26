package com.mysql.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	static {
		InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("trade.properties");// ctrl+1键可自动返回对象
		// 创建Properties类型的对象
		Properties p = new Properties();
		// 加载流文件
		try {
			p.load(inputStream);
			driver = p.getProperty("driver");
			url = p.getProperty("url");
			username = p.getProperty("username");
			password = p.getProperty("password");
			// 加载MySQL驱动
			Class.forName(driver);
			System.out.println("驱动加载成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获得连接对象的方法
	public static Connection getConnection() {
		try {
			System.out.println("数据库连接成功!");
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 释放资源的方法:先创建后关闭
	public static void close(Connection conn, Statement statement, ResultSet result) {
		try {
			if (result != null) {
				result.close();
				result = null;
			}
			if (statement != null) {
				statement.close();
				statement = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
