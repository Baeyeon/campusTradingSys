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
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.mysql.bean.User;

public class registerClient extends JFrame {
	private DataOutputStream dos;
	static JTextField tAccount;
	static JTextField tId;
	static JPasswordField pwf;
	static String sexStr;
	static JTextField tTel;

	// 创建一个容器
	Container ct;

	// 创建背景面板。
	BackgroundPanel bgp;

	private Socket client = new Socket("localhost", 8001);

	public registerClient() throws UnknownHostException, IOException {
		this.dos = new DataOutputStream(this.client.getOutputStream());
		this.setBounds(200, 100, 420, 500);
		ct = this.getContentPane();
		this.setLayout(null);
		this.setDefaultCloseOperation(3);
		this.setTitle("注册");
		Container c = this.getContentPane();
		c.setLayout((LayoutManager) null);
		JLabel account = new JLabel("账号:");
		account.setFont(new java.awt.Font("仿宋", Font.BOLD, 15));
		account.setForeground(Color.white);
		account.setBackground(Color.white);
		JLabel id = new JLabel("用户名:");
		id.setFont(new java.awt.Font("仿宋", Font.BOLD, 15));
		id.setForeground(Color.white);
		id.setBackground(Color.white);

		JLabel sex = new JLabel("性别:");
		sex.setFont(new java.awt.Font("仿宋", Font.BOLD, 15));
		sex.setForeground(Color.white);
		sex.setBackground(Color.white);

		JLabel pwd = new JLabel("设置密码:");
		pwd.setFont(new java.awt.Font("仿宋", Font.BOLD, 15));
		pwd.setForeground(Color.white);
		pwd.setBackground(Color.white);

		JLabel rePwd = new JLabel("确认密码:");
		rePwd.setFont(new java.awt.Font("仿宋", Font.BOLD, 15));
		rePwd.setForeground(Color.white);
		rePwd.setBackground(Color.white);

		JLabel tel = new JLabel("联系电话:");
		tel.setFont(new java.awt.Font("仿宋", Font.BOLD, 15));
		tel.setForeground(Color.white);
		tel.setBackground(Color.white);

		account.setBounds(50, 50, 80, 40);
		id.setBounds(50, 100, 80, 40);
		sex.setBounds(50, 150, 80, 40);
		pwd.setBounds(50, 200, 80, 40);
		rePwd.setBounds(50, 250, 80, 40);
		tel.setBounds(50, 300, 80, 40);
		tAccount = new JTextField();
		tId = new JTextField();
		pwf = new JPasswordField();
		pwf.setColumns(20);
		pwf.setFont(new Font("Arial", 1, 20));
		final JPasswordField repwf = new JPasswordField();
		repwf.setColumns(20);
		repwf.setFont(new Font("Arial", 1, 20));
		tTel = new JTextField();
		tAccount.setBounds(150, 50, 200, 40);
		tAccount.setText("请输入您的学号");
		// tAccount.setFont(new java.awt.Font("仿宋", Font.BOLD , 15));

		tId.setBounds(150, 100, 200, 40);
		tId.setText("请输入您的用户名");
		// tId.setFont(new java.awt.Font("仿宋", Font.BOLD , 15));

		pwf.setBounds(150, 200, 200, 40);
		repwf.setBounds(150, 250, 200, 40);
		tTel.setBounds(150, 300, 200, 40);
		JButton btn1 = new JButton("确定");
		// btn1.setBorder(BorderFactory.createRaisedBevelBorder());
		btn1.setBorder(BorderFactory.createLoweredBevelBorder());
		btn1.setForeground(Color.white);
		btn1.setBackground(Color.white);
		btn1.setContentAreaFilled(false);
		JButton btn2 = new JButton("取消");
		btn2.setBorder(BorderFactory.createLoweredBevelBorder());
		btn2.setForeground(Color.white);
		btn2.setBackground(Color.white);
		btn2.setContentAreaFilled(false);
		btn1.setBounds(60, 380, 60, 30);
		btn2.setBounds(260, 380, 60, 30);
		ButtonGroup group = new ButtonGroup();
		JRadioButton rb1 = new JRadioButton("男");
		JRadioButton rb2 = new JRadioButton("女");
		rb1.setBounds(150, 150, 40, 30);
		rb2.setBounds(250, 150, 40, 30);
		rb1.setOpaque(false);
		rb1.setFont(new java.awt.Font("仿宋", Font.BOLD, 15));

		rb1.setForeground(Color.white);
		rb1.setBackground(Color.white);
		rb2.setOpaque(false);
		rb2.setFont(new java.awt.Font("仿宋", Font.BOLD, 15));

		rb2.setForeground(Color.white);
		rb2.setBackground(Color.white);
		group.add(rb1);
		group.add(rb2);
		final JLabel incorrect = new JLabel();
		incorrect.setBounds(75, 350, 200, 40);
		incorrect.setFont(new Font("仿宋", Font.PLAIN, 15));// 标贴的字体
		incorrect.setForeground(Color.white);// 标贴的颜色
		rb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerClient.sexStr = "男";
			}
		});
		rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerClient.sexStr = "女";
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerClient.this.dispose();

				try {
					new LogonClient();
				} catch (IOException var3) {
					var3.printStackTrace();
				}

			}
		});
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] c1 = registerClient.pwf.getPassword();
				String pwdStr = new String(c1);
				char[] c2 = repwf.getPassword();
				String repwdStr = new String(c2);
				String userAcct = registerClient.tAccount.getText();
				String userId = registerClient.tId.getText();
				String userTel = registerClient.tTel.getText();
				if (!pwdStr.equals(repwdStr)) {
					incorrect.setText("两次密码不一致！");
				} else {
					incorrect.setText("");

					try {
						registerClient.this.dos.writeUTF("userId=" + userId + "&" + "pwdStr=" + pwdStr + "&" + "sexStr="
								+ registerClient.sexStr + "&" + "userTel=" + userTel + "&" + "userAcct=" + userAcct);
						registerClient.this.dos.flush();
						registerClient.this.dos.close();
					} catch (IOException var11) {
						var11.printStackTrace();
					}

					registerClient.this.dispose();

					try {
						new LogonClient();
					} catch (IOException var10) {
						var10.printStackTrace();
					}
				}

			}
		});
		c.add(id);
		c.add(pwd);
		c.add(rePwd);
		c.add(tel);
		c.add(tId);
		c.add(pwf);
		c.add(repwf);
		c.add(tTel);
		c.add(btn1);
		c.add(btn2);
		c.add(sex);
		c.add(rb1);
		c.add(rb2);
		c.add(incorrect);
		c.add(account);
		c.add(tAccount);
		this.validate();

		bgp = new BackgroundPanel(
				(new ImageIcon("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\xing.jpg")).getImage());
		bgp.setBounds(0, 0, 420, 500);
		ct.add(bgp);

		this.setVisible(true);
	}

	public static User indexReturn() {
		return new User(tAccount.getText(), tId.getText(), new String(pwf.getPassword()), sexStr, tTel.getText());
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		new registerClient();
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
