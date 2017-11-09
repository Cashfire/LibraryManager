package com.ggcc.util;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * Function: database util
 */
public class DbUtil {
	private String dbUrl="jdbc:mysql://localhost:3306/db_library"; 
	private String dbUserName="root"; 
	private String dbPassword="NIni"; 
	private String jdbcName="com.mysql.jdbc.Driver"; 
	
	/**
	 * get connection from jdbc
	 * @return
	 * @throws Exception
	 */
	public Connection GetCon()throws Exception{
		//Returns the Class object related to the given string name
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	
	public void closeConnection(Connection con)throws Exception{
		if(con != null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbutil = new DbUtil();
		try {
			dbutil.GetCon();
			System.out.println("connect succeed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("connect failed");
		}
		
	}
}