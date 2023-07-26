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
//		  1.指定端口 使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(9994);
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
			String searchInfo = "";
			// 分析数据1

			String[] userinfo = receive().split("=");
			if (userinfo[0].equals("searchInfo")) {
				searchInfo = userinfo[1];
			}
			/*
			 * 获得数据库返回信息
			 */
			UserDao_imp dao = new UserDao_imp();
			String[][] data = dao.search(searchInfo);

			// 1.创建目标路径
			File file = new File("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\searchFile");
			// 2.创建流通道
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				// 3.创建对象输出流
				ObjectOutputStream objOP = new ObjectOutputStream(fos);
				// 4.向目标路径文件写入对象
				objOP.writeObject(data);
				// 5.关闭资源
				objOP.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			// 分析数据2
//			String kindInfo = "";
//			String userInfo2 = receive();
//			if (userInfo2.equals("服装")) {
//				kindInfo = "服装";
//			} else if (userInfo2.equals("书籍")) {
//				kindInfo = "书籍";
//			} else if (userInfo2.equals("日用品")) {
//				kindInfo = "日用品";
//			} else if (userInfo2.equals("运动器材")) {
//				kindInfo = "运动器材";
//			}
//			UserDao_imp dao2 = new UserDao_imp();
//			String[][] data2 = dao2.search(1, searchInfo);
//
//			// 1.创建目标路径
//			File file1 = new File("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\kindSearchFile");
//			// 2.创建流通道
//			FileOutputStream fos1 = null;
//			try {
//				fos1 = new FileOutputStream(file1);
//			} catch (FileNotFoundException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}
//			try {
//				// 3.创建对象输出流
//				ObjectOutputStream objOP = new ObjectOutputStream(fos1);
//				// 4.向目标路径文件写入对象
//				objOP.writeObject(data);
//				// 5.关闭资源
//				objOP.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

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
