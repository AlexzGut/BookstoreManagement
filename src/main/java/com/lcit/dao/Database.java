package com.lcit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Alexander Gutierrez
 */
public class Database {
	
	final static String DB_URL = "jdbc:mysql://localhost:3307/bookstore";
	final static String USERNAME = "alexander";
	final static String PASSWORD = "alexandermysql";
	
	public static Connection getConnection() {
		Connection conn = null;
		Properties properties = new Properties();
		properties.setProperty("user", USERNAME);
		properties.setProperty("password", PASSWORD);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, properties);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return conn;
	}

}