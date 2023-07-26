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
		// 加载驱动

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("驱动加载成功");

			// 连接数据库,获得连接对象
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/message?useSSL=false&serverTimezone=UTC", "root", "baeyeon159357");
			System.out.println("数据库连接成功");

			// 创建执行环境
			Statement statement = conn.createStatement();
			// 执行sql语句，得到结果集
			/*
			 * ResultSet result = statement.executeQuery("select*from 用户基本信息");
			 * while(result.next()){ System.out.println(result.getString("用户名"));
			 * System.out.println(result.getString("性别"));
			 * System.out.println(result.getString("密码"));
			 * System.out.println(result.getString("联系电话"));
			 * System.out.println(result.getString("账号")); }
			 * 
			 * 
			 */
			ResultSet rs = conn.getMetaData().getTables(null, null, ownerId, null);

			if (rs.next()) {

				int i = 1;
				String ren = "SELECT DISTINCT 发送人 FROM " + ownerId;
				re = statement.executeQuery(ren);

				while (re.next()) {
					duifangid = re.getString("发送人");
					System.out.println(duifangid);
				}

				// while(re.next()){
				// i++;

				String mess = "SELECT 消息内容 FROM " + ownerId;

				res = statement.executeQuery(mess);
				/*
				 * zanshi while(res.next()){ System.out.println(res.getString("消息内容")); } return
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
				 * String a = "CREATE TABLE "+ gettername+"(发送人 char(100),消息内容 char(100))";
				 * System.out.println(a); statement.executeUpdate(a); String sql4
				 * ="INSERT INTO "+gettername+" VALUES('"+m.getSender()+"','"+m.getCon()+"')";
				 * // String sql4 ="INSERT INTO "+gettername+"(消息内容)"+" VALUES("+m.getCon()+")";
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

			// String mess = "SELECT 消息内容 FROM "+ownerId;

			// ResultSet res = statement.executeQuery(mess);
			while (res.next()) {
				String xinxi = res.getString("消息内容");
				String mimi = xinxi + "\n\r";

				chat022.jta.append(mimi);
				// System.out.println(res.getString("消息内容"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("驱动加载失败");
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
