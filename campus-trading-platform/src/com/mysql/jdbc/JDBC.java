package com.mysql.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
	public static void main(String[] args) {
		try {
			// ����MySQL����
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("���������سɹ�");
			// �������ݿ�,������Ӷ��� getConnection("�������ݿ��ַ���˴���data���ݿ�","���ݿ��û���","�û�������")
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/trade?useSSL=false&serverTimezone=UTC", "root", "baeyeon159357");
			System.out.println("���ݿ����ӳɹ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
