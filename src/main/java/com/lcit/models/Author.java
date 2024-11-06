package com.lcit.models;

import java.sql.Date;
import java.util.List;

/**
 * @author Alexander Gutierrez
 */
public class Author extends Person{
	
	private String pseudonym;
	private List<Book> books;
	
	public Author() {
		
	}
	
	public Author(int id, String firstName, String lastName, Date dateOfBirth, String nationality, String email) {
		super(id, firstName, lastName, dateOfBirth, nationality, email);
	}

	public Author(int id, String firstName, String lastName, String pseudonym, Date dateOfBirth, String nationality, String email) {
		super(id, firstName, lastName, dateOfBirth, nationality, email);
		this.pseudonym = pseudonym;
//		this.books = books;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return super.toString() + "Author [pseudonym=" + pseudonym + ", books=" + books + "]";
	}


	
	
}
