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
						+ m.getSender() + "给" + m.getGetter() + "说:" + m.getCon() + "')";
				// String sql2 ="INSERT INTO "+gettername+"
				// VALUES("+m.getSender()+","+m.getCon()+")";

				// String sql2 ="INSERT INTO "+gettername+"
				// VALUES("+sendername+","+m.getSender()+"给"+m.getGetter()+"说:"+m.getCon()+")";
				System.out.println(sql2);

				statement.executeUpdate(sql2);
			} else {

				String a = "CREATE TABLE " + gettername + "(发送人 char(100),消息内容 char(100))";
				System.out.println(a);
				statement.executeUpdate(a);
				// String sql4 ="INSERT INTO "+gettername+"
				// VALUES('"+m.getSender()+"','"+m.getCon()+"')";
				String sql4 = "INSERT INTO " + gettername + " VALUES('" + m.getSender() + "','" + m.getSendTime()
						+ m.getSender() + "给" + m.getGetter() + "说:" + m.getCon() + "')";
				// String sql4 ="INSERT INTO "+gettername+"(消息内容)"+" VALUES("+m.getCon()+")";
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
			System.out.println("驱动加载失败");
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