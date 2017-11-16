package com.ggcc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.ggcc.dal.BookGenreDao;
import com.ggcc.model.BookGenre;
import com.ggcc.util.DbUtil;
import com.ggcc.util.StringUtil;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class BookGenreAddInterFrm extends JInternalFrame {
	private JTextField bookGenreNameTxt;
	private JTextArea bookGenreDescTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private BookGenreDao bookGenreDao = new BookGenreDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookGenreAddInterFrm frame = new BookGenreAddInterFrm();
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
	public BookGenreAddInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Add Book Genre");
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("Book genre name: ");
		
		JLabel lblBookGenreDescription = new JLabel("Book genre description: ");
		
		bookGenreNameTxt = new JTextField();
		bookGenreNameTxt.setColumns(10);
		
		bookGenreDescTxt = new JTextArea();
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookGenreAddActionPerformed(e);
			}
		});
		btnAdd.setIcon(new ImageIcon(BookGenreAddInterFrm.class.getResource("/images/add.png")));
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restValueActionPerformed(e);
			}
		});
		btnReset.setIcon(new ImageIcon(BookGenreAddInterFrm.class.getResource("/images/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBookGenreDescription)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(bookGenreNameTxt)
							.addContainerGap(51, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(bookGenreDescTxt, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addComponent(btnAdd)
					.addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
					.addComponent(btnReset)
					.addGap(69))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(bookGenreNameTxt, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addComponent(lblBookGenreDescription))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(26)
							.addComponent(bookGenreDescTxt, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnReset))
					.addGap(30))
		);
		getContentPane().setLayout(groupLayout);
		//Set border: make the border of the TextArea visible
		bookGenreDescTxt.setBorder(new LineBorder(new java.awt.Color(127, 157, 185),1,false));

	}
	
	private void bookGenreAddActionPerformed(ActionEvent evt) {
		String bookGenreName = this.bookGenreNameTxt.getText();
		String bookGenreDesc = this.bookGenreDescTxt.getText();
		if(StringUtil.isEmpty(bookGenreName)){
			JOptionPane.showMessageDialog(null, "Please enter a book Genre");
			return;
		}
		BookGenre bookGenre = new BookGenre(bookGenreName, bookGenreDesc);
		Connection con = null;
		try{
			con = dbUtil.GetCon();
			int n = bookGenreDao.add(con, bookGenre);
			if(n==1){
				JOptionPane.showMessageDialog(null, "Book genre add succeess");
				this.resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "Book genre add failure");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Book genre add failure");
		}finally{
			try {
				dbUtil.closeConnection(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	// reset the event handler
	private void restValueActionPerformed(ActionEvent evt) {
		this.resetValue();
		
	}

	// reset the table
	public void resetValue(){
		this.bookGenreNameTxt.setText("");
		this.bookGenreDescTxt.setText("");
	}
}
