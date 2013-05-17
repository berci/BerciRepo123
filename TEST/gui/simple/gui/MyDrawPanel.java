package simple.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyDrawPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.orange);
		
		g.fillRect(20, 50, 100, 100);
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.add(new MyDrowPanel());
		frame.getContentPane().add(new MyDrawPanel());
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
