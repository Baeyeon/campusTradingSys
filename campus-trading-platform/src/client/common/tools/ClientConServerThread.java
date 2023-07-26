package client.common.tools;

import client.common.Message;
import client.view.chat022;

import java.io.ObjectInputStream;
import java.net.Socket;

//�ͻ������ӷ��������̣߳��ͻ��˺ͷ���������ͨѶ���̣߳�
public class ClientConServerThread extends Thread {



    public Socket s;

    //���캯��
    public ClientConServerThread(Socket s){
        this.s = s;
    }

    public void run(){
        while(true){
            //��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
            try {
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                Message m = (Message)ois.readObject();
                System.out.println("��ȡ���ӷ�������������Ϣ��"+m.getSender()+"��"+m.getGetter()+"����Ϊ��"+m.getCon());


                //�Ѵӷ�������õ���Ϣ��ʾ������ʾ�Ľ���
                 chat022 chattt =  Managechat02.getchat02(m.getGetter()+"��"+m.getSender());
                //��ʾ
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

