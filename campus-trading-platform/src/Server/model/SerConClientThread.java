package Server.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.common.Message;

/*
功能：是服务器和某个客户端的通信线程
 */
public class SerConClientThread extends Thread {

	Socket s;

	public SerConClientThread(Socket s) {
		// 把服务器与该客户端的连接赋给s
		this.s = s;
	}

	public void run() {

		while (true) {
			// 这里该线程就可以接收客户端的信息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();
				System.out.println("..................................................");

				System.out.println(m.getSendTime() + " " + m.getSender() + "给" + m.getGetter() + "说：" + m.getCon());

				System.out.println(m.getGetter());
				String get = m.getGetter();
				// 一会完成转发任务
				/*
				 * SerConClientThread scct = new SerConClientThread(s);
				 * 
				 * System.out.println("*-*-*-*。。。。。。。。。。。。"); //自行改为下面的一行
				 * ManageClientThread.addClientThread(u.getUserId(),scct);
				 * 
				 * ManageClientThread.addClientThread(m.getSender(),scct);
				 * 
				 * System.out.println("*-*-*-*、、、、、、、、、、、、、、、、、、"); //启动与该客户端通讯的线程 scct.start();
				 * System.out.println(scct);
				 * 
				 */

				// 取得接收人的通讯线程

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
					// 将信息存到数据库
					JDBCS.JDBCS(m.getGetter(), m.getSender(), m);
					System.out.println("已将消息存到数据库");
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
