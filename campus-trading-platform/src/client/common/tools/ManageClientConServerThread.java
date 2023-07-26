package client.common.tools;

import java.util.HashMap;

//这是一个管理客户端与服务器保持通讯的线程类
public class ManageClientConServerThread {

	static HashMap hm = new HashMap<String, ClientConServerThread>();

	// 把创建好的通讯线程（ClientConServerThread)放入到hm
	public static void addClientConServerThread(String uId, ClientConServerThread ccst) {
		System.out.println("add");
		hm.put(uId, ccst);
		System.out.println(hm);
	}

	// 可以通过uid取得该线程
	public static ClientConServerThread getClientConServerThread(String uId) {
		System.out.println("获取duifang线程");
		System.out.println((ClientConServerThread) hm.get(uId));
		return (ClientConServerThread) hm.get(uId);
	}
}