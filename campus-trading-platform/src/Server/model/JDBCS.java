package Server.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import client.common.Message;

public class JDBCS {
	public static void main(String[] args) {

	}

	public static void JDBCS(String gettername, String sendername, Message m) {
		// ��������

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("�������سɹ�");

			// �������ݿ�,������Ӷ���
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/message?useSSL=false&serverTimezone=UTC", "root", "baeyeon159357");
			System.out.println("���ݿ����ӳɹ�");

			// ����ִ�л���
			Statement statement = conn.createStatement();
			// ִ��sql��䣬�õ������
			/*
			 * ResultSet result = statement.executeQuery("select*from �û�������Ϣ");
			 * while(result.next()){ System.out.println(result.getString("�û���"));
			 * System.out.println(result.getString("�Ա�"));
			 * System.out.println(result.getString("����"));
			 * System.out.println(result.getString("��ϵ�绰"));
			 * System.out.println(result.getString("�˺�")); }
			 * 
			 * 
			 */
			ResultSet rs = conn.getMetaData().getTables(null, null, gettername, null);

			if (rs.next()) {
				/*
				 * ResultSet res = statement.executeQuery(gettername); while(res.next()){ String
				 * sss = res.getString(sendername); if(sss==null){
				 * 
				 * } }
				 * 
				 */

				String sql2 = "INSERT INTO " + gettername + " VALUES('" + m.getSender() + "','" + m.getSendTime()
						+ m.getSender() + "��" + m.getGetter() + "˵:" + m.getCon() + "')";
				// String sql2 ="INSERT INTO "+gettername+"
				// VALUES("+m.getSender()+","+m.getCon()+")";

				// String sql2 ="INSERT INTO "+gettername+"
				// VALUES("+sendername+","+m.getSender()+"��"+m.getGetter()+"˵:"+m.getCon()+")";
				System.out.println(sql2);

				statement.executeUpdate(sql2);
			} else {

				String a = "CREATE TABLE " + gettername + "(������ char(100),��Ϣ���� char(100))";
				System.out.println(a);
				statement.executeUpdate(a);
				// String sql4 ="INSERT INTO "+gettername+"
				// VALUES('"+m.getSender()+"','"+m.getCon()+"')";
				String sql4 = "INSERT INTO " + gettername + " VALUES('" + m.getSender() + "','" + m.getSendTime()
						+ m.getSender() + "��" + m.getGetter() + "˵:" + m.getCon() + "')";
				// String sql4 ="INSERT INTO "+gettername+"(��Ϣ����)"+" VALUES("+m.getCon()+")";
				System.out.println(sql4);

				// String sql3 ="insert into "+gettername+"
				// values("+sendername+","+m.getCon()+")";
				// String sql1 ="INSERT INTO "+ gettername+"("+sendername+")
				// VALUES("+m.getCon()+")";
				statement.executeUpdate(sql4);
			}

			/*
			 * ResultSet re = statement.executeQuery("select*from "+ gettername);
			 * while(re.next()){ System.out.println(re.getString(sendername)); }
			 * 
			 * 
			 */

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��������ʧ��");
		}
	}
	/*
	 * public boolean validateTableNameExist(String tableName) { //Connection con =
	 * getYourCnnection;
	 * 
	 * }
	 * 
	 * 
	 */
}