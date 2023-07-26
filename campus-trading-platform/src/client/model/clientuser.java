package client.model;

import com.mysql.bean.User;

public class clientuser {

	// 验证用户是否合法
	public boolean checkUser(User u) {
		/*
		 * boolean b = false; try{
		 * 
		 * }catch(Exception e){
		 * 
		 * }finally{
		 * 
		 * } return b;
		 * 
		 * 
		 */

		return new myclientconserver().sendLoginInfoToServer(u);
	}

}
