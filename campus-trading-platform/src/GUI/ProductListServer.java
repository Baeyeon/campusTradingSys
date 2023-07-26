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
//		  1.指定端口 使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(8888);
		boolean isRunning = true;
		while (isRunning) {
//		  2.阻塞式等待连接 accpet
			Socket client = server.accept();
			System.out.println("一个客户端建立了连接");
			new Thread(new Channel(client)).start();
		}
		server.close();
	}

	static class Channel implements Runnable {
		private Socket client;
		// 输入流
		private DataInputStream dis;
		// 输出流
		private DataOutputStream dos;

		// 构造函数
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

		// 接收数据
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

		// 发送数据
		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 释放资源
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

		// 线程体
		@Override
		public void run() {

//			  3.操作：输入输出流操作
			String commt = "";
			String pro_name = "";
			String pro_ownertel = "";
			// 分析数据
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
			 * 连接到数据库
			 */
			Product pro = new Product(pro_name, pro_ownertel, commt);
			UserDao_imp dao = new UserDao_imp();
			dao.uploadCommt(pro);

//			3.释放资源
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
