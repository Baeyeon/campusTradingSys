package Server.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.mysql.bean.User;

import client.common.Message;

//import sever.common.User;

//import com.mysql.bean.User;

/*
���Ƿ��������ڼ����ȴ�ĳ���ͻ���������
 */

public class myserver {
	public static void main(String[] args) {
		myserver m = new myserver();
	}

	public myserver() {
		try {
			// ��9999����
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("���Ƿ���������9999����");
			// �������ȴ�����
			while (true) {

				Socket s = ss.accept();
				// ���տͻ��˷�������Ϣ

				/*
				 * BufferedReader br = new BufferedReader(new
				 * InputStreamReader(s.getInputStream()));
				 * 
				 * String info = br.readLine();
				 * 
				 * 
				 */

				System.out.println("tttttttttttttt");

				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

				System.out.println("aaaaaaaaaaaaaaa");

				// Object a = ois.readObject() ;
				// User u = (User)a;
				User u = (User) ois.readObject();
				System.out.println("bbbbbbbbbbb");
				// ��һ���ǲ�������
				System.out.println("���������յ��û�id" + u.getId() + " ����" + u.getPassword());
				// System.out.println("���������յ��û�id"+u.getUserId()+" ����"+u.getPasswd());

				Message m = new Message();
				System.out.println("message�ɹ�����");
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				System.out.println("message�ɹ�����................");
//��ʱ�Ķ�

				System.out.println("�ɹ���½");
				m.setMesType("1");
				oos.writeObject(m);
				System.out.println("*-*-*-*");

				// ����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ
				SerConClientThread scct = new SerConClientThread(s);

				// ClientConServerThread sccct = new ClientConServerThread(s);
				System.out.println("*-*-*-*������������������������");
				// ���и�Ϊ�����һ�� ManageClientThread.addClientThread(u.getUserId(),scct);

				ManageClientThread.addClientThread(u.getId(), scct);

				// ManageClientConServerThread.addClientConServerThread(u.getAccount(),sccct);
				System.out.println("*-*-*-*������������������������������������");
				// ������ÿͻ���ͨѶ���߳�

				// sccct.start();

				scct.start();
				System.out.println(scct);
				// System.out.println(sccct);
				System.out.println("*-*-*-*---------------------------------------------");
				/*
				 * if(u.getPassword().equals("123")){ //����һ���ɹ���½����Ϣ��
				 * 
				 * System.out.println("�ɹ���½"); m.setMesType("1"); oos.writeObject(m);
				 * System.out.println("*-*-*-*");
				 * 
				 * //����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ SerConClientThread scct = new
				 * SerConClientThread(s);
				 * 
				 * //���и�Ϊ�����һ�� ManageClientThread.addClientThread(u.getUserId(),scct);
				 * 
				 * ManageClientThread.addClientThread(u.getAccount(),scct); //������ÿͻ���ͨѶ���߳�
				 * scct.start(); }else{ m.setMesType("2"); oos.writeObject(m); //�ر����� s.close();
				 * }
				 */
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
