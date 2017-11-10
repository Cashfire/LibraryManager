package com.ggcc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.ggcc.model.BookGenre;

public class BookGenreDao {
	public int add(Connection con, BookGenre bookGenre)throws Exception{
		String sql = "insert into t_bookGenres values(null, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookGenre.getBookGenreName());
		pstmt.setString(2, bookGenre.getBookGenreDesc());
		return pstmt.executeUpdate();
	}
}
