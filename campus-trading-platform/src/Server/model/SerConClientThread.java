package Server.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.common.Message;

/*
���ܣ��Ƿ�������ĳ���ͻ��˵�ͨ���߳�
 */
public class SerConClientThread extends Thread {

	Socket s;

	public SerConClientThread(Socket s) {
		// �ѷ�������ÿͻ��˵����Ӹ���s
		this.s = s;
	}

	public void run() {

		while (true) {
			// ������߳̾Ϳ��Խ��տͻ��˵���Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();
				System.out.println("..................................................");

				System.out.println(m.getSendTime() + " " + m.getSender() + "��" + m.getGetter() + "˵��" + m.getCon());

				System.out.println(m.getGetter());
				String get = m.getGetter();
				// һ�����ת������
				/*
				 * SerConClientThread scct = new SerConClientThread(s);
				 * 
				 * System.out.println("*-*-*-*������������������������"); //���и�Ϊ�����һ��
				 * ManageClientThread.addClientThread(u.getUserId(),scct);
				 * 
				 * ManageClientThread.addClientThread(m.getSender(),scct);
				 * 
				 * System.out.println("*-*-*-*������������������������������������"); //������ÿͻ���ͨѶ���߳� scct.start();
				 * System.out.println(scct);
				 * 
				 */

				// ȡ�ý����˵�ͨѶ�߳�

				// SerConClientThread sc = ManageClientThread.getClientThread(m.getGetter());
				System.out.println("--------------");
				// System.out.println(sc);

				/*
				 * ClientConServerThread sccc =
				 * ManageClientConServerThread.getClientConServerThread(m.getGetter());
				 * System.out.println(sccc);
				 * 
				 * ObjectOutputStream oos = new ObjectOutputStream(sccc.s.getOutputStream());
				 * System.out.println("===================="); oos.writeObject(m);
				 * 
				 * 
				 */

				/*
				 * SerConClientThread scct1 = new SerConClientThread(s);
				 * ManageClientThread.addClientThread(m.getGetter(),scct1); scct1.start();
				 * System.out.println(scct1); System.out.println("1111111");
				 * 
				 * 
				 * 
				 */

				SerConClientThread scc1 = ManageClientThread.getClientThread(m.getSender());
				System.out.println(scc1);
				// ObjectOutputStream oos1 = new ObjectOutputStream(scc1.s.getOutputStream());
				// oos1.writeObject(m);

				SerConClientThread scc = ManageClientThread.getClientThread(m.getGetter());
				if (scc == null) {
					// ����Ϣ�浽���ݿ�
					JDBCS.JDBCS(m.getGetter(), m.getSender(), m);
					System.out.println("�ѽ���Ϣ�浽���ݿ�");
				} else {
					System.out.println(scc);
					ObjectOutputStream oos = new ObjectOutputStream(scc.s.getOutputStream());
					oos.writeObject(m);
				}

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
