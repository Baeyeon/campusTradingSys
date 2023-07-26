package GUI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.mysql.dao.UserDao_imp;

public class HomepageServer {

	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
//		  1.ָ���˿� ʹ��ServerSocket����������
		ServerSocket server = new ServerSocket(9994);
		boolean isRunning = true;
		while (isRunning) {
//		  2.����ʽ�ȴ����� accpet
			Socket client = server.accept();
			System.out.println("һ���ͻ��˽���������");
			new Thread(new Channel(client)).start();
		}
		server.close();
	}

	static class Channel implements Runnable {
		private Socket client;
		// ������
		private DataInputStream dis;
		// �����
		private DataOutputStream dos;

		// ���캯��
		public Channel(Socket client) {
			this.client = client;
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
				release();
			}
		}

		// ��������
		private String receive() {
			String datas = "";
			try {
				datas = dis.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return datas;
		}

		// ��������
		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// �ͷ���Դ
		private void release() {
			try {
				if (null != dis)
					dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (null != dos)
					dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (null != client)
					client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// �߳���
		@Override
		public void run() {

//			  3.�������������������
			String searchInfo = "";
			// ��������1

			String[] userinfo = receive().split("=");
			if (userinfo[0].equals("searchInfo")) {
				searchInfo = userinfo[1];
			}
			/*
			 * ������ݿⷵ����Ϣ
			 */
			UserDao_imp dao = new UserDao_imp();
			String[][] data = dao.search(searchInfo);

			// 1.����Ŀ��·��
			File file = new File("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\searchFile");
			// 2.������ͨ��
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				// 3.�������������
				ObjectOutputStream objOP = new ObjectOutputStream(fos);
				// 4.��Ŀ��·���ļ�д�����
				objOP.writeObject(data);
				// 5.�ر���Դ
				objOP.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			// ��������2
//			String kindInfo = "";
//			String userInfo2 = receive();
//			if (userInfo2.equals("��װ")) {
//				kindInfo = "��װ";
//			} else if (userInfo2.equals("�鼮")) {
//				kindInfo = "�鼮";
//			} else if (userInfo2.equals("����Ʒ")) {
//				kindInfo = "����Ʒ";
//			} else if (userInfo2.equals("�˶�����")) {
//				kindInfo = "�˶�����";
//			}
//			UserDao_imp dao2 = new UserDao_imp();
//			String[][] data2 = dao2.search(1, searchInfo);
//
//			// 1.����Ŀ��·��
//			File file1 = new File("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\kindSearchFile");
//			// 2.������ͨ��
//			FileOutputStream fos1 = null;
//			try {
//				fos1 = new FileOutputStream(file1);
//			} catch (FileNotFoundException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}
//			try {
//				// 3.�������������
//				ObjectOutputStream objOP = new ObjectOutputStream(fos1);
//				// 4.��Ŀ��·���ļ�д�����
//				objOP.writeObject(data);
//				// 5.�ر���Դ
//				objOP.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

//			3.�ͷ���Դ
			try {
				dos.close();
				client.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			release();
		}
	}
}
