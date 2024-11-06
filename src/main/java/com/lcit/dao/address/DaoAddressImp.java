package com.lcit.dao.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lcit.dao.Database;
import com.lcit.models.Address;

/**
 * @author Alexander Gutierrez
 */
public class DaoAddressImp implements DaoAddress{
	private Connection conn = Database.getConnection();
	
	@Override
	public int insert(Address address) throws SQLException {
		int rowsInserted = -1;

		String query = "INSERT INTO address (street1, street2, city, state_province, postal_code, country) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, address.getStreet1());
		statement.setString(2, address.getStreet2());
		statement.setString(3, address.getCity());
		statement.setString(4, address.getStateProvince());
		statement.setString(5, address.getPostalCode());
		statement.setString(6, address.getCountry());
		
		rowsInserted = -statement.executeUpdate();

		return rowsInserted;
	}

	@Override
	public Address selectOne(int primaryKey) throws SQLException {
		
		Address address = null;

		String query = "SELECT * FROM address WHERE id = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, primaryKey);
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			address =  new Address (
				result.getInt(1),
				result.getString(2),
				result.getString(3),
				result.getString(4),
				result.getString(5),
				result.getString(6),
				result.getString(7));
		}

		return address;
	}

	@Override
	public List<Address> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateOne(Address t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOne(Address t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Address selectByStreet(String street) throws SQLException {
		Connection conn = Database.getConnection();
		Address address = null;

		String query = "SELECT * FROM address WHERE street1 = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, street);
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			address =  new Address (
			result.getInt(1),
			result.getString(2),
			result.getString(3),
			result.getString(4),
			result.getString(5),
			result.getString(6),
			result.getString(7));
		}

		return address;
	}

}
