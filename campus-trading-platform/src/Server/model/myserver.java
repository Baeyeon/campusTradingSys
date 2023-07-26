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
这是服务器，在监听等待某个客户端来连接
 */

public class myserver {
	public static void main(String[] args) {
		myserver m = new myserver();
	}

	public myserver() {
		try {
			// 在9999监听
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("我是服务器，在9999监听");
			// 阻塞，等待连接
			while (true) {

				Socket s = ss.accept();
				// 接收客户端发来的信息

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
				// 下一行是测试内容
				System.out.println("服务器接收到用户id" + u.getId() + " 密码" + u.getPassword());
				// System.out.println("服务器接收到用户id"+u.getUserId()+" 密码"+u.getPasswd());

				Message m = new Message();
				System.out.println("message成功建立");
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				System.out.println("message成功建立................");
//暂时改动

				System.out.println("成功登陆");
				m.setMesType("1");
				oos.writeObject(m);
				System.out.println("*-*-*-*");

				// 这里就单开一个线程，让该线程与该客户端保持通讯
				SerConClientThread scct = new SerConClientThread(s);

				// ClientConServerThread sccct = new ClientConServerThread(s);
				System.out.println("*-*-*-*。。。。。。。。。。。。");
				// 自行改为下面的一行 ManageClientThread.addClientThread(u.getUserId(),scct);

				ManageClientThread.addClientThread(u.getId(), scct);

				// ManageClientConServerThread.addClientConServerThread(u.getAccount(),sccct);
				System.out.println("*-*-*-*、、、、、、、、、、、、、、、、、、");
				// 启动与该客户端通讯的线程

				// sccct.start();

				scct.start();
				System.out.println(scct);
				// System.out.println(sccct);
				System.out.println("*-*-*-*---------------------------------------------");
				/*
				 * if(u.getPassword().equals("123")){ //返回一个成功登陆的信息报
				 * 
				 * System.out.println("成功登陆"); m.setMesType("1"); oos.writeObject(m);
				 * System.out.println("*-*-*-*");
				 * 
				 * //这里就单开一个线程，让该线程与该客户端保持通讯 SerConClientThread scct = new
				 * SerConClientThread(s);
				 * 
				 * //自行改为下面的一行 ManageClientThread.addClientThread(u.getUserId(),scct);
				 * 
				 * ManageClientThread.addClientThread(u.getAccount(),scct); //启动与该客户端通讯的线程
				 * scct.start(); }else{ m.setMesType("2"); oos.writeObject(m); //关闭连接 s.close();
				 * }
				 */
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
