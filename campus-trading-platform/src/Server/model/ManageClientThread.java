package Server.model;

import java.util.HashMap;

/*
����û����߲�����
 */
public class ManageClientThread {

	public static HashMap hm = new HashMap<String, SerConClientThread>();

	// ��hm�����һ���ͻ���ͨѶ�߳�
	public static void addClientThread(String uid, SerConClientThread ct) {
		hm.put(uid, ct);
		System.out.println("��ӳɹ�");
		System.out.println();
	}

	public static SerConClientThread getClientThread(String uid) {
		System.out.println("��ȡ�߳�");
		// System.out.println((ClientConServerThread)hm.get(uid));
		SerConClientThread ser = (SerConClientThread) hm.get(uid);
		System.out.println(ser);
		return ser;
	}
}
