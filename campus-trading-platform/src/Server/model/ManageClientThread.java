package Server.model;

import java.util.HashMap;

/*
完成用户在线不在线
 */
public class ManageClientThread {

	public static HashMap hm = new HashMap<String, SerConClientThread>();

	// 向hm中添加一个客户端通讯线程
	public static void addClientThread(String uid, SerConClientThread ct) {
		hm.put(uid, ct);
		System.out.println("添加成功");
		System.out.println();
	}

	public static SerConClientThread getClientThread(String uid) {
		System.out.println("获取线程");
		// System.out.println((ClientConServerThread)hm.get(uid));
		SerConClientThread ser = (SerConClientThread) hm.get(uid);
		System.out.println(ser);
		return ser;
	}
}
