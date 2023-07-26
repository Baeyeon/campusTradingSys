package com.mysql.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.bean.Product;
import com.mysql.bean.User;
import com.mysql.jdbc.JDBCUtils;

public class UserDao_imp implements UserDao {
	public static int num = 0;

	/*
	 * 服务器到数据库方法实现
	 */
	// 登录方法
	@Override
	public int login(User user) {
		// 连接数据库，创建连接对象conn
		Connection conn = JDBCUtils.getConnection();
		ResultSet result = null;
		PreparedStatement prepareStatement = null;
		int value = -1;
		try {
			String SQL_USER_LOGIN = "SELECT * FROM 用户基本信息 WHERE 账号=? AND 密码=?";
			// 创建执行环境statement,创建预编译环境（因为参数是由用户自己传入，所以需创建预编译环境）
			prepareStatement = conn.prepareStatement(SQL_USER_LOGIN);
			// 执行sql语句QUERY，得到结果对象result
			prepareStatement.setString(1, user.getAccount());
			prepareStatement.setString(2, new String(user.getPassword()));
			// 执行语句
			result = prepareStatement.executeQuery();
			while (result.next()) {
				// 用结果集查询是否存在当前用户
				value = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, prepareStatement, result);
		}
		return value;
	}

	// 注册方法
	private static final String SQL_USER_REGISTER = "INSERT INTO 用户基本信息 VALUES(?,?,?,?,?)";

	public boolean register(User user) {
		try {
			// 创建数据库连接
			Connection conn = JDBCUtils.getConnection();
			// 创建执行环境statement,创建预编译环境
			PreparedStatement prepareStatement2 = conn.prepareStatement(SQL_USER_REGISTER);
			// 执行sql语句QUERY，得到结果对象result
			prepareStatement2.setString(1, user.getId());
			prepareStatement2.setString(2, user.getSex());
			prepareStatement2.setString(3, new String(user.getPassword()));
			prepareStatement2.setString(4, user.getTel());
			prepareStatement2.setString(5, user.getAccount());
			int line = prepareStatement2.executeUpdate();// 添加数据影响行数
			if (line > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 上传商品基本信息
	private static final String SQL_USER_PRODUCTUPLOAD = "INSERT INTO 商品基本信息 (商品名称,商品图片路径,商品种类,卖家,商品价格,商品简介,商品数量) VALUES(?,?,?,?,?,?,?)";

	public boolean upload(Product pro) {
		try {
			// 创建数据库连接
			Connection conn = JDBCUtils.getConnection();
			// 创建执行环境statement,创建预编译环境
			PreparedStatement prepareStatement2 = conn.prepareStatement(SQL_USER_PRODUCTUPLOAD);
			// 执行sql语句QUERY，得到结果对象result
			prepareStatement2.setString(1, pro.getPro_name());
			prepareStatement2.setString(2, pro.getPro_url());
			prepareStatement2.setString(3, pro.getKind());
			prepareStatement2.setString(4, pro.getpro_owner());
			prepareStatement2.setString(5, pro.getPro_price());
			prepareStatement2.setString(6, pro.getPro_intro());
			prepareStatement2.setString(7, pro.getPro_count());
			int line = prepareStatement2.executeUpdate();// 添加数据影响行数
			if (line > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 上传商品评论
	public boolean uploadCommt(Product pro) {
		String SQL_USER_COMMENTUPLOAD = "UPDATE 商品基本信息 SET 商品评论=" + pro.getPro_commt() + " WHERE 商品名称='"
				+ pro.getPro_name() + "' AND 卖家='" + pro.getpro_owner() + "'";
		try {
			// 创建数据库连接
			Connection conn = JDBCUtils.getConnection();
			// 创建执行环境statement,创建预编译环境
			PreparedStatement prepareStatement2 = conn.prepareStatement(SQL_USER_COMMENTUPLOAD);
			// 执行sql语句QUERY，得到结果对象result
			int line = prepareStatement2.executeUpdate();// 添加数据影响的行数
			if (line > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 搜索方法
	public String[][] search(String searchInfo) {
		String data[][] = null;
		Connection conn = null;
		Statement statement1 = null;
		Statement statement2 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		try {
			// 创建数据库连接
			conn = JDBCUtils.getConnection();
			String sql = "select * from 商品基本信息 where 商品名称='" + searchInfo + "'"; // 发出查询指令
			String sql2 = "SELECT COUNT(商品名称) AS " + searchInfo + "个数 FROM 商品基本信息 where 商品名称='" + searchInfo + "'"; // where改成了like！！！！！！！！
			statement1 = conn.createStatement();
			resultSet = statement1.executeQuery(sql);// 执行语句后得到的结果很多，存到定义的集合中，再用循环输出。
			statement2 = conn.createStatement();
			resultSet2 = statement2.executeQuery(sql2);
			resultSet2.next();
			String numStr = resultSet2.getString(searchInfo + "个数");
			num = Integer.parseInt(numStr);
			// 1.创建目标路径
			File file = new File("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\searchNumFile");
			// 2.创建流通道
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				// 3.创建对象输出流
				ObjectOutputStream objOP = new ObjectOutputStream(fos);
				// 4.向目标路径文件写入对象
				objOP.writeObject(num);
				// 5.关闭资源
				objOP.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			data = new String[num][8];
			for (int i = 0; i < num; i++) {
				resultSet.next();
				data[i][0] = resultSet.getString("商品名称");
				data[i][1] = resultSet.getString("商品图片路径");
				data[i][2] = resultSet.getString("商品种类");
				data[i][3] = resultSet.getString("卖家");
				data[i][4] = resultSet.getString("商品价格");
				data[i][5] = resultSet.getString("商品简介");
				data[i][6] = resultSet.getString("商品数量");
				data[i][7] = resultSet.getString("商品评论");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 释放资源：首先关闭循环，再关闭窗口，最后关闭连接。
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (resultSet2 != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (statement1 != null) {
					statement1.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (statement2 != null) {
					statement2.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

//种类搜索方法
	public String[][] search(int num, String searchInfo) {
		String data[][] = null;
		Connection conn = null;
		Statement statement1 = null;
		Statement statement2 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		try {
			// 创建数据库连接
			conn = JDBCUtils.getConnection();
			String sql = "select * from 商品基本信息 where 商品种类='" + searchInfo + "'"; // 发出查询指令
			String sql2 = "SELECT COUNT(商品种类) AS " + searchInfo + "个数 FROM 商品基本信息 where 商品种类='" + searchInfo + "'"; // where改成了like！！！！！！！！
			statement1 = conn.createStatement();
			resultSet = statement1.executeQuery(sql);// 执行语句后得到的结果很多，存到定义的集合中，再用循环输出。
			statement2 = conn.createStatement();
			resultSet2 = statement2.executeQuery(sql2);
			resultSet2.next();
			String numStr = resultSet2.getString(searchInfo + "个数");
			num = Integer.parseInt(numStr);
			// 1.创建目标路径
			File file = new File("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\kindNumFile");
			// 2.创建流通道
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				// 3.创建对象输出流
				ObjectOutputStream objOP = new ObjectOutputStream(fos);
				// 4.向目标路径文件写入对象
				objOP.writeObject(num);
				// 5.关闭资源
				objOP.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			data = new String[num][8];
			for (int i = 0; i < num; i++) {
				resultSet.next();
				data[i][0] = resultSet.getString("商品名称");
				data[i][1] = resultSet.getString("商品图片路径");
				data[i][2] = resultSet.getString("商品种类");
				data[i][3] = resultSet.getString("卖家");
				data[i][4] = resultSet.getString("商品价格");
				data[i][5] = resultSet.getString("商品简介");
				data[i][6] = resultSet.getString("商品数量");
				data[i][7] = resultSet.getString("商品评论");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 释放资源：首先关闭循环，再关闭窗口，最后关闭连接。
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (resultSet2 != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (statement1 != null) {
					statement1.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (statement2 != null) {
					statement2.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
}
