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
	private static String finalKind = "服装";

	public ProductUploadClient() throws UnknownHostException, IOException {
//		1.建立连接：使用Socket创建客户端+服务器的地址和端口
		Socket client = new Socket("localhost", 7777);
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());

		/*
		 * 创建窗体
		 */
		setTitle("商品上传");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(610, 520);

		/*
		 * 设置背景
		 */
		JLabel label = new JLabel(); // 把背景图片添加到标签里
		label.setBounds(0, 0, 650, 520); // 把标签设置为和图片等高等宽
		ImageIcon background = new ImageIcon("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\小星星.jpg"); // 创建一个背景图片
		Image temp = background.getImage().getScaledInstance(label.getWidth(), label.getHeight(),
				background.getImage().SCALE_DEFAULT);
		background = new ImageIcon(temp);
		label.setIcon(background);
		JPanel panel = (JPanel) getContentPane(); // 把我的面板设置为内容面板
		panel.setOpaque(false); // 把我的面板设置为不可视
		panel.setLayout(new FlowLayout()); // 把我的面板设置为流动布局
		getLayeredPane().setLayout(null); // 把分层面板的布局置空
		getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // 把标签添加到分层面板的最底层

		/*
		 * 获得容器并设置布局
		 */
		Container c = getContentPane();
		c.setLayout(null);

		/*
		 * 构造组件
		 */
		JLabel pinfor = new JLabel("商品基本信息");
		pinfor.setBounds(100, 40, 300, 30);
		pinfor.setFont(new Font("仿宋", Font.BOLD, 20));// 标贴的字体
		pinfor.setForeground(Color.white);

		JLabel style = new JLabel("类型：");
		style.setBounds(100, 80, 60, 30);
		style.setFont(new Font("仿宋", Font.PLAIN, 18));// 标贴的字体
		style.setForeground(Color.white);

		JComboBox pstyle = new JComboBox();// 类型选择下拉框
		pstyle.addItem("服装");
		pstyle.addItem("书籍");
		pstyle.addItem("日用品");
		pstyle.addItem("运动器材");
		pstyle.addItem("其他");
		pstyle.setBounds(200, 80, 250, 30);
		JLabel pname = new JLabel("名称：");
		pname.setBounds(100, 120, 60, 30);
		pname.setFont(new Font("仿宋", Font.PLAIN, 18));// 标贴的字体
		pname.setForeground(Color.white);

		JTextField pnameText = new JTextField(10);
		pnameText.setBounds(200, 120, 250, 30);

		JLabel pprice = new JLabel("价格：");
		pprice.setBounds(100, 160, 60, 30);
		pprice.setFont(new Font("仿宋", Font.PLAIN, 18));// 标贴的字体
		pprice.setForeground(Color.white);

		JTextField ppriceText = new JTextField(10);
		ppriceText.setBounds(200, 160, 250, 30);

		JLabel pusername = new JLabel("卖家：");
		pusername.setBounds(100, 200, 200, 30);
		pusername.setFont(new Font("仿宋", Font.PLAIN, 18));// 标贴的字体
		pusername.setForeground(Color.white);

		JTextField pusernameText = new JTextField(10);
		pusernameText.setBounds(200, 200, 250, 30);

		JLabel purl = new JLabel("图片路径：");
		purl.setBounds(100, 240, 200, 30);
		purl.setFont(new Font("仿宋", Font.PLAIN, 18));// 标贴的字体
		purl.setForeground(Color.white);

		JTextField purlText = new JTextField(100);
		purlText.setBounds(200, 240, 250, 30);

		JLabel pcount = new JLabel("商品数量：");
		pcount.setBounds(100, 280, 200, 30);
		pcount.setFont(new Font("仿宋", Font.PLAIN, 18));// 标贴的字体
		pcount.setForeground(Color.white);

		JTextField pcountText = new JTextField(100);
		pcountText.setBounds(200, 280, 250, 30);

		JLabel introduce = new JLabel("介绍：");
		introduce.setBounds(100, 320, 200, 30);
		introduce.setFont(new Font("仿宋", Font.PLAIN, 18));// 标贴的字体
		introduce.setForeground(Color.white);

		JTextArea pintroduceText = new JTextArea("");
		JScrollPane pintroducegundong = new JScrollPane(pintroduceText);
		pintroducegundong.setBounds(200, 320, 250, 80);
		JButton upload = new JButton("上传");
		upload.setBounds(200, 420, 120, 30);
		upload.setBorder(BorderFactory.createLoweredBevelBorder());
		upload.setForeground(Color.white);
		upload.setBackground(Color.white);
		upload.setContentAreaFilled(false);

		/*
		 * 添加组件
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
		 * 动作监听 类型选择
		 */
		pstyle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = pstyle.getSelectedIndex();
				if (index == 0)
					finalKind = "服装";
				if (index == 1)
					finalKind = "书籍";
				if (index == 2)
					finalKind = "日用品";
				if (index == 3)
					finalKind = "运动器材";
				if (index == 4)
					finalKind = "其他";
			}
		});
		/*
		 * 动作监听 上传键
		 */
		upload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 属性传输
				String pname = pnameText.getText();
				String pprice = ppriceText.getText();
				String pusername = pusernameText.getText();
				String intro = pintroduceText.getText();
				String purl = purlText.getText();
				String pcount = pcountText.getText();
				/*
				 * 客户端连接到服务器
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
				 * 释放资源
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
