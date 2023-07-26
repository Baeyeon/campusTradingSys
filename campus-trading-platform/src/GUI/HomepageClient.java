package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HomepageClient extends JFrame {

	public HomepageClient() throws UnknownHostException, IOException {
//		1.�������ӣ�ʹ��Socket�����ͻ���+�������ĵ�ַ�Ͷ˿�
		Socket client = new Socket("localhost", 9994);
//		2.�������������������
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		/*
		 * ��������
		 */
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		setTitle("��ҳ");

		/*
		 * ���ñ���
		 */
		// ��ǩ
		JLabel label = new JLabel(); // �ѱ���ͼƬ��ӵ���ǩ��
		label.setBounds(0, 0, 800, 700); // �ѱ�ǩ����Ϊ��ͼƬ�ȸߵȿ�
		// ͼƬ
		ImageIcon background = new ImageIcon("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\�ڰ���.jpg"); // ����һ������ͼƬ
		Image temp = background.getImage().getScaledInstance(label.getWidth(), label.getHeight(),
				background.getImage().SCALE_DEFAULT);
		background = new ImageIcon(temp);
		// ͼƬ�ӽ���ǩ
		label.setIcon(background);
		// ��ǩ�ӽ�panel
		JPanel myPanel = (JPanel) this.getContentPane(); // ���ҵ��������Ϊ�������
		myPanel.setOpaque(false); // ���ҵ��������Ϊ������
		myPanel.setLayout(new FlowLayout()); // ���ҵ��������Ϊ��������
		this.getLayeredPane().setLayout(null); // �ѷֲ����Ĳ����ÿ�
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // �ѱ�ǩ��ӵ��ֲ�������ײ�

		/*
		 * ������������ò���
		 */
		Container c = getContentPane();
		c.setLayout(null);

		/*
		 * �������
		 */
		JTextField searchText = new JTextField();
		searchText.setColumns(40);
		searchText.setBounds(150, 40, 500, 30);

		// ͼ��
		JButton btn1 = new JButton();
		btn1.setBounds(650, 40, 40, 30);
		ImageIcon ii = new ImageIcon("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\�����Ŵ�.jpg");
		Image temp1 = ii.getImage().getScaledInstance(btn1.getWidth(), btn1.getHeight(), ii.getImage().SCALE_DEFAULT);
		ii = new ImageIcon(temp1);
		btn1.setIcon(ii);
		btn1.setToolTipText("image");
		btn1.setBorderPainted(true);

		JButton btn2 = new JButton("�ϴ���Ʒ");
		btn2.setBounds(60, 40, 90, 30);
		btn2.setForeground(Color.pink);// ��ť������ɫ
		btn2.setBackground(Color.black);// ��ť�ı���ɫ

		JButton btn3 = new JButton("��װ");
		JButton btn4 = new JButton("�鼮");
		JButton btn5 = new JButton("����Ʒ");
		JButton btn6 = new JButton("�˶�����");
		btn3.setBounds(30, 150, 100, 30);
		btn4.setBounds(247, 150, 100, 30);
		btn5.setBounds(454, 150, 100, 30);
		btn6.setBounds(670, 150, 100, 30);

		/*
		 * ������
		 */
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String searchInfo = searchText.getText();
				/*
				 * �ͻ������ӵ�������
				 */
				try {
					dos.writeUTF("searchInfo=" + searchInfo);
					dos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				3.�ͷ���Դ
				try {
					dos.close();
					new ProductListClient();
					client.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		/*
		 * �ϴ���Ʒ��
		 */
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new ProductUploadClient();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		/*
		 * ��װ
		 */
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String kind = "��װ";
				try {
					dos.writeUTF(kind);
					dos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					dos.close();
					new ProductListClient();
					client.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		/*
		 * �鼮
		 */
		btn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String kind = "�鼮";
				try {
					dos.writeUTF(kind);
					dos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		/*
		 * ����Ʒ
		 */
		btn5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String kind = "����Ʒ";
				try {
					dos.writeUTF(kind);
					dos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		/*
		 * �˶�����
		 */
		btn6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String kind = "�˶�����";
				try {
					dos.writeUTF(kind);
					dos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		/*
		 * ������������
		 */
		myPanel.add(searchText);
		myPanel.add(btn1);
		myPanel.add(btn2);
//		myPanel.add(btn3);
//		myPanel.add(btn4);
//		myPanel.add(btn5);
//		myPanel.add(btn6);
		validate();
		setVisible(true);
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		new HomepageClient();
	}
}
