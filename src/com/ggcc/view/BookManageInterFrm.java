package com.ggcc.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.ggcc.dal.BookDao;
import com.ggcc.dal.BookGenreDao;
import com.ggcc.model.Book;
import com.ggcc.model.BookGenre;
import com.ggcc.util.DbUtil;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookManageInterFrm extends JInternalFrame {
	private JTable bookTable;
	private JTextField s_bookNameTxt;
	private JTextField s_authorTxt;
	private JComboBox s_bookGenreJcb;

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
					BookManageInterFrm frame = new BookManageInterFrm();
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
	public BookManageInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Book Management");
		setBounds(100, 100, 750, 476);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Search Conditions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(scrollPane, Alignment.LEADING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
					.addGap(98))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(201, Short.MAX_VALUE))
		);
		
		JLabel lblBookName = new JLabel("Book name:");
		
		s_bookNameTxt = new JTextField();
		s_bookNameTxt.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Author:");
		
		s_authorTxt = new JTextField();
		s_authorTxt.setColumns(10);
		
		JLabel lblGenre = new JLabel("Genre:");
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookSearchActionPerformed(e);
			}
		});
		btnSearch.setIcon(new ImageIcon(BookManageInterFrm.class.getResource("/images/search.png")));
		
		s_bookGenreJcb = new JComboBox();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblBookName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblAuthor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblGenre)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_bookGenreJcb, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
					.addComponent(btnSearch))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBookName)
						.addComponent(lblAuthor)
						.addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGenre)
						.addComponent(s_bookGenreJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch)
						.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		bookTable = new JTable();
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Book name", "Author", "Gender", "Price", "Book description", "Genre"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.getColumnModel().getColumn(0).setPreferredWidth(35);
		bookTable.getColumnModel().getColumn(1).setPreferredWidth(108);
		bookTable.getColumnModel().getColumn(2).setPreferredWidth(76);
		bookTable.getColumnModel().getColumn(3).setPreferredWidth(48);
		bookTable.getColumnModel().getColumn(4).setPreferredWidth(47);
		bookTable.getColumnModel().getColumn(5).setPreferredWidth(222);
		scrollPane.setViewportView(bookTable);
		getContentPane().setLayout(groupLayout);

		this.fillBookGenre("search");
		this.fillTable(new Book());
	}
	
	/**
	 * Book searching event handler
	 * @param e
	 */
	private void BookSearchActionPerformed(ActionEvent evt) {
		String bookName = this.s_bookNameTxt.getText();
		String author = this.s_authorTxt.getText();
		BookGenre bookGenre = (BookGenre) this.s_bookGenreJcb.getSelectedItem();
		int bookGenreId = bookGenre.getId();
		Book book = new Book(bookName, author, bookGenreId);
		this.fillTable(book);
	}

	/**
	 * Initiate the bookGenreJcb
	 */
	private void fillBookGenre(String genre){
		Connection con = null;
		BookGenre bookGenre = null;
		try{
			con = dbUtil.GetCon();
			ResultSet rs = bookGenreDao.list(con,new BookGenre());
			if("search".equals(genre)){//?????????
				bookGenre = new BookGenre();
				bookGenre.setBookGenreName("Please choose one");
				bookGenre.setId(-1);
				this.s_bookGenreJcb.addItem(bookGenre);
			}
			while(rs.next()){
				bookGenre = new BookGenre();
				bookGenre.setBookGenreName(rs.getString("bookGenreName"));
				bookGenre.setId(rs.getInt("id"));
				if("search".equals(genre)){
					this.s_bookGenreJcb.addItem(bookGenre);
				}else if("modify".equals(genre)){
					
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeConnection(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Initiate the table info in the ScrollPane
	 * @param book
	 */
	private void fillTable(Book book){
		//the 1st time use can be fillTable(), but 2nd time it will be fillTable(book).
		//DefaultTableModel uses a Vector to store value objects
		DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
		dtm.setRowCount(0);//set to be 0 row (=clear the table)
		//search the database
		Connection con= null;
		try{
			con = dbUtil.GetCon();
			//Note: when the bookGenre is null, list() will select all from the sql table
			ResultSet rs = bookDao.list(con, book);
			//By moving the cursor of the result set rs, we get each vector to add to each dtm row
			while(rs.next()){
				//get string values from different columns in the sql t_book
				Vector<String> v = new Vector<String>(); //a Vector is like dynamic size array
				v.add(rs.getString("id"));//get the "id" value of the current row
				v.add(rs.getString("bookName"));
				v.add(rs.getString("author"));
				v.add(rs.getString("gender"));
				v.add(Float.toString(rs.getFloat("price")));
				v.add(rs.getString("bookDesc"));
				v.add(rs.getString("bookGenreName")); //because of the related query, we can also get from t_bookGenre.
				dtm.addRow(v);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				dbUtil.closeConnection(con);
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
		
	}
	
}


