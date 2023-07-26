package client.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import client.common.tools.Managechat02;

public class chat011 extends JFrame implements MouseListener, ActionListener {

	// 创建一个容器
	Container ct;

	// 创建背景面板。
	BackgroundPanel bgp;
	BackgroundPanel bg;
	// Container ctt;

	// 处理第一张卡片
	JPanel jphy1, jphy2, jphy3;
	JScrollPane jsp1;
	String ownerId;

	ImageIcon icon;
	JLabel jl;
	JPanel jp;

	JButton but;

	public static void main(String[] args) {

		// chat011 c = new chat011("wsy");
	}

	public chat011(String ownerId) {
		// this.setTitle("联系人");
		this.ownerId = ownerId;
		this.setSize(250, 600);
		ct = this.getContentPane();
		this.setLayout(null);

		but = new JButton("看看新消息");
		but.setBorder(BorderFactory.createLoweredBevelBorder());
		but.setForeground(Color.white);
		but.setBackground(Color.white);
		but.setContentAreaFilled(false);

		// 假定有50个好友

		jphy2 = new JPanel(new GridLayout(50, 1, 0, 0));

		// jphy2.setOpaque(false);
		// jphy2.add(jl);
		/*
		 * { public void paintComponent(Graphics g) { icon = new
		 * ImageIcon("src\\images\\second.jpg"); Dimension dim=getSize();
		 * g.drawImage(icon.getImage(), 0,0,dim.width, dim.height, null); }
		 * 
		 * 
		 * };
		 * 
		 */
		/*
		 * //初始化50个好友 JLabel []jbls = new JLabel[50]; jbls[1]=new JLabel("王思旸样");
		 * jbls[2]=new JLabel("李艳艳"); jbls[1].addMouseListener(this);
		 * jbls[2].addMouseListener(this); jphy2.add(jbls[1]); jphy2.add(jbls[2]);
		 * for(int i=3;i<jbls.length;i++){
		 * 
		 * 
		 * jbls[i]=new JLabel(i+1+" ",JLabel.LEFT); jbls[i].addMouseListener(this);
		 * jphy2.add(jbls[i]); }
		 * 
		 * 
		 * 
		 */
		/*
		 * 
		 * icon = new ImageIcon("src\\images\\second.jpg"); for(int i=0;i<50;i++){
		 * JLabel jj = new JLabel(icon); jphy2.add(jj); }
		 * 
		 * 
		 * 
		 */
		// 初始化50个好友
		JLabel[] jbls = new JLabel[50];

		/*
		 * 
		 * //jbls[0]=new JLabel(launch()); //jbls[0].addMouseListener(this);
		 * //jphy2.add(jbls[0]); jbls[1] = new JLabel("王思旸样"); jbls[2] = new
		 * JLabel("李艳艳"); jbls[3] = new JLabel("李妍"); jbls[4] = new JLabel("王思旸");
		 * 
		 */

		// jbls[4].setOpaque(false);
		/*
		 * ImageIcon image = new ImageIcon("src\\images\\second.jpeg");
		 * image.setImage(image.getImage().getScaledInstance(400,20,Image.SCALE_DEFAULT)
		 * ); jphy2.setIcon(image);
		 * 
		 * 
		 */
		/*
		 * jbls[1].addMouseListener(this); jbls[2].addMouseListener(this);
		 * jbls[3].addMouseListener(this); jbls[4].addMouseListener(this);
		 * jphy2.add(jbls[1]); jphy2.add(jbls[2]); jphy2.add(jbls[3]);
		 * jphy2.add(jbls[4]);
		 * 
		 * 
		 */
		String[] name = JDBCchat.JDBCchat();
		for (int i = 0; i < name.length; i++) {

			jbls[i] = new JLabel(name[i]);

			// jbls[i] = new JLabel(i + 1 + " ", JLabel.LEFT);
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);
			jbls[i].setOpaque(false);
		}
		for (int j = 0; j < name.length; j++) {
			jbls[j].setForeground(Color.white);
			jbls[j].setBackground(Color.white);
		}

		but.setBounds(10, 520, 220, 30);
		but.addActionListener(this);

		jsp1 = new JScrollPane(jphy2);
		jsp1.setBounds(10, 10, 220, 500);

		// jsp1.setOpaque(false);
		// jsp1.add(jl);

		jphy2.setOpaque(false);
		jsp1.setOpaque(false);
		jsp1.getViewport().setOpaque(false);

		this.add(but);
		this.add(jsp1);
		this.setTitle(ownerId);
		/*
		 * ctt = jsp1.getHorizontalScrollBar(); //jsp1.setLayout(null); bg=new
		 * BackgroundPanel((new ImageIcon("src\\images\\second.jpg")).getImage());
		 * bg.setBounds(0,0,400,200); ctt.add(bg);
		 * 
		 * 
		 */
		bgp = new BackgroundPanel((new ImageIcon("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\yezi.jpg")).getImage());
		bgp.setBounds(0, 0, 250, 600);
		ct.add(bgp);

		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		if (mouseEvent.getClickCount() == 2) {
			String friendno = ((JLabel) mouseEvent.getSource()).getText();

			chat022 ch = new chat022(this.ownerId, friendno);
			// Thread t = new Thread(ch);
			// t.start();
			System.out.println("你希望和" + friendno + "聊天");

			// 把聊天界面加入到管理类
			Managechat02.addchat02(this.ownerId + "和" + friendno, ch);

		}
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {

	}

	/*
	 * 调解鼠标移入和移除的颜色
	 */
	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
		JLabel ji = (JLabel) mouseEvent.getSource();
		ji.setForeground(Color.lightGray);
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		JLabel jii = (JLabel) mouseEvent.getSource();
		jii.setForeground(Color.white);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == but) {

			ResultSet ree = JDBCC.JDBCS(ownerId);
			/*
			 * try { while (ree.next()) { System.out.println("你真棒！"); } }catch (Exception e)
			 * { e.printStackTrace(); }finally{ System.out.println("是不是空"); }
			 * 
			 */

			// String friend = JDBCC.duifangid;
			// System.out.println(friend);
			// new chat02(ownerId,friend,ree);

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
