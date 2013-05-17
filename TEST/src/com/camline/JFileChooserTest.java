package com.camline;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class JFileChooserTest extends AbstractAction {
    JFileChooser chooser;
    JFrame frame;

    JFileChooserTest(JFrame frame, JFileChooser chooser) {
        super("Save As...");
        this.chooser = chooser;
        this.frame = frame;
    }
 

	@Override
	public void actionPerformed(ActionEvent e) {
		chooser.showSaveDialog(frame);
		File file = chooser.getSelectedFile();
	}
};


