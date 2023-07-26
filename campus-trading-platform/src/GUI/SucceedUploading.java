package GUI;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SucceedUploading extends JFrame {
	public SucceedUploading() {
		setBounds(500, 200, 200, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		/*
		 * 设置背景
		 */
		// 标签
		JLabel label = new JLabel(); // 把背景图片添加到标签里
		label.setBounds(0, 0, 200, 150); // 把标签设置为和图片等高等宽
		// 图片
		ImageIcon background = new ImageIcon("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\黑月亮.jpg"); // 创建一个背景图片
		Image temp = background.getImage().getScaledInstance(label.getWidth(), label.getHeight(),
				background.getImage().SCALE_DEFAULT);
		background = new ImageIcon(temp);
		// 图片加进标签
		label.setIcon(background);
		JPanel myPanel = (JPanel) this.getContentPane(); // 把我的面板设置为内容面板
		myPanel.setOpaque(false); // 把我的面板设置为不可视
		myPanel.setLayout(null); // 把我的面板设置为流动布局
		this.getLayeredPane().setLayout(null); // 把分层面板的布局置空
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // 把标签添加到分层面板的最底层

		JLabel l = new JLabel("上传成功！");
		l.setBounds(20, 25, 150, 60);
		l.setForeground(Color.white);

		myPanel.add(l);

		setVisible(true);
	}

	public static void main(String[] args) {
		new SucceedUploading();
	}
}
