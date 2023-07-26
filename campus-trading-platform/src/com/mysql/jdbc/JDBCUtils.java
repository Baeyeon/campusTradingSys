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
		InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("trade.properties");// ctrl+1�����Զ����ض���
		// ����Properties���͵Ķ���
		Properties p = new Properties();
		// �������ļ�
		try {
			p.load(inputStream);
			driver = p.getProperty("driver");
			url = p.getProperty("url");
			username = p.getProperty("username");
			password = p.getProperty("password");
			// ����MySQL����
			Class.forName(driver);
			System.out.println("�������سɹ���");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ������Ӷ���ķ���
	public static Connection getConnection() {
		try {
			System.out.println("���ݿ����ӳɹ�!");
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// �ͷ���Դ�ķ���:�ȴ�����ر�
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
