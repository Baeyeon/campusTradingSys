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
//		1.建立连接：使用Socket创建客户端+服务器的地址和端口
		Socket client = new Socket("localhost", 9994);
//		2.操作：输入输出流操作
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		/*
		 * 创建窗体
		 */
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		setTitle("首页");

		/*
		 * 设置背景
		 */
		// 标签
		JLabel label = new JLabel(); // 把背景图片添加到标签里
		label.setBounds(0, 0, 800, 700); // 把标签设置为和图片等高等宽
		// 图片
		ImageIcon background = new ImageIcon("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\黑白月.jpg"); // 创建一个背景图片
		Image temp = background.getImage().getScaledInstance(label.getWidth(), label.getHeight(),
				background.getImage().SCALE_DEFAULT);
		background = new ImageIcon(temp);
		// 图片加进标签
		label.setIcon(background);
		// 标签加进panel
		JPanel myPanel = (JPanel) this.getContentPane(); // 把我的面板设置为内容面板
		myPanel.setOpaque(false); // 把我的面板设置为不可视
		myPanel.setLayout(new FlowLayout()); // 把我的面板设置为流动布局
		this.getLayeredPane().setLayout(null); // 把分层面板的布局置空
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // 把标签添加到分层面板的最底层

		/*
		 * 获得容器并设置布局
		 */
		Container c = getContentPane();
		c.setLayout(null);

		/*
		 * 构造组件
		 */
		JTextField searchText = new JTextField();
		searchText.setColumns(40);
		searchText.setBounds(150, 40, 500, 30);

		// 图标
		JButton btn1 = new JButton();
		btn1.setBounds(650, 40, 40, 30);
		ImageIcon ii = new ImageIcon("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\搜索放大镜.jpg");
		Image temp1 = ii.getImage().getScaledInstance(btn1.getWidth(), btn1.getHeight(), ii.getImage().SCALE_DEFAULT);
		ii = new ImageIcon(temp1);
		btn1.setIcon(ii);
		btn1.setToolTipText("image");
		btn1.setBorderPainted(true);

		JButton btn2 = new JButton("上传商品");
		btn2.setBounds(60, 40, 90, 30);
		btn2.setForeground(Color.pink);// 按钮的字颜色
		btn2.setBackground(Color.black);// 按钮的背景色

		JButton btn3 = new JButton("服装");
		JButton btn4 = new JButton("书籍");
		JButton btn5 = new JButton("日用品");
		JButton btn6 = new JButton("运动器材");
		btn3.setBounds(30, 150, 100, 30);
		btn4.setBounds(247, 150, 100, 30);
		btn5.setBounds(454, 150, 100, 30);
		btn6.setBounds(670, 150, 100, 30);

		/*
		 * 搜索键
		 */
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String searchInfo = searchText.getText();
				/*
				 * 客户端连接到服务器
				 */
				try {
					dos.writeUTF("searchInfo=" + searchInfo);
					dos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				3.释放资源
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
		 * 上传商品键
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
		 * 服装
		 */
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String kind = "服装";
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
		 * 书籍
		 */
		btn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String kind = "书籍";
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
		 * 日用品
		 */
		btn5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String kind = "日用品";
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
		 * 运动器材
		 */
		btn6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String kind = "运动器材";
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
		 * 添加组件到容器
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
