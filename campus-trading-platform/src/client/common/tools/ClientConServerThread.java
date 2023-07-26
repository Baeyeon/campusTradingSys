package client.common.tools;

import client.common.Message;
import client.view.chat022;

import java.io.ObjectInputStream;
import java.net.Socket;

//客户端连接服务器的线程（客户端和服务器保持通讯的线程）
public class ClientConServerThread extends Thread {



    public Socket s;

    //构造函数
    public ClientConServerThread(Socket s){
        this.s = s;
    }

    public void run(){
        while(true){
            //不停的读取从服务器端发来的消息
            try {
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                Message m = (Message)ois.readObject();
                System.out.println("读取到从服务器发来的消息："+m.getSender()+"给"+m.getGetter()+"内容为："+m.getCon());


                //把从服务器获得的消息显示到该显示的界面
                 chat022 chattt =  Managechat02.getchat02(m.getGetter()+"和"+m.getSender());
                //显示
                chattt.showMessage(m);
/*

                ClientConServerThread sccc = ManageClientConServerThread.getClientConServerThread(m.getGetter());
                System.out.println(sccc);

                ObjectOutputStream oos = new ObjectOutputStream(sccc.s.getOutputStream());
                System.out.println("====================");
                oos.writeObject(m);

 */
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }
}

