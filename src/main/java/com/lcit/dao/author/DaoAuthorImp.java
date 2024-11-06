package com.lcit.dao.author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lcit.dao.Database;
import com.lcit.models.Author;

/**
 * @author Alexander Gutierrez
 */
public class DaoAuthorImp implements DaoAuthor {
	private Connection conn = Database.getConnection();
	
	@Override
	public int insert(Author author) throws SQLException {
		int rowsInserted = -1;
		
		String query = "INSERT INTO author VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setInt(1, author.getId());
		statement.setString(2, author.getFirstName());
		statement.setString(3, author.getLastName());
		statement.setString(4, author.getPseudonym());
		statement.setDate(5, author.getDateOfBirth());
		statement.setString(6, author.getNationality());
		statement.setString(7, author.getEmail());
		
		rowsInserted = statement.executeUpdate();
		
		return rowsInserted;
	}

	@Override
	public Author selectOne(int primaryKey) throws SQLException {
		
		Author author = null;
		String query = "SELECT * FROM author WHERE id = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, primaryKey);
		
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			author = new Author(
					result.getInt(1),
					result.getString(2),
					result.getString(3),
					result.getString(4),
					result.getDate(5),
					result.getString(6),
					result.getString(7)
			);
		}
		return author;
	}

	public Author selectOne(Author author) throws SQLException {
		
		Author authorDTO = null;
		String query = "SELECT * FROM author WHERE first_name = ? AND last_name = ? AND date_of_birth = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, author.getFirstName());
		statement.setString(2, author.getLastName());
		statement.setDate(3, author.getDateOfBirth());
		
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			authorDTO = new Author(
					result.getInt(1),
					result.getString(2),
					result.getString(3),
					result.getString(4),
					result.getDate(5),
					result.getString(6),
					result.getString(7)
			);
		}
		return authorDTO;
	}
	
	@Override
	public List<Author> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateOne(Author t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOne(Author t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
