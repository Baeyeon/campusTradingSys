package com.mysql.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com.mysql.jdbc.JDBCUtils;

public class JDBCUtilsTest {
	@Test
	public void jdbcConnectionTest() throws Exception {
		Connection conn = JDBCUtils.getConnection();
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery("select * from 用户基本信息");
		while (result.next()) {
			System.out.println(result.getLong("联系电话"));
		}
	}
}
