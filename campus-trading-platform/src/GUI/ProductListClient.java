package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import client.view.chat022;

public class ProductListClient extends JFrame {
	private String[][] data = null;
	private JPanel myPanel = new JPanel();
	private DataOutputStream dos;
	private JLabel[] pro_nameImp = null;
	private JButton[] commtbtn = null;
	private JTextArea[] pro_launchcommtImp = null;
	private JLabel[] pro_ownerImp = null;
	private JTextArea[] pro_commtImp = null;
	private JScrollPane[] pro_commtScroll = null;
	private JScrollPane[] pro_introScroll = null;

	// ����һ������
	Container ct;

	// ����������塣
	BackgroundPanel bgp;

//    1.�������ӣ�ʹ��Socket�����ͻ���+�������ĵ�ַ�Ͷ˿�
	Socket client = new Socket("localhost", 8888);

	public ProductListClient() throws IOException {
//    2.�������������������
		dos = new DataOutputStream(client.getOutputStream());

		ct = this.getContentPane();
		this.setLayout(null);

		JPanel myPanel = new JPanel();
		myPanel.setLayout(null);
		myPanel.setPreferredSize(new Dimension(800, 1000));

		/*
		 * ��ȡdata����
		 */
		File file = new File("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\searchFile");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectInputStream objIP;
		try {
			objIP = new ObjectInputStream(fis);
			// ��ȡ�������ݣ���Ҫ��������ǿ��ת��Ϊ Ҫд����������
			try {
				data = (String[][]) objIP.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			objIP.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JScrollPane pane = new JScrollPane(myPanel);
		pane.setBounds(0, 0, 790, 800);
		add(pane);

		pro_nameImp = new JLabel[data.length];
		pro_ownerImp = new JLabel[data.length];
		pro_launchcommtImp = new JTextArea[data.length];
		commtbtn = new JButton[data.length];
		pro_commtImp = new JTextArea[data.length];
		pro_commtScroll = new JScrollPane[data.length];
		pro_introScroll = new JScrollPane[data.length];

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 10, 800, 800);
		setTitle("��Ʒ�б�");

		for (int i = 0; i < data.length; i++) {
			/*
			 * ����Ʒ��ʾ
			 */
			// ���ͼƬ
			JLabel pic_l1 = new JLabel();
			pic_l1.setBounds(20, 20 + 460 * i, 250, 250);
			ImageIcon icon = new ImageIcon(data[i][1]);
			Image temp1 = icon.getImage().getScaledInstance(pic_l1.getWidth(), pic_l1.getHeight(),
					icon.getImage().SCALE_DEFAULT);
			icon = new ImageIcon(temp1);
			pic_l1.setIcon(icon);
			// ��������չʾ
			JLabel pro_name = new JLabel("��Ʒ���ƣ�");
			JLabel pro_count = new JLabel("��Ʒ������");
			JLabel pro_owner = new JLabel("���ң�");
			JLabel pro_price = new JLabel("��Ʒ�۸�");
			JLabel pro_intro = new JLabel("��Ʒ��飺");
			JLabel pro_commt = new JLabel("��Ʒ���ۣ�");
			JLabel pro_launchcommt = new JLabel("�������ۣ�");
			pro_name.setBounds(300, 20 + 460 * i, 100, 30);
			pro_count.setBounds(300, 60 + 460 * i, 100, 30);
			pro_price.setBounds(300, 100 + 460 * i, 100, 30);
			pro_owner.setBounds(300, 140 + 460 * i, 100, 30);
			pro_intro.setBounds(300, 180 + 460 * i, 100, 30);
			pro_commt.setBounds(300, 290 + 460 * i, 100, 30);
			pro_launchcommt.setBounds(300, 400 + 460 * i, 100, 30);

			pro_name.setFont(new Font("����", Font.PLAIN, 15));// ����������
			pro_name.setForeground(Color.white);// ��������ɫ
			pro_count.setFont(new Font("����", Font.PLAIN, 15));// ����������
			pro_count.setForeground(Color.white);// ��������ɫ
			pro_owner.setFont(new Font("����", Font.PLAIN, 15));// ����������
			pro_owner.setForeground(Color.white);// ��������ɫ
			pro_price.setFont(new Font("����", Font.PLAIN, 15));// ����������
			pro_price.setForeground(Color.white);// ��������ɫ
			pro_intro.setFont(new Font("����", Font.PLAIN, 15));// ����������
			pro_intro.setForeground(Color.white);// ��������ɫ
			pro_commt.setFont(new Font("����", Font.PLAIN, 15));// ����������
			pro_commt.setForeground(Color.white);// ��������ɫ
			pro_launchcommt.setFont(new Font("����", Font.PLAIN, 15));// ����������
			pro_launchcommt.setForeground(Color.white);// ��������ɫ

			pro_nameImp[i] = new JLabel(data[i][0]);
			JLabel pro_countImp = new JLabel(data[i][6]);
			pro_ownerImp[i] = new JLabel(data[i][3]);
			JLabel pro_priceImp = new JLabel(data[i][4]);
			JTextArea pro_introImp = new JTextArea(data[i][5]);
			pro_introImp.setBackground(Color.gray);
			pro_commtImp[i] = new JTextArea(data[i][7]);
			pro_commtImp[i].setEditable(false);
			pro_launchcommtImp[i] = new JTextArea();
			pro_nameImp[i].setBounds(380, 20 + 460 * i, 100, 30);
			pro_countImp.setBounds(380, 60 + 460 * i, 100, 30);
			pro_priceImp.setBounds(380, 100 + 460 * i, 100, 30);
			pro_ownerImp[i].setBounds(380, 140 + 460 * i, 100, 30);
			pro_introScroll[i] = new JScrollPane(pro_introImp);
			pro_introScroll[i].setBounds(380, 190 + 460 * i, 300, 100);
			pro_introImp.setEditable(false);
			pro_commtScroll[i] = new JScrollPane(pro_commtImp[i]);
			pro_commtScroll[i].setBounds(380, 300 + 460 * i, 300, 100);
			pro_launchcommtImp[i].setBounds(380, 410 + 460 * i, 300, 50);

			pro_nameImp[i].setFont(new Font("����", Font.PLAIN, 15));// ����������
			pro_nameImp[i].setForeground(Color.white);// ��������ɫ
			pro_ownerImp[i].setFont(new Font("����", Font.PLAIN, 15));// ����������
			pro_ownerImp[i].setForeground(Color.white);// ��������ɫ
			pro_introImp.setFont(new Font("����", Font.PLAIN, 15));// ����������
			pro_commtImp[i].setFont(new Font("����", Font.PLAIN, 15));// ����������
			pro_countImp.setFont(new Font("����", Font.PLAIN, 15));// ����������
			pro_countImp.setForeground(Color.white);// ��������ɫ
			pro_priceImp.setFont(new Font("����", Font.PLAIN, 15));// ����������
			pro_priceImp.setForeground(Color.white);// ��������ɫ
			pro_launchcommtImp[i].setFont(new Font("����", Font.PLAIN, 15));// ����������

			JButton contact = new JButton("��ϵ����");
			contact.setBounds(30, 320 + 460 * i, 90, 30);
			contact.setBorder(BorderFactory.createLoweredBevelBorder());
			contact.setForeground(Color.white);
			contact.setBackground(Color.white);
			contact.setContentAreaFilled(false);

			// hiehie
			JButton buybtn = new JButton("����");
			buybtn.setBounds(170, 320 + 460 * i, 90, 30);
			buybtn.setBorder(BorderFactory.createLoweredBevelBorder());
			buybtn.setForeground(Color.white);
			buybtn.setBackground(Color.white);
			buybtn.setContentAreaFilled(false);

			commtbtn[i] = new JButton("�ύ");
			commtbtn[i].setBounds(680, 430 + 460 * i, 60, 30);
			commtbtn[i].setBorder(BorderFactory.createLoweredBevelBorder());
			commtbtn[i].setForeground(Color.white);
			commtbtn[i].setBackground(Color.white);
			commtbtn[i].setContentAreaFilled(false);

			commtListener lis = new commtListener(i);
			commtbtn[i].addActionListener(lis);

			/*
			 * ���۶�������
			 */
			commtbtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub��
					String commt = pro_launchcommtImp[0].getText();
//               String id =
					String pro_name = pro_nameImp[0].getText();
					/*
					 * �ͻ������ӵ�������
					 */
					try {
						dos.writeUTF("commt=" + commt + "&" + "pro_name=" + pro_name/* +"&"+"id="+id */);
						dos.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//               3.�ͷ���Դ
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

			myPanel.setOpaque(false);
			pane.setOpaque(false);
			pane.getViewport().setOpaque(false);

			myPanel.add(pic_l1);
			myPanel.add(pro_name);
			myPanel.add(pro_count);
			myPanel.add(pro_price);
			myPanel.add(pro_owner);
			myPanel.add(pro_intro);
			myPanel.add(pro_nameImp[i]);
			myPanel.add(pro_countImp);
			myPanel.add(pro_priceImp);
			myPanel.add(pro_ownerImp[i]);
			myPanel.add(pro_introScroll[i]);
			myPanel.add(pro_commt);
			myPanel.add(pro_commtScroll[i]);
			myPanel.add(buybtn);
			myPanel.add(pro_launchcommt);
			myPanel.add(pro_launchcommtImp[i]);
			myPanel.add(commtbtn[i]);
			myPanel.add(contact);

			/*
			 * �����򡱶�������
			 */
			buybtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new SucceedBuying();
				}
			});

			/*
			 * ����ϵ���ҡ���������
			 */
			contact.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String friend = pro_owner.getText();
					String owner = null;
					File file = new File("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\contact2");
					FileInputStream fis = null;
					try {
						fis = new FileInputStream(file);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ObjectInputStream objIP;
					try {
						objIP = new ObjectInputStream(fis);
						// ��ȡ�������ݣ���Ҫ��������ǿ��ת��Ϊ Ҫд����������
						try {
							owner = (String) objIP.readObject();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						objIP.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					new chat022(owner, friend);
				}
			});
		}

		validate();// ��֤�����е������ʹ�������ٴβ��������ˢ�¹��ܣ�

		bgp = new BackgroundPanel(
				(new ImageIcon("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\�ǿ�.jpg")).getImage());
		bgp.setBounds(0, 0, 800, 1000);
		ct.add(bgp);

		setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		new ProductListClient();
	}

	/*
	 * ���۶�������
	 */
	class commtListener implements ActionListener {
		int count;

		public commtListener(int i) {
			count = i;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String commt = pro_launchcommtImp[count].getText();
			String pro_name = pro_nameImp[count].getText();
			String pro_owner = pro_ownerImp[count].getText();
			/*
			 * �ͻ������ӵ�������
			 */
			try {
				dos.writeUTF("commt=" + commt + "&" + "pro_name=" + pro_name + "&" + "pro_owner="
						+ pro_owner/* +"&"+"id="+id */);
				dos.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//      3.�ͷ���Դ
			try {
				dos.close();
				new ProductListClient();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

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

}
