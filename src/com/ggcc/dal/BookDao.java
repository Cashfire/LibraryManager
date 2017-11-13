package com.ggcc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ggcc.model.Book;
import com.ggcc.model.BookGenre;
import com.ggcc.util.StringUtil;

/**
 * Book DAO class
 * @author Mao
 *
 */
public class BookDao {
	
	/**
	 * Add book
	 * @param con
	 * @param book
	 * @return
	 */
	public int add(Connection con, Book book)throws Exception{
		String sql = "insert into t_book values(null,?, ?, ?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getGender());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setInt(5, book.getBookGenreId());
		pstmt.setString(6, book.getBookDesc());
		return pstmt.executeUpdate();
	}
	
	/**
	 * Showing the book list after searching
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con, Book book)throws Exception{
		StringBuffer sb = new StringBuffer("select * from t_book b, t_bookgenre bg where b.bookGenreId= bg.id");
		if(StringUtil.isNotEmpty(book.getBookName())){
			sb.append(" and b.bookName like '%"+book.getBookName()+"%'");
		}
		if(StringUtil.isNotEmpty(book.getAuthor())){
			sb.append(" and b.author like '%"+book.getAuthor()+"%'");
		}
		//if the user chose a book genre
		if(book.getBookGenreId() != null && book.getBookGenreId()!= -1){
			sb.append(" and b.bookGenreId="+ book.getBookGenreId());
		}
		//If nothing is chosen, then sb will mean select all.
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
}


