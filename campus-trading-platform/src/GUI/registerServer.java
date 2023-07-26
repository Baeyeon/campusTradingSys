package GUI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.mysql.bean.User;
import com.mysql.dao.UserDao_imp;

public class registerServer {

	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
//		  1.ָ���˿� ʹ��ServerSocket����������
		ServerSocket server = new ServerSocket(8001);
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
				// TODO Auto-generated catch block
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
//		3.�������������������
			String userAcct = "";
			String userId = "";
			String pwdStr = "";
			String sexStr = "";
			String userTel = "";
			// ��������
			String[] dataArray = receive().split("&");
			for (String info : dataArray) {
				String[] userinfo = info.split("=");
				if (userinfo[0].equals("userId")) {
					userId = userinfo[1];
				} else if (userinfo[0].equals("pwdStr")) {
					pwdStr = userinfo[1];
				} else if (userinfo[0].equals("sexStr")) {
					sexStr = userinfo[1];
				} else if (userinfo[0].equals("userTel")) {
					userTel = userinfo[1];
				} else if (userinfo[0].equals("userAcct")) {
					userAcct = userinfo[1];
				}
			}
			/*
			 * ���ӵ����ݿ�
			 */
			User newU = new User(userAcct, userId, pwdStr, sexStr, userTel);
			UserDao_imp dao = new UserDao_imp();
			dao.register(newU);
			release();
		}
	}
}