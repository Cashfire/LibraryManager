package com.ggcc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class BookGenreManagementInterFrm extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookGenreManagementInterFrm frame = new BookGenreManagementInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookGenreManagementInterFrm() {
		setBounds(100, 100, 450, 300);

	}

}
