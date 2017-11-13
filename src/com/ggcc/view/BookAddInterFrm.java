package com.ggcc.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.ggcc.dal.BookDao;
import com.ggcc.dal.BookGenreDao;
import com.ggcc.model.Book;
import com.ggcc.model.BookGenre;
import com.ggcc.util.DbUtil;
import com.ggcc.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookAddInterFrm extends JInternalFrame {
	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField priceTxt;
	private JComboBox bookGenreJcb;
	private JTextArea bookDescTxt;
	private JRadioButton maleJrb;
	private JRadioButton femaleJrb;
	
	private DbUtil dbUtil = new DbUtil();
	private BookGenreDao bookGenreDao = new BookGenreDao();
	private BookDao bookDao = new BookDao();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookAddInterFrm frame = new BookAddInterFrm();
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
	public BookAddInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("Add Book");
		setBounds(100, 100, 464, 432);
		
		JLabel lblBookName = new JLabel("Book name: ");
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Author: ");
		
		authorTxt = new JTextField();
		authorTxt.setColumns(10);
		
		JLabel lblAuthorGender = new JLabel("Author gender:");
		
		maleJrb = new JRadioButton("M");
		buttonGroup.add(maleJrb);
		maleJrb.setSelected(true);
		
		femaleJrb = new JRadioButton("F");
		buttonGroup.add(femaleJrb);
		
		JLabel lblPrice = new JLabel("Price:");
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		
		JLabel lblBookDescription = new JLabel("Book description:");
		
		bookDescTxt = new JTextArea();
		
		JLabel lblBookGenre = new JLabel("Book genre:");
		
		bookGenreJcb = new JComboBox();
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookAddActionPerformed(e);
			}
		});
		btnAdd.setIcon(new ImageIcon(BookAddInterFrm.class.getResource("/images/add.png")));
		
		JButton btnRest = new JButton("Rest");
		btnRest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnRest.setIcon(new ImageIcon(BookAddInterFrm.class.getResource("/images/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAdd)
							.addGap(18)
							.addComponent(btnRest))
						.addComponent(lblBookDescription)
						.addComponent(bookDescTxt, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblBookGenre)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(bookGenreJcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblBookName)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookNameTxt))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblAuthorGender)
									.addGap(17)
									.addComponent(maleJrb)
									.addGap(18)
									.addComponent(femaleJrb)))
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAuthor)
								.addComponent(lblPrice))
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(priceTxt)
								.addComponent(authorTxt, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))))
					.addGap(54))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblBookName)
							.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblAuthor)
							.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAuthorGender)
						.addComponent(maleJrb, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(femaleJrb)
						.addComponent(lblPrice)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(26)
							.addComponent(lblBookGenre))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(bookGenreJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(30)
					.addComponent(lblBookDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnRest))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		//Set border: make the border of the TextArea visible
		bookDescTxt.setBorder(new LineBorder(new java.awt.Color(127, 157, 185),1,false));

		
		fillBookGenre();
	}
	
	/**
	 * Reset Event hander 
	 * @param e
	 */
	private void resetValueActionPerformed(ActionEvent e) {
		this.resetValue();
	}

	/**
	 * Event handler of adding book
	 * @param e
	 */
	private void bookAddActionPerformed(ActionEvent evt) {
		String bookName = this.bookNameTxt.getText();
		String author = this.authorTxt.getText();
		String price = this.priceTxt.getText();
		String bookDesc = this.bookDescTxt.getText();
		
		if(StringUtil.isEmpty(bookName)){
			JOptionPane.showMessageDialog(null, "Please enter a book name");
			return;
		}
		if(StringUtil.isEmpty(author)){
			JOptionPane.showMessageDialog(null, "Please enter the author");
			return;
		}
		if(StringUtil.isEmpty(price)){
			JOptionPane.showMessageDialog(null, "Please enter the price");
			return;
		}
		
		String gender = "";
		if(maleJrb.isSelected()){
			gender = "male";			
		}else if(femaleJrb.isSelected()){
			gender = "female";
		}
		
		BookGenre bookGenre = (BookGenre) bookGenreJcb.getSelectedItem();
		int bookGenreId = bookGenre.getId();
		
		Book book = new Book(bookName, author, gender, Float.parseFloat(price), bookGenreId, bookDesc);
		
		Connection con = null;
		try{
			con = dbUtil.GetCon();
			int addNum = bookDao.add(con, book);
			if(addNum ==1){
				JOptionPane.showMessageDialog(null, "Book add succeess");
				resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "Book add failure");
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			try {
				dbUtil.closeConnection(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Book add failure");
			}
		}
				
	}

	/**
	 * Reset the input of BookAddInterFrm 
	 */
	private void resetValue() {
		this.bookNameTxt.setText("");
		this.authorTxt.setText("");
		this.priceTxt.setText("");
		this.bookDescTxt.setText("");
		this.maleJrb.setSelected(true);
		this.bookDescTxt.setText("");
		if(this.bookGenreJcb.getItemCount() > 0){
			this.bookGenreJcb.setSelectedIndex(0);
		}
		
	}

	/**
	 * Initiate the JComboBox bookGenreJcb
	 */
	private void fillBookGenre(){
		Connection con = null;
		BookGenre bookGenre = new BookGenre();
		try{
			con = dbUtil.GetCon();
			ResultSet rs = bookGenreDao.list(con, new BookGenre());
			while(rs.next()){
				bookGenre = new BookGenre();
				bookGenre.setId(rs.getInt("id"));
				bookGenre.setBookGenreName(rs.getString("bookGenreName"));
				this.bookGenreJcb.addItem(bookGenre);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		
	}
}



