package client.view;
/*
������������������
��Ϊ�ͻ���Ҫ���ڶ�ȡ״̬��������ǰ�������һ���߳�
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.common.Message;
import client.common.tools.ManageClientConServerThread;

public class chat022 extends JFrame implements ActionListener {

	public static JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;
	String friendId;

	// xinjia

	// ����һ������
	Container ct;
	// Container ctt;
	// ����������塣
	BackgroundPanel bgp;

//	public static void main(String[] args) {
//		/*
//		 * ��ȡ�û�����
//		 */
//		String OwnerId = null;
//		File file = new File("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\contact2");
//		FileInputStream fis = null;
//		try {
//			fis = new FileInputStream(file);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ObjectInputStream objIP;
//		try {
//			objIP = new ObjectInputStream(fis);
//			// ��ȡ�������ݣ���Ҫ��������ǿ��ת��Ϊ Ҫд����������
//			try {
//				OwnerId = (String) objIP.readObject();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			objIP.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		/*
//		 * ��ȡ��������
//		 */
//		String FriendId = null;
//		File file1 = new File("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\contactFile");
//		FileInputStream fis1 = null;
//		try {
//			fis1 = new FileInputStream(file1);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ObjectInputStream objIP1;
//		try {
//			objIP1 = new ObjectInputStream(fis1);
//			// ��ȡ�������ݣ���Ҫ��������ǿ��ת��Ϊ Ҫд����������
//			try {
//				FriendId = (String) objIP1.readObject();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			objIP1.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		chat022 c2 = new chat022(OwnerId, FriendId);
//	}

	public chat022(String ownerId, String friendId) {
		this.ownerId = ownerId;
		this.friendId = friendId;

//xinjia
		ct = this.getContentPane();

		this.setLayout(null);
		this.setSize(400, 300);
		this.setTitle("�㣨" + ownerId + ")���ں�" + friendId + "��̸");
		jta = new JTextArea();
		// jta.Edible(false);
		JScrollPane js = new JScrollPane(jta);
		jtf = new JTextField(15);
		jb = new JButton("����");
		jb.addActionListener(this);
		jp = new JPanel();
		// jp.add(jtf);
		// jb.setBackground(Color.cyan);

		jb.setFont(new java.awt.Font("������κ", Font.BOLD, 15));
		// jb.setBorder(BorderFactory.createRaisedBevelBorder());
		jb.setBorder(BorderFactory.createLoweredBevelBorder());
		// jb.setFont(new java.awt.Font("������κ", Font.BOLD + Font.ITALIC, 15));

		// Font f2 = new Font("����", Font.BOLD + Font.ITALIC, 22);
		// jb.setFont(f2);
		// jb .setFont(new java.awt.Font("�����п�", 1, 15));
		jb.setForeground(Color.white);
		jb.setBackground(Color.white);
		jb.setContentAreaFilled(false);

		js.setBounds(20, 20, 340, 180);
		jtf.setBounds(20, 220, 240, 20);
		jb.setBounds(280, 220, 80, 20);

		this.add(js);
		// this.add(jp);
		this.add(jtf);
		this.add(jb);

		// xinjia
		bgp = new BackgroundPanel(
				(new ImageIcon("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\third.jpg")).getImage());
		bgp.setBounds(0, 0, 400, 300);
		ct.add(bgp);

		this.setVisible(true);

	}

	public chat022(String ownerId, String friendId, ResultSet res) {
		this.ownerId = ownerId;
		this.friendId = friendId;
		this.setLayout(null);
		this.setSize(400, 300);
		this.setTitle("�㣨" + ownerId + ")���ں�" + friendId + "��̸");
		jta = new JTextArea();
		jtf = new JTextField(15);
		jb = new JButton("����");
		jb.addActionListener(this);
		jb.setBackground(Color.blue);

		jb.setFont(new java.awt.Font("�����п�", Font.BOLD + Font.ITALIC, 15));
		// Font f2 = new Font("����", Font.BOLD + Font.ITALIC, 22);
		// jb.setFont(f2);
		jb.setContentAreaFilled(false);

		jp = new JPanel();
		// jp.add(jtf);
		// jp.add(jb);

		jta.setBounds(10, 10, 360, 200);
		jtf.setBounds(10, 220, 200, 20);
		jb.setBounds(250, 220, 80, 20);

		this.add(jta);
		// this.add(jp);
		this.add(jtf);
		this.add(jb);

		System.out.println("jiayou");

		try {
			while (res.next()) {
				String xinxi = res.getString("��Ϣ����");

				this.jta.append(xinxi);
				String ssss = "zai ke hu duan xian shi ";
				this.jta.append(ssss);
			}
			System.out.println("nizhenbang");
			String nini = "kaixinjiuhao";
			this.jta.append(nini);

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setVisible(true);

	}

	public static class BackgroundPanel extends JPanel {
		Image im;

		public BackgroundPanel(Image im) {
			this.im = im;
			this.setOpaque(true);
		}

		// Draw the back ground.
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);

		}
	}

	// дһ����������ʾ��Ϣ
	public void showMessage(Message m) {
		String info = m.getSendTime() + " " + m.getSender() + "��" + m.getGetter() + "˵:" + m.getCon() + "\r\n";
		this.jta.append(info);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == jb) {
			// ����û�����˷��Ͱ�ť
			Message m = new Message();
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());

			String info = m.getSendTime() + " " + m.getSender() + "��" + m.getGetter() + "˵:" + m.getCon() + "\r\n";
			this.jta.append(info);

			// getgetter getter = new getgetter();
			// String geting = getter.getgetter(m.getGetter());

			// ���͸�������

			// oos = null;
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());

				oos.writeObject(m);

			} catch (IOException e) {
				e.printStackTrace();
			}

			jtf.setText("");

		}
	}

	/*
	 * @Override public void run() {
	 * 
	 * while(true){ //��ȡ(����������͵��ţ�
	 * 
	 * try { ObjectInputStream ois = new
	 * ObjectInputStream(myclientconserver.s.getInputStream());
	 * 
	 * 
	 * Message m = (Message)ois.readObject();
	 * 
	 * 
	 * //��ʾ String info =
	 * m.getSendTime()+" "+m.getSender()+"��"+m.getGetter()+"˵:"+m.getCon()+"\r\n";
	 * this.jta.append(info);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * }
	 * 
	 */

}
