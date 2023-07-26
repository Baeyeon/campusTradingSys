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
	 * �����������ݿⷽ��ʵ��
	 */
	// ��¼����
	@Override
	public int login(User user) {
		// �������ݿ⣬�������Ӷ���conn
		Connection conn = JDBCUtils.getConnection();
		ResultSet result = null;
		PreparedStatement prepareStatement = null;
		int value = -1;
		try {
			String SQL_USER_LOGIN = "SELECT * FROM �û�������Ϣ WHERE �˺�=? AND ����=?";
			// ����ִ�л���statement,����Ԥ���뻷������Ϊ���������û��Լ����룬�����贴��Ԥ���뻷����
			prepareStatement = conn.prepareStatement(SQL_USER_LOGIN);
			// ִ��sql���QUERY���õ��������result
			prepareStatement.setString(1, user.getAccount());
			prepareStatement.setString(2, new String(user.getPassword()));
			// ִ�����
			result = prepareStatement.executeQuery();
			while (result.next()) {
				// �ý������ѯ�Ƿ���ڵ�ǰ�û�
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

	// ע�᷽��
	private static final String SQL_USER_REGISTER = "INSERT INTO �û�������Ϣ VALUES(?,?,?,?,?)";

	public boolean register(User user) {
		try {
			// �������ݿ�����
			Connection conn = JDBCUtils.getConnection();
			// ����ִ�л���statement,����Ԥ���뻷��
			PreparedStatement prepareStatement2 = conn.prepareStatement(SQL_USER_REGISTER);
			// ִ��sql���QUERY���õ��������result
			prepareStatement2.setString(1, user.getId());
			prepareStatement2.setString(2, user.getSex());
			prepareStatement2.setString(3, new String(user.getPassword()));
			prepareStatement2.setString(4, user.getTel());
			prepareStatement2.setString(5, user.getAccount());
			int line = prepareStatement2.executeUpdate();// �������Ӱ������
			if (line > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// �ϴ���Ʒ������Ϣ
	private static final String SQL_USER_PRODUCTUPLOAD = "INSERT INTO ��Ʒ������Ϣ (��Ʒ����,��ƷͼƬ·��,��Ʒ����,����,��Ʒ�۸�,��Ʒ���,��Ʒ����) VALUES(?,?,?,?,?,?,?)";

	public boolean upload(Product pro) {
		try {
			// �������ݿ�����
			Connection conn = JDBCUtils.getConnection();
			// ����ִ�л���statement,����Ԥ���뻷��
			PreparedStatement prepareStatement2 = conn.prepareStatement(SQL_USER_PRODUCTUPLOAD);
			// ִ��sql���QUERY���õ��������result
			prepareStatement2.setString(1, pro.getPro_name());
			prepareStatement2.setString(2, pro.getPro_url());
			prepareStatement2.setString(3, pro.getKind());
			prepareStatement2.setString(4, pro.getpro_owner());
			prepareStatement2.setString(5, pro.getPro_price());
			prepareStatement2.setString(6, pro.getPro_intro());
			prepareStatement2.setString(7, pro.getPro_count());
			int line = prepareStatement2.executeUpdate();// �������Ӱ������
			if (line > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// �ϴ���Ʒ����
	public boolean uploadCommt(Product pro) {
		String SQL_USER_COMMENTUPLOAD = "UPDATE ��Ʒ������Ϣ SET ��Ʒ����=" + pro.getPro_commt() + " WHERE ��Ʒ����='"
				+ pro.getPro_name() + "' AND ����='" + pro.getpro_owner() + "'";
		try {
			// �������ݿ�����
			Connection conn = JDBCUtils.getConnection();
			// ����ִ�л���statement,����Ԥ���뻷��
			PreparedStatement prepareStatement2 = conn.prepareStatement(SQL_USER_COMMENTUPLOAD);
			// ִ��sql���QUERY���õ��������result
			int line = prepareStatement2.executeUpdate();// �������Ӱ�������
			if (line > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// ��������
	public String[][] search(String searchInfo) {
		String data[][] = null;
		Connection conn = null;
		Statement statement1 = null;
		Statement statement2 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		try {
			// �������ݿ�����
			conn = JDBCUtils.getConnection();
			String sql = "select * from ��Ʒ������Ϣ where ��Ʒ����='" + searchInfo + "'"; // ������ѯָ��
			String sql2 = "SELECT COUNT(��Ʒ����) AS " + searchInfo + "���� FROM ��Ʒ������Ϣ where ��Ʒ����='" + searchInfo + "'"; // where�ĳ���like����������������
			statement1 = conn.createStatement();
			resultSet = statement1.executeQuery(sql);// ִ������õ��Ľ���ܶ࣬�浽����ļ����У�����ѭ�������
			statement2 = conn.createStatement();
			resultSet2 = statement2.executeQuery(sql2);
			resultSet2.next();
			String numStr = resultSet2.getString(searchInfo + "����");
			num = Integer.parseInt(numStr);
			// 1.����Ŀ��·��
			File file = new File("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\searchNumFile");
			// 2.������ͨ��
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				// 3.�������������
				ObjectOutputStream objOP = new ObjectOutputStream(fos);
				// 4.��Ŀ��·���ļ�д�����
				objOP.writeObject(num);
				// 5.�ر���Դ
				objOP.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			data = new String[num][8];
			for (int i = 0; i < num; i++) {
				resultSet.next();
				data[i][0] = resultSet.getString("��Ʒ����");
				data[i][1] = resultSet.getString("��ƷͼƬ·��");
				data[i][2] = resultSet.getString("��Ʒ����");
				data[i][3] = resultSet.getString("����");
				data[i][4] = resultSet.getString("��Ʒ�۸�");
				data[i][5] = resultSet.getString("��Ʒ���");
				data[i][6] = resultSet.getString("��Ʒ����");
				data[i][7] = resultSet.getString("��Ʒ����");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �ͷ���Դ�����ȹر�ѭ�����ٹرմ��ڣ����ر����ӡ�
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

//������������
	public String[][] search(int num, String searchInfo) {
		String data[][] = null;
		Connection conn = null;
		Statement statement1 = null;
		Statement statement2 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		try {
			// �������ݿ�����
			conn = JDBCUtils.getConnection();
			String sql = "select * from ��Ʒ������Ϣ where ��Ʒ����='" + searchInfo + "'"; // ������ѯָ��
			String sql2 = "SELECT COUNT(��Ʒ����) AS " + searchInfo + "���� FROM ��Ʒ������Ϣ where ��Ʒ����='" + searchInfo + "'"; // where�ĳ���like����������������
			statement1 = conn.createStatement();
			resultSet = statement1.executeQuery(sql);// ִ������õ��Ľ���ܶ࣬�浽����ļ����У�����ѭ�������
			statement2 = conn.createStatement();
			resultSet2 = statement2.executeQuery(sql2);
			resultSet2.next();
			String numStr = resultSet2.getString(searchInfo + "����");
			num = Integer.parseInt(numStr);
			// 1.����Ŀ��·��
			File file = new File("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\kindNumFile");
			// 2.������ͨ��
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				// 3.�������������
				ObjectOutputStream objOP = new ObjectOutputStream(fos);
				// 4.��Ŀ��·���ļ�д�����
				objOP.writeObject(num);
				// 5.�ر���Դ
				objOP.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			data = new String[num][8];
			for (int i = 0; i < num; i++) {
				resultSet.next();
				data[i][0] = resultSet.getString("��Ʒ����");
				data[i][1] = resultSet.getString("��ƷͼƬ·��");
				data[i][2] = resultSet.getString("��Ʒ����");
				data[i][3] = resultSet.getString("����");
				data[i][4] = resultSet.getString("��Ʒ�۸�");
				data[i][5] = resultSet.getString("��Ʒ���");
				data[i][6] = resultSet.getString("��Ʒ����");
				data[i][7] = resultSet.getString("��Ʒ����");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �ͷ���Դ�����ȹر�ѭ�����ٹرմ��ڣ����ر����ӡ�
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
