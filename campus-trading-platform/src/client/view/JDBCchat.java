package client.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCchat {
	public static void main(String[] args) {
	}

	public static String[] JDBCchat() {
		// ��������
		String[] name = new String[0];
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
			ResultSet result = statement.executeQuery("select*from �û�������Ϣ");
			name = new String[50];
			int i = 0;
			while (result.next()) {
				name[i] = result.getString("�û���");
				i++;

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��������ʧ��");
		}
		return name;
	}

}
