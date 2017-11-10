package com.ggcc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane  desktopPane = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("Library Main Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Data Maintenance");
		mnNewMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/base.png")));
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("Genres Management");
		mnNewMenu_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookTypeManager.png")));
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmAddGenres = new JMenuItem("Add Genres");
		mntmAddGenres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookGenreAddInterFrm bookGenreAddInterFrm = new BookGenreAddInterFrm();
				bookGenreAddInterFrm.setVisible(true);
				desktopPane.add(bookGenreAddInterFrm);
			}
		});
		mntmAddGenres.setIcon(new ImageIcon(MainFrame.class.getResource("/images/add.png")));
		mnNewMenu_2.add(mntmAddGenres);
		
		JMenuItem mntmMaitgenres = new JMenuItem("Maitain Genres");
		mntmMaitgenres.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		mnNewMenu_2.add(mntmMaitgenres);
		
		JMenu mnNewMenu_3 = new JMenu("Book Management");
		mnNewMenu_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookManager.png")));
		mnNewMenu.add(mnNewMenu_3);
		
		JMenuItem mntmAddBook = new JMenuItem("Add Book");
		mntmAddBook.setIcon(new ImageIcon(MainFrame.class.getResource("/images/add.png")));
		mnNewMenu_3.add(mntmAddBook);
		
		JMenuItem mntmMaitainBook = new JMenuItem("Maitain Book");
		mntmMaitainBook.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		mnNewMenu_3.add(mntmMaitainBook);
		
		JMenuItem mntmSafeExit = new JMenuItem("Safe Exit");
		mntmSafeExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure to Exit?");
				if(result == 0){
					dispose();
				}
				
			}
		});
		mntmSafeExit.setIcon(new ImageIcon(MainFrame.class.getResource("/images/exit.png")));
		mnNewMenu.add(mntmSafeExit);
		
		JMenu mnNewMenu_1 = new JMenu("About us");
		mnNewMenu_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/about.png")));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmAboutMaoYe = new JMenuItem("About Mao Ye");
		mntmAboutMaoYe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MaoInterFrm maoInterFrm = new MaoInterFrm();
				maoInterFrm.setVisible(true);
				desktopPane.add(maoInterFrm);
				
			}
		});
		mnNewMenu_1.add(mntmAboutMaoYe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		// Set JFrame maximization
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
