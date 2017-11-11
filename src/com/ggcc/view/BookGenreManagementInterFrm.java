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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;

public class BookGenreManagementInterFrm extends JInternalFrame {
	private JTable bookGenreTable;
	
	private DbUtil dbUtil = new DbUtil();
	private BookGenreDao bookGenreDao = new BookGenreDao();
	private JTextField s_bookGenreNameTxt;
	private JTextField idTxt;
	private JTextField bookGenreNameTxt;
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
		setBounds(100, 100, 450, 402);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblBookGenreName = new JLabel("Book genre name: ");
		
		s_bookGenreNameTxt = new JTextField();
		s_bookGenreNameTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookGenreSearchActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookGenreManagementInterFrm.class.getResource("/images/search.png")));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Table Operation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBookGenreName)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(s_bookGenreNameTxt, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
						.addComponent(scrollPane, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(72))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBookGenreName)
						.addComponent(s_bookGenreNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		
		JLabel lblId = new JLabel("id:");
		
		JLabel lblBookGenreName_1 = new JLabel("Book genre name:");
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		
		bookGenreNameTxt = new JTextField();
		bookGenreNameTxt.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		
		JTextArea bookGenreDescTxt = new JTextArea();
		
		JButton btnModify = new JButton("Modify");
		btnModify.setIcon(new ImageIcon(BookGenreManagementInterFrm.class.getResource("/images/modify.png")));
		
		JButton btnDelet = new JButton("Delete");
		btnDelet.setIcon(new ImageIcon(BookGenreManagementInterFrm.class.getResource("/images/delete.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblId)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addComponent(lblBookGenreName_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookGenreNameTxt, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDescription)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookGenreDescTxt, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnModify)
							.addPreferredGap(ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
							.addComponent(btnDelet)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(lblBookGenreName_1)
						.addComponent(bookGenreNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescription)
						.addComponent(bookGenreDescTxt, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnModify)
						.addComponent(btnDelet))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
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
		 * Book genre search event handler
		 * @param evt
		 */
		private void bookGenreSearchActionPerformed(ActionEvent evt) {
			String s_bookGenreName = s_bookGenreNameTxt.getText();
			BookGenre bookGenre = new BookGenre();
			bookGenre.setBookGenreName(s_bookGenreName);
			this.fillTable(bookGenre);
		
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
