package com.mysql.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCU {
	public static String xingming;

	public static String JDBCU(String ownerId) {
		// ��������

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("�������سɹ�");

			// �������ݿ�,������Ӷ���
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/trade?useSSL=false&serverTimezone=UTC", "root", "baeyeon159357");
			System.out.println("���ݿ����ӳɹ�");

			// ����ִ�л���
			Statement statement = conn.createStatement();
			// ִ��sql��䣬�õ������

			ResultSet result = statement.executeQuery("SELECT �û��� FROM �û�������Ϣ WHERE �˺�=" + ownerId);
			while (result.next()) {
				xingming = result.getString("�û���");
				System.out.println("�����ݿ��õ��û���Ϊ" + xingming);
			}

			/*
			 * while(result.next()){ System.out.println(result.getString("�û���"));
			 * System.out.println(result.getString("�Ա�"));
			 * System.out.println(result.getString("����"));
			 * System.out.println(result.getString("��ϵ�绰"));
			 * System.out.println(result.getString("�˺�")); }
			 * 
			 * 
			 */

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��������ʧ��");
		}
		return xingming;
	}

}
