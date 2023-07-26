package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProductUploadClient extends JFrame {
	private static String finalKind = "��װ";

	public ProductUploadClient() throws UnknownHostException, IOException {
//		1.�������ӣ�ʹ��Socket�����ͻ���+�������ĵ�ַ�Ͷ˿�
		Socket client = new Socket("localhost", 7777);
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());

		/*
		 * ��������
		 */
		setTitle("��Ʒ�ϴ�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(610, 520);

		/*
		 * ���ñ���
		 */
		JLabel label = new JLabel(); // �ѱ���ͼƬ��ӵ���ǩ��
		label.setBounds(0, 0, 650, 520); // �ѱ�ǩ����Ϊ��ͼƬ�ȸߵȿ�
		ImageIcon background = new ImageIcon("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\С����.jpg"); // ����һ������ͼƬ
		Image temp = background.getImage().getScaledInstance(label.getWidth(), label.getHeight(),
				background.getImage().SCALE_DEFAULT);
		background = new ImageIcon(temp);
		label.setIcon(background);
		JPanel panel = (JPanel) getContentPane(); // ���ҵ��������Ϊ�������
		panel.setOpaque(false); // ���ҵ��������Ϊ������
		panel.setLayout(new FlowLayout()); // ���ҵ��������Ϊ��������
		getLayeredPane().setLayout(null); // �ѷֲ����Ĳ����ÿ�
		getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // �ѱ�ǩ��ӵ��ֲ�������ײ�

		/*
		 * ������������ò���
		 */
		Container c = getContentPane();
		c.setLayout(null);

		/*
		 * �������
		 */
		JLabel pinfor = new JLabel("��Ʒ������Ϣ");
		pinfor.setBounds(100, 40, 300, 30);
		pinfor.setFont(new Font("����", Font.BOLD, 20));// ����������
		pinfor.setForeground(Color.white);

		JLabel style = new JLabel("���ͣ�");
		style.setBounds(100, 80, 60, 30);
		style.setFont(new Font("����", Font.PLAIN, 18));// ����������
		style.setForeground(Color.white);

		JComboBox pstyle = new JComboBox();// ����ѡ��������
		pstyle.addItem("��װ");
		pstyle.addItem("�鼮");
		pstyle.addItem("����Ʒ");
		pstyle.addItem("�˶�����");
		pstyle.addItem("����");
		pstyle.setBounds(200, 80, 250, 30);
		JLabel pname = new JLabel("���ƣ�");
		pname.setBounds(100, 120, 60, 30);
		pname.setFont(new Font("����", Font.PLAIN, 18));// ����������
		pname.setForeground(Color.white);

		JTextField pnameText = new JTextField(10);
		pnameText.setBounds(200, 120, 250, 30);

		JLabel pprice = new JLabel("�۸�");
		pprice.setBounds(100, 160, 60, 30);
		pprice.setFont(new Font("����", Font.PLAIN, 18));// ����������
		pprice.setForeground(Color.white);

		JTextField ppriceText = new JTextField(10);
		ppriceText.setBounds(200, 160, 250, 30);

		JLabel pusername = new JLabel("���ң�");
		pusername.setBounds(100, 200, 200, 30);
		pusername.setFont(new Font("����", Font.PLAIN, 18));// ����������
		pusername.setForeground(Color.white);

		JTextField pusernameText = new JTextField(10);
		pusernameText.setBounds(200, 200, 250, 30);

		JLabel purl = new JLabel("ͼƬ·����");
		purl.setBounds(100, 240, 200, 30);
		purl.setFont(new Font("����", Font.PLAIN, 18));// ����������
		purl.setForeground(Color.white);

		JTextField purlText = new JTextField(100);
		purlText.setBounds(200, 240, 250, 30);

		JLabel pcount = new JLabel("��Ʒ������");
		pcount.setBounds(100, 280, 200, 30);
		pcount.setFont(new Font("����", Font.PLAIN, 18));// ����������
		pcount.setForeground(Color.white);

		JTextField pcountText = new JTextField(100);
		pcountText.setBounds(200, 280, 250, 30);

		JLabel introduce = new JLabel("���ܣ�");
		introduce.setBounds(100, 320, 200, 30);
		introduce.setFont(new Font("����", Font.PLAIN, 18));// ����������
		introduce.setForeground(Color.white);

		JTextArea pintroduceText = new JTextArea("");
		JScrollPane pintroducegundong = new JScrollPane(pintroduceText);
		pintroducegundong.setBounds(200, 320, 250, 80);
		JButton upload = new JButton("�ϴ�");
		upload.setBounds(200, 420, 120, 30);
		upload.setBorder(BorderFactory.createLoweredBevelBorder());
		upload.setForeground(Color.white);
		upload.setBackground(Color.white);
		upload.setContentAreaFilled(false);

		/*
		 * ������
		 */
		panel.add(pinfor);
		panel.add(style);
		panel.add(pstyle);
		panel.add(pprice);
		panel.add(ppriceText);
		panel.add(pname);
		panel.add(pnameText);
		panel.add(pcount);
		panel.add(pcountText);
		panel.add(introduce);
		panel.add(pintroducegundong);
		panel.add(purl);
		panel.add(purlText);
		panel.add(pusername);
		panel.add(pusernameText);
		panel.add(upload);

		setVisible(true);
		/*
		 * �������� ����ѡ��
		 */
		pstyle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = pstyle.getSelectedIndex();
				if (index == 0)
					finalKind = "��װ";
				if (index == 1)
					finalKind = "�鼮";
				if (index == 2)
					finalKind = "����Ʒ";
				if (index == 3)
					finalKind = "�˶�����";
				if (index == 4)
					finalKind = "����";
			}
		});
		/*
		 * �������� �ϴ���
		 */
		upload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ���Դ���
				String pname = pnameText.getText();
				String pprice = ppriceText.getText();
				String pusername = pusernameText.getText();
				String intro = pintroduceText.getText();
				String purl = purlText.getText();
				String pcount = pcountText.getText();
				/*
				 * �ͻ������ӵ�������
				 */
				try {
					dos.writeUTF("pname=" + pname + "&" + "pprice=" + pprice + "&" + "puser=" + pusername + "&"
							+ "intro=" + intro + "&" + "purl=" + purl + "&" + "finalKind=" + finalKind + "&" + "pcount="
							+ pcount);
					dos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/*
				 * �ͷ���Դ
				 */
				try {
					dos.close();
					client.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new SucceedUploading();
				dispose();
			}
		});

	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		new ProductUploadClient();
	}
}
