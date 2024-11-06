package com.lcit.dao.genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lcit.dao.Database;
import com.lcit.models.Genre;

/**
 * @author Alexander Gutierrez
 */
public class DaoGenreImp implements DaoGenre{

	private Connection conn = Database.getConnection();
	
	@Override
	public int insert(Genre genre) throws SQLException {
		int rowsInserted = -1;
		String query = "INSERT INTO genre (name, description) VALUES (?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, genre.getName());
		statement.setString(2, genre.getDescription());
		
		rowsInserted = statement.executeUpdate();
		return rowsInserted;
	}

	@Override
	public Genre selectOne(int primaryKey) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Genre selectOne(String name) throws SQLException {
		
		Genre genre = null;
		String query = "SELECT * FROM genre WHERE name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			genre = new Genre(
					result.getInt(1),
					result.getString(2),
					result.getString(3)
			);
		}
		return genre;
	}

	@Override
	public List<Genre> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateOne(Genre t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOne(Genre t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
