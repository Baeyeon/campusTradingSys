package client.common.tools;

import java.util.HashMap;

//����һ������ͻ��������������ͨѶ���߳���
public class ManageClientConServerThread {

	static HashMap hm = new HashMap<String, ClientConServerThread>();

	// �Ѵ����õ�ͨѶ�̣߳�ClientConServerThread)���뵽hm
	public static void addClientConServerThread(String uId, ClientConServerThread ccst) {
		System.out.println("add");
		hm.put(uId, ccst);
		System.out.println(hm);
	}

	// ����ͨ��uidȡ�ø��߳�
	public static ClientConServerThread getClientConServerThread(String uId) {
		System.out.println("��ȡduifang�߳�");
		System.out.println((ClientConServerThread) hm.get(uId));
		return (ClientConServerThread) hm.get(uId);
	}
}