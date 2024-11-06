package com.lcit.dao.publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lcit.dao.Database;
import com.lcit.dao.address.DaoAddressImp;
import com.lcit.models.Address;
import com.lcit.models.Publisher;

/**
 * @author Alexander Gutierrez
 */
public class DaoPublisherImp implements DaoPublisher {
	private Connection conn = Database.getConnection();
	@Override
	public int insert(Publisher publisher) throws SQLException {
		int rowsInserted = -1;
		String query = "INSERT INTO publisher (name, address_id, phone, email) VALUES (?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		
		if (checkAddressNotExists(publisher.getAddress().getStreet1())) {
			DaoAddressImp daoAddress = new DaoAddressImp();
			daoAddress.insert(publisher.getAddress());
		}
		
		publisher.setAddress(getAddressByStreet(publisher.getAddress().getStreet1()));
		
		statement.setString(1, publisher.getName());
		statement.setInt(2, publisher.getAddress().getId());
		statement.setString(3, publisher.getPhone());
		statement.setString(4, publisher.getEmail());
		
		rowsInserted = statement.executeUpdate();

		return rowsInserted;
	}

	@Override
	public Publisher selectOne(int primaryKey) throws SQLException {
		
		Publisher publisher = null;

		String query = "SELECT * FROM publisher WHERE id = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, primaryKey);
		ResultSet result = statement.executeQuery();

		if (result.next()) {
			Address address = getAddress(result.getInt("address_id"));
			
			publisher = new Publisher(
			result.getInt(1),
			result.getString(2),
			address,
			result.getString(4),
			result.getString(5));
		} 

		return publisher;
	}
	
	public Publisher selectByName(String name) throws SQLException {
		
		Publisher publisher = null;

		String query = "SELECT * FROM publisher WHERE name = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);
		ResultSet result = statement.executeQuery();

		if (result.next()) {
			Address address = getAddress(result.getInt("address_id"));
			
			publisher = new Publisher(
			result.getInt(1),
			result.getString(2),
			address,
			result.getString(4),
			result.getString(5));
		} 

		return publisher;
	}

	@Override
	public List<Publisher> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateOne(Publisher t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOne(Publisher t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// Miscellaneous Methods
	private boolean checkAddressNotExists(String street) throws SQLException {
		DaoAddressImp daoAddress = new DaoAddressImp();
		Address address = daoAddress.selectByStreet(street);
		return address == null;
	}
	
	private Address getAddress(int addressId) throws SQLException {
		DaoAddressImp daoAddress = new DaoAddressImp();
		return daoAddress.selectOne(addressId);	
	}
	
	private Address getAddressByStreet(String street) throws SQLException {
		DaoAddressImp daoAddress = new DaoAddressImp();
		return daoAddress.selectByStreet(street);	
	}
	
}
