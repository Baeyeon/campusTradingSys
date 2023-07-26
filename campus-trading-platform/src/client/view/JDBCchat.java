package client.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCchat {
	public static void main(String[] args) {
	}

	public static String[] JDBCchat() {
		// 加载驱动
		String[] name = new String[0];
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
			ResultSet result = statement.executeQuery("select*from 用户基本信息");
			name = new String[50];
			int i = 0;
			while (result.next()) {
				name[i] = result.getString("用户名");
				i++;

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("驱动加载失败");
		}
		return name;
	}

}
