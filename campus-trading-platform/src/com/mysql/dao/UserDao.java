package com.mysql.dao;

import com.mysql.bean.Product;
import com.mysql.bean.User;

public interface UserDao {
	/**
	 * ��֤��¼�ķ���
	 * 
	 * @param userҪ��¼���û� �û���������
	 * @return int���� -1��¼ʧ��;1��¼�ɹ�
	 */
	int login(User user);

	/**
	 * ����ע��ķ���
	 * 
	 * @param userҪ��ӵĶ��󣬰����û������Ա����룬��ϵ�绰
	 * @return ��ӳɹ�����true�����ʧ�ܷ���false
	 */
	boolean register(User user);

	boolean upload(Product pro);

	String[][] search(String searchInfo);

	boolean uploadCommt(Product pro);
}
