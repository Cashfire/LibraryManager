package com.ggcc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ggcc.model.BookGenre;
import com.ggcc.util.StringUtil;

public class BookGenreDao {
	
	//add to sql from BookGenreAddInterFrm
	public int add(Connection con, BookGenre bookGenre)throws Exception{
		String sql = "insert into t_bookgenre values(null, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookGenre.getBookGenreName());
		pstmt.setString(2, bookGenre.getBookGenreDesc());
		return pstmt.executeUpdate(); 
	}
	
	/**
	 * Search book genre set
	 * @param con
	 * @param bookGenre
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con, BookGenre bookGenre)throws Exception{
		//Unlike Strings, StringBuffer objects can be modified over and over again
		StringBuffer sb = new StringBuffer("select * from t_bookgenre");
		if(StringUtil.isNotEmpty(bookGenre.getBookGenreName())){
			//java syntax: "..."
			//sql syntax: WHERE CustomerName LIKE '%L%': finds any values that have "L" in any position
			sb.append(" and bookGenreName like '%"+bookGenre.getBookGenreName()+"%'");
		}
		//If bookGenre.getBookGenreName is null, then sb will be "select * from t_bookgenre".
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	/**
	 * Delete a book genre
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con, String id)throws Exception{
		String sql = "delete from t_bookGenre where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	/**
	 * Modify the book genre
	 * @param con
	 * @param bookGenre
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con, BookGenre bookGenre)throws Exception{
		String sql = "update t_bookGenre set bookGenreName=?,bookGenreDesc=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookGenre.getBookGenreName());
		pstmt.setString(2, bookGenre.getBookGenreDesc());
		pstmt.setInt(3, bookGenre.getId());
		return pstmt.executeUpdate();
	}
}
