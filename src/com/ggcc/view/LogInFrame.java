package com.ggcc.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.ggcc.dal.UserDao;
import com.ggcc.model.User;
import com.ggcc.util.DbUtil;
import com.ggcc.util.StringUtil;

public class LogInFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;

	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInFrame frame = new LogInFrame();
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
	public LogInFrame() {
		setResizable(false);
		setTitle("ManagerLogin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Library Management System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setIcon(new ImageIcon(LogInFrame.class.getResource("/images/logo.png")));
		
		JLabel lblNewLabel_1 = new JLabel("User Name: ");
		lblNewLabel_1.setIcon(new ImageIcon(LogInFrame.class.getResource("/images/userName.png")));
		
		JLabel lblNewLabel_2 = new JLabel("Password: ");
		lblNewLabel_2.setIcon(new ImageIcon(LogInFrame.class.getResource("/images/password.png")));
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(LogInFrame.class.getResource("/images/login.png")));
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(LogInFrame.class.getResource("/images/reset.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2))
					.addGap(67)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(passwordTxt)
						.addComponent(userNameTxt, 162, 162, Short.MAX_VALUE))
					.addContainerGap(38, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(73)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(83))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addComponent(lblNewLabel)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(19))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Login Event Handler
	 * @param e
	 */
	protected void loginActionPerformed(ActionEvent e) {
		String userName = this.userNameTxt.getText();
		String password = new String(this.passwordTxt.getPassword());
		if(StringUtil.isEmpty(userName)){
			JOptionPane.showMessageDialog(null, "Invalid user name");
			return;
		}
		if(StringUtil.isEmpty(password)){
			JOptionPane.showMessageDialog(null, "Invalid password");
			return;
		}
		User user = new User(userName, password);
		Connection con = null;
		try {
			con = dbUtil.GetCon();
			User currentUser = userDao.login(con, user);
			if(currentUser != null){
				JOptionPane.showMessageDialog(null,"Login Succeess");
			}else{
				JOptionPane.showMessageDialog(null,"Invalid user name or password");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		}
	}

	/**
	 * Reset Event Handler
	 * @param e
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
		this.userNameTxt.setText("");
		this.passwordTxt.setText("");
		
	}
}
