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
这是客户端连接服务器的后台
 */
public class myclientconserver {
	// public static Socket s;

	/*
	 * public myclientconsever(){ }
	 */

	public Socket s;// 刚刚去掉static
	// 发送第一次请求

	public boolean sendLoginInfoToServer(Object o) {
		boolean b = false;
		try {
			s = new Socket("localhost", 9999);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);

			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

			Message ms = (Message) ois.readObject();

			if (ms.getMesType().equals("1")) {
				// 就创建一个该账号与服务器连接起来的线程
				ClientConServerThread ccst = new ClientConServerThread(s);
				System.out.println("线程创建成功");
				// 启动该通讯线程
				ccst.start();
				ManageClientConServerThread.addClientConServerThread(((User) o).getId(), ccst);

				System.out.println(ccst);

				b = true;
			} // 1表明登录成功

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

		}
		return b;
	}

	public void SendInfoToSever(Object o) {

	}
	/*
	 * 自查 private void readObject(ObjectInputStream in) throws
	 * IOException,ClassNotFoundException{ in.defaultReadObject(); //
	 * 使定制的readObject()方法可以利用自动序列化中内置的逻辑。 int ival = in.readInt(); //
	 * 若要读取“int类型的值”，则使用readInt() Object obj = in.readObject(); //
	 * 若要读取“Object对象”，则使用readObject() }
	 */
}