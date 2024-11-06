package com.lcit.dao.book;

import com.lcit.dao.DAO;
import com.lcit.models.Book;

import java.sql.SQLException;

/**
 * @author Alexander Gutierrez
 */
public interface DaoBook extends DAO<Book>{
	public Book selectOne(String isbn) throws SQLException;
	
	public int deleteOne(String isbn) throws SQLException;
}