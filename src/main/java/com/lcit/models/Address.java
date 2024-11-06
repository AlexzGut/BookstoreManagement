package com.lcit.models;

/**
 * @author Alexander Gutierrez
 */
public class Address {
	
	private int id;
	private String street1;
	private String street2;
	private String city;
	private String stateProvince;
	private String postalCode;
	private String country;
	
	public Address() {
		super();
	}
	
	public Address(int id, String street1, String street2, String city, String stateProvince, String postalCode,
			String country) {
		super();
		this.id = id;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.stateProvince = stateProvince;
		this.postalCode = postalCode;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street1=" + street1 + ", street2=" + street2 + ", city=" + city
				+ ", stateProvince=" + stateProvince + ", postalCode=" + postalCode + ", country=" + country + "]";
	}	
}