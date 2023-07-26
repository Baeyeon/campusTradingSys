package GUI;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.mysql.bean.Product;
import com.mysql.dao.UserDao_imp;

public class ProductUploadServer {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
//		  1.指定端口 使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(7777);
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
				// TODO Auto-generated catch block
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
//			接收数据
			String pname = "";
			String pprice = "";
			String puser = "";
			String intro = "";
			String purl = "";
			String finalKind = "";
			String pcount = "";

			String[] dataArray = receive().split("&");
			for (String info : dataArray) {
				String[] userinfo = info.split("=");
				if (userinfo[0].equals("pname")) {
					pname = userinfo[1];
				} else if (userinfo[0].equals("pprice")) {
					pprice = userinfo[1];
				} else if (userinfo[0].equals("puser")) {
					puser = userinfo[1];
				} else if (userinfo[0].equals("intro")) {
					intro = userinfo[1];
				} else if (userinfo[0].equals("purl")) {
					purl = userinfo[1];
				} else if (userinfo[0].equals("finalKind")) {
					finalKind = userinfo[1];
				} else if (userinfo[0].equals("pcount")) {
					pcount = userinfo[1];
				}
			}
//			

//			图片文件存储
			InputStream is;
//			OutputStream os;
			try {
				is = new BufferedInputStream(client.getInputStream());
////				os = new BufferedOutputStream(new FileOutputStream("src/tcp.png"));
//				byte[] flush = new byte[1024];
//				int len;
//				while ((len = is.read(flush)) != -1) { // len=-1时读取结束
//					os.write(flush, 0, len);
//				}
//				os.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			release();
			/*
			 * 连接到数据库
			 */
			Product pro = new Product(pname, purl, finalKind, puser, pprice, intro, pcount);
			UserDao_imp dao = new UserDao_imp();
			dao.upload(pro);
		}
	}
}
