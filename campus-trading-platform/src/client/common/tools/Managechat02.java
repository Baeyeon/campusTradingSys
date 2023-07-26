package client.common.tools;

import java.util.HashMap;

import client.view.chat022;

//这是一个管理用户聊天界面的类
public class Managechat02 {
	private static HashMap hm = new HashMap<String, chat022>();

	// 加入
	public static void addchat02(String loginIdAnFriendId, chat022 cc) {
		hm.put(loginIdAnFriendId, cc);
	}

	// 取出
	public static chat022 getchat02(String loginIdAnFriendId) {
		return (chat022) hm.get(loginIdAnFriendId);
	}
}