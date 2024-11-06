package com.lcit.models;

/**
 * @author Alexander Gutierrez
 */
public class Publisher {
	private int id;
	private String name;
	private Address address;
	private String phone;
	private String email;
	
	public Publisher() {
		super();
	}
	
	public Publisher(int id, String name, Address address, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email="
				+ email + "]";
	}		
}
