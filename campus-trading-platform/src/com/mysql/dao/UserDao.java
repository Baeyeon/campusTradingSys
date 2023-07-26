package com.mysql.dao;

import com.mysql.bean.Product;
import com.mysql.bean.User;

public interface UserDao {
	/**
	 * 验证登录的方法
	 * 
	 * @param user要登录的用户 用户名，密码
	 * @return int类型 -1登录失败;1登录成功
	 */
	int login(User user);

	/**
	 * 用来注册的方法
	 * 
	 * @param user要添加的对象，包括用户名，性别，密码，联系电话
	 * @return 添加成功返回true，添加失败返回false
	 */
	boolean register(User user);

	boolean upload(Product pro);

	String[][] search(String searchInfo);

	boolean uploadCommt(Product pro);
}
