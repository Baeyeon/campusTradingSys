//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.bean.JDBCU;
import com.mysql.bean.User;

import client.model.clientuser;
import client.view.chat011;

public class LogonClient extends JFrame {
	// public class LogonClient extends JFrame {
	public Socket client = new Socket("localhost", 8880);// wsy改为public
	public JButton btn1;// wsy
	public JButton btn2;// wsy
	public JTextField t1;// 下面的final被去掉
	public JPasswordField t2;

	// 创建一个容器
	Container ct;

	// 创建背景面板。
	BackgroundPanel bgp;

	public LogonClient() throws IOException {
		final DataOutputStream dos = new DataOutputStream(this.client.getOutputStream());
		this.setBounds(300, 300, 400, 280);
		this.setDefaultCloseOperation(3);

		this.setTitle("登录");

		Container c = this.getContentPane();
		c.setLayout((LayoutManager) null);

		t1 = new JTextField();
		t1.setColumns(200);
		t1.setBounds(120, 50, 200, 40);
		t2 = new JPasswordField();
		t2.setColumns(200);
		t2.setFont(new Font("Arabial", 1, 20));
		t2.setBounds(120, 100, 200, 40);
		btn1 = new JButton("登录");
		btn1.setBorder(BorderFactory.createLoweredBevelBorder());
		btn1.setForeground(Color.white);
		btn1.setBackground(Color.white);
		btn1.setContentAreaFilled(false);

		btn2 = new JButton("注册");
		btn2.setBorder(BorderFactory.createLoweredBevelBorder());
		btn2.setForeground(Color.white);
		btn2.setBackground(Color.white);
		btn2.setContentAreaFilled(false);

		btn1.setBounds(60, 180, 60, 30);
		btn2.setBounds(260, 180, 60, 30);
		JLabel id = new JLabel("账号:");
		id.setFont(new java.awt.Font("仿宋", Font.BOLD, 15));
		id.setForeground(Color.white);
		id.setBackground(Color.white);
		id.setBounds(50, 50, 60, 40);
		// id.setFont();
		JLabel pwd = new JLabel("密码:");
		pwd.setFont(new java.awt.Font("仿宋", Font.BOLD, 15));
		pwd.setForeground(Color.white);
		pwd.setBackground(Color.white);
		pwd.setBounds(50, 100, 60, 40);

		/*
		 * 登录
		 */
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userAcct = t1.getText();
				String userPwd = new String(t2.getPassword());

				String xingming = JDBCU.JDBCU(userAcct);
				System.out.println(xingming);
				clientuser cu = new clientuser();
				User u = new User(xingming);

				if (cu.checkUser(u)) {
					new chat011(xingming);
// LogonClient.dispose();
// this.dispose();
				} else {
// JOptionPane.showMessageDialog(this, "用户名密码错误");
				}

				try {
					dos.writeUTF("uacct=" + userAcct + "&" + "upwd=" + userPwd);
					dos.flush();
				} catch (IOException var6) {
					var6.printStackTrace();
				}

				try {
					dos.close();
					LogonClient.this.client.close();
				} catch (IOException var5) {
					var5.printStackTrace();
				}
				try {
					new HomepageClient();
				} catch (IOException e1) {
// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();

				String owner = t1.getText();
				// 1.创建目标路径
				File file = new File("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\contact2");
				// 2.创建流通道
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(file);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					// 3.创建对象输出流
					ObjectOutputStream objOP = new ObjectOutputStream(fos);
					// 4.向目标路径文件写入对象
					objOP.writeObject(owner);
					// 5.关闭资源
					objOP.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/*
				 * wsy
				 *
				 */
				/*
				 * if (e.getSource() == btn1) {
				 *
				 * clientuser cu = new clientuser(); User u = new User(t1.getText().trim(), new
				 * String(t2.getPassword()));
				 *
				 * if (cu.checkUser(u)) { new chat011(userAcct); // LogonClient.dispose();
				 * //this.dispose(); } else { // JOptionPane.showMessageDialog(this, "用户名密码错误");
				 * }
				 *
				 *
				 *
				 */

// this.dispose();
// new chat01();
			}

// new chat011(userAcct);

// }
		});

		/*
		 * 注册
		 */
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogonClient.this.dispose();

				try {
					new registerClient();
				} catch (UnknownHostException var3) {
					var3.printStackTrace();
				} catch (IOException var4) {
					var4.printStackTrace();
				}

			}
		});
		c.add(t1);
		c.add(t2);
		c.add(btn1);
		c.add(btn2);
		c.add(id);
		c.add(pwd);
		this.validate();

		bgp = new BackgroundPanel(
				(new ImageIcon("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\third.jpg")).getImage());
		bgp.setBounds(0, 0, 400, 280);
		c.add(bgp);

		this.setVisible(true);
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		new LogonClient();
	}

	static class BackgroundPanel extends JPanel {
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
