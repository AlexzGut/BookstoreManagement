package com.lcit.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Alexander Gutierrez
 */
public interface DAO<T> {	
	// create
	public int insert(T t) throws SQLException;
	// read
	public T selectOne(int primaryKey) throws SQLException;
	// read all
	public List<T> selectAll() throws SQLException;
	// update
	public int updateOne(T t) throws SQLException;
	// delete
	public int deleteOne(T t) throws SQLException;
}