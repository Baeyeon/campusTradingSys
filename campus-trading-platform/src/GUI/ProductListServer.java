package GUI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.mysql.bean.Product;
import com.mysql.dao.UserDao_imp;

public class ProductListServer {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
//		  1.ָ���˿� ʹ��ServerSocket����������
		ServerSocket server = new ServerSocket(8888);
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
			String commt = "";
			String pro_name = "";
			String pro_ownertel = "";
			// ��������
			String[] dataArray = receive().split("&");
			for (String info : dataArray) {
				String[] userinfo = info.split("=");
				if (userinfo[0].equals("commt")) {
					commt = userinfo[1];
				} else if (userinfo[0].equals("pro_name")) {
					pro_name = userinfo[1];
				} else if (userinfo[0].equals("pro_ownertel")) {
					pro_ownertel = userinfo[1];
				}
			}
			/*
			 * ���ӵ����ݿ�
			 */
			Product pro = new Product(pro_name, pro_ownertel, commt);
			UserDao_imp dao = new UserDao_imp();
			dao.uploadCommt(pro);

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
