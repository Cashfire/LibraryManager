package com.ggcc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MaoInterFrm extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MaoInterFrm frame = new MaoInterFrm();
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
	public MaoInterFrm() {
		setBackground(Color.PINK);
		setIconifiable(true);
		setClosable(true);
		setTitle("About Mao Ye");
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("https://github.com/Cashfire");
		lblNewLabel.setIcon(null);
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);

	}

}
