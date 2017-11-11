package com.ggcc.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ggcc.dal.BookGenreDao;
import com.ggcc.model.BookGenre;
import com.ggcc.util.DbUtil;

public class BookGenreManagementInterFrm extends JInternalFrame {
	private JTable bookGenreTable;
	
	private DbUtil dbUtil = new DbUtil();
	private BookGenreDao bookGenreDao = new BookGenreDao();
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
		setClosable(true);
		setIconifiable(true);
		setRootPaneCheckingEnabled(false);
		setTitle("Book Genre Management");
		setBounds(100, 100, 450, 300);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(69)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(85, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(63)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(79, Short.MAX_VALUE))
		);
		
		bookGenreTable = new JTable();
		bookGenreTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Book Genre Name", "Book Genre Description"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookGenreTable.getColumnModel().getColumn(0).setPreferredWidth(32);
		bookGenreTable.getColumnModel().getColumn(1).setPreferredWidth(102);
		bookGenreTable.getColumnModel().getColumn(2).setPreferredWidth(193);
		scrollPane.setViewportView(bookGenreTable);
		getContentPane().setLayout(groupLayout);
		
		this.fillTable(new BookGenre()); //call fillTable function with a null object
	}
		/**
		 * Initiate the table (clear the set of search result)
		 * @param bookGenre
		 */
		private void fillTable(BookGenre bookGenre){
			//cast the TableModel interface to a DefaulTableModel class
			//DefaultTableModel uses a Vector to store value objects
			DefaultTableModel dtm = (DefaultTableModel) bookGenreTable.getModel();
			dtm.setRowCount(0);//set to be 0 row (=clear the table)
			//search the database
			Connection con= null;
			try{
				con = dbUtil.GetCon();
				//
				ResultSet rs = bookGenreDao.list(con, bookGenre);
				//By moving the cursor of the result set rs, we get each vector to add to each dtm row
				while(rs.next()){
					Vector<String> v = new Vector<String>(); //a Vector is like dynamic size array
					v.add(rs.getString("id"));//get the "id" value of the current row
					v.add(rs.getString("bookGenreName"));
					v.add(rs.getString("bookGenreDesc"));
					//add one table model row to the dtm
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
