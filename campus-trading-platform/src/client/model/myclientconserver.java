package client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.mysql.bean.User;

import client.common.Message;
import client.common.tools.ClientConServerThread;
import client.common.tools.ManageClientConServerThread;

/*
���ǿͻ������ӷ������ĺ�̨
 */
public class myclientconserver {
	// public static Socket s;

	/*
	 * public myclientconsever(){ }
	 */

	public Socket s;// �ո�ȥ��static
	// ���͵�һ������

	public boolean sendLoginInfoToServer(Object o) {
		boolean b = false;
		try {
			s = new Socket("localhost", 9999);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);

			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

			Message ms = (Message) ois.readObject();

			if (ms.getMesType().equals("1")) {
				// �ʹ���һ�����˺�������������������߳�
				ClientConServerThread ccst = new ClientConServerThread(s);
				System.out.println("�̴߳����ɹ�");
				// ������ͨѶ�߳�
				ccst.start();
				ManageClientConServerThread.addClientConServerThread(((User) o).getId(), ccst);

				System.out.println(ccst);

				b = true;
			} // 1������¼�ɹ�

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

		}
		return b;
	}

	public void SendInfoToSever(Object o) {

	}
	/*
	 * �Բ� private void readObject(ObjectInputStream in) throws
	 * IOException,ClassNotFoundException{ in.defaultReadObject(); //
	 * ʹ���Ƶ�readObject()�������������Զ����л������õ��߼��� int ival = in.readInt(); //
	 * ��Ҫ��ȡ��int���͵�ֵ������ʹ��readInt() Object obj = in.readObject(); //
	 * ��Ҫ��ȡ��Object���󡱣���ʹ��readObject() }
	 */
}