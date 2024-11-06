package com.lcit.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.sql.Date;

/**
 * @author Alexander Gutierrez
 */
public class Book {
	
	private int id;
	@NotNull
	@NotBlank
	private String isbn; //International Standard Book Number
	@NotNull
	@NotBlank
	private String title;
	private String subtitle;
	private String language;	
	private double price;
	private List<Author> authors;
	private List<Genre> genres;
	private Date publicationDate;
	private String edition;
	private Publisher publisher; 
	private int availability;
	
	public Book() {
		super();
	}

	public Book(int id, String isbn, String title, String subtitle, String language, double price, List<Author> authors,
			List<Genre> genres, Date publicationDate, String edition, Publisher publisher, int availability) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.subtitle = subtitle;
		this.language = language;
		this.price = price;
		this.authors = authors;
		this.genres = genres;
		this.publicationDate = publicationDate;
		this.edition = edition;
		this.publisher = publisher;
		this.availability = availability;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", subtitle=" + subtitle + ", language="
				+ language + ", price=" + price + ", authors=" + authors + ", genres=" + genres + ", publicationDate="
				+ publicationDate + ", edition=" + edition + ", publisher=" + publisher + ", availability="
				+ availability + "]";
	}	
}