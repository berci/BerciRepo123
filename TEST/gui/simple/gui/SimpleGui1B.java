package simple.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SimpleGui1B /*implements ActionListener*/ {
	JButton button;
	
	public static void main(String[] args) {
		SimpleGui1B gui = new SimpleGui1B();
		gui.go();
	}
	
	public void go(){
		JFrame frame = new JFrame();
		button = new JButton();

		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				button.setText("I've been clicked!");
				
			}
		});
		
		frame.getContentPane().add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		button.setText("I've been clicked!");
//	}
}
