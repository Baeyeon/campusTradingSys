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
		 * ���ñ���
		 */
		// ��ǩ
		JLabel label = new JLabel(); // �ѱ���ͼƬ��ӵ���ǩ��
		label.setBounds(0, 0, 200, 150); // �ѱ�ǩ����Ϊ��ͼƬ�ȸߵȿ�
		// ͼƬ
		ImageIcon background = new ImageIcon("C:\\Users\\frozen\\workspace\\TradeGUI\\src\\GUI\\������.jpg"); // ����һ������ͼƬ
		Image temp = background.getImage().getScaledInstance(label.getWidth(), label.getHeight(),
				background.getImage().SCALE_DEFAULT);
		background = new ImageIcon(temp);
		// ͼƬ�ӽ���ǩ
		label.setIcon(background);
		JPanel myPanel = (JPanel) this.getContentPane(); // ���ҵ��������Ϊ�������
		myPanel.setOpaque(false); // ���ҵ��������Ϊ������
		myPanel.setLayout(null); // ���ҵ��������Ϊ��������
		this.getLayeredPane().setLayout(null); // �ѷֲ����Ĳ����ÿ�
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // �ѱ�ǩ��ӵ��ֲ�������ײ�

		JLabel l = new JLabel("�ϴ��ɹ���");
		l.setBounds(20, 25, 150, 60);
		l.setForeground(Color.white);

		myPanel.add(l);

		setVisible(true);
	}

	public static void main(String[] args) {
		new SucceedUploading();
	}
}
