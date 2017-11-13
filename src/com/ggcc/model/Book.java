package com.ggcc.model;

public class Book {
	private int id;
	private String bookName;
	private String author;
	private String gender;
	private float price;
	private Integer bookGenreId;
	private String bookGenreName;
	private String bookDesc;
	
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public Book(String bookName, String author, Integer bookGenreId) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.bookGenreId = bookGenreId;
	}

	public Book(String bookName, String author, String gender, float price, Integer bookGenreId, String bookDesc) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.gender = gender;
		this.price = price;
		this.bookGenreId = bookGenreId;
		this.bookDesc = bookDesc;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Integer getBookGenreId() {
		return bookGenreId;
	}
	public void setBookGenreId(Integer bookGenreId) {
		this.bookGenreId = bookGenreId;
	}
	public String getBookGenreName() {
		return bookGenreName;
	}
	public void setBookGenreName(String bookGenreName) {
		this.bookGenreName = bookGenreName;
	}
	public String getBookDesc() {
		return bookDesc;
	}
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}
	
	
}
