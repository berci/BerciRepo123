package com.camline;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ChooserTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		// Create a file chooser
		String filename = File.separator+"tmp";
		JFileChooser fc = new JFileChooser(new File(filename));

		// Create the actions
		
		Action saveAction = new JFileChooserTest(frame, fc);

		// Create buttons for the actions
		
		JButton saveButton = new JButton(saveAction);

		// Add the buttons to the frame and show the frame
		
		frame.getContentPane().add(saveButton, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);


	}

}
