package client.common.tools;

import java.util.HashMap;

import client.view.chat022;

//����һ�������û�����������
public class Managechat02 {
	private static HashMap hm = new HashMap<String, chat022>();

	// ����
	public static void addchat02(String loginIdAnFriendId, chat022 cc) {
		hm.put(loginIdAnFriendId, cc);
	}

	// ȡ��
	public static chat022 getchat02(String loginIdAnFriendId) {
		return (chat022) hm.get(loginIdAnFriendId);
	}
}