package client.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCC {
	public static ResultSet re;
	public static ResultSet res;

	public static String getDuifangid() {
		return duifangid;
	}

	public static void setDuifangid(String duifangid) {
		JDBCC.duifangid = duifangid;
	}

	public static String duifangid;

	public static void main(String[] args) {

	}

	public static ResultSet JDBCS(String ownerId) {
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
			ResultSet rs = conn.getMetaData().getTables(null, null, ownerId, null);

			if (rs.next()) {

				int i = 1;
				String ren = "SELECT DISTINCT ������ FROM " + ownerId;
				re = statement.executeQuery(ren);

				while (re.next()) {
					duifangid = re.getString("������");
					System.out.println(duifangid);
				}

				// while(re.next()){
				// i++;

				String mess = "SELECT ��Ϣ���� FROM " + ownerId;

				res = statement.executeQuery(mess);
				/*
				 * zanshi while(res.next()){ System.out.println(res.getString("��Ϣ����")); } return
				 * res;
				 * 
				 */

				// System.out.println(res);
				// }
				/*
				 * for(int j=1;j<=i;j++){ String haoyou = }
				 * 
				 */

				// String xin = "SELECT*FROM "+ownerId;
				// statement.executeQuery(xin);

			} else {
				/*
				 * String a = "CREATE TABLE "+ gettername+"(������ char(100),��Ϣ���� char(100))";
				 * System.out.println(a); statement.executeUpdate(a); String sql4
				 * ="INSERT INTO "+gettername+" VALUES('"+m.getSender()+"','"+m.getCon()+"')";
				 * // String sql4 ="INSERT INTO "+gettername+"(��Ϣ����)"+" VALUES("+m.getCon()+")";
				 * System.out.println(sql4);
				 * 
				 * // String sql3
				 * ="insert into "+gettername+" values("+sendername+","+m.getCon()+")"; //
				 * String sql1 ="INSERT INTO "+
				 * gettername+"("+sendername+") VALUES("+m.getCon()+")";
				 * statement.executeUpdate(sql4);
				 * 
				 */
			}

			/*
			 * ResultSet re = statement.executeQuery("select*from "+ gettername);
			 * while(re.next()){ System.out.println(re.getString(sendername)); }
			 * 
			 * 
			 */
			chat022 ccc = new chat022(ownerId, duifangid);

			// String mess = "SELECT ��Ϣ���� FROM "+ownerId;

			// ResultSet res = statement.executeQuery(mess);
			while (res.next()) {
				String xinxi = res.getString("��Ϣ����");
				String mimi = xinxi + "\n\r";

				chat022.jta.append(mimi);
				// System.out.println(res.getString("��Ϣ����"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��������ʧ��");
		}

		return null;// ???
	}

	/*
	 * public boolean validateTableNameExist(String tableName) { //Connection con =
	 * getYourCnnection;
	 * 
	 * }
	 * 
	 * 
	 */

	public static String getDuifang(String duifangId) {

		String duifang = duifangId;
		return duifang;
	}

}
