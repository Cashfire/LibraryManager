package com.ggcc.model;
/**
 * 
 * @author Mao
 *
 */
public class BookGenre {
	private int id;
	private String bookGenreName;
	private String bookGenreDesc;
	
	
	public BookGenre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BookGenre(String bookGenreName, String bookGenreDesc) {
		super();
		this.bookGenreName = bookGenreName;
		this.bookGenreDesc = bookGenreDesc;
	}

	public BookGenre(int id, String bookGenreName, String bookGenreDesc) {
		super();
		this.id = id;
		this.bookGenreName = bookGenreName;
		this.bookGenreDesc = bookGenreDesc;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookGenreName() {
		return bookGenreName;
	}
	public void setBookGenreName(String bookGenreName) {
		this.bookGenreName = bookGenreName;
	}
	public String getBookGenreDesc() {
		return bookGenreDesc;
	}
	public void setBookGenreDesc(String bookGenreDesc) {
		this.bookGenreDesc = bookGenreDesc;
	}
	
	

}
