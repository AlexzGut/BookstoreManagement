package com.lcit.dao.book;

import com.lcit.dao.Database;
import com.lcit.dao.author.DaoAuthorImp;
import com.lcit.dao.genre.DaoGenreImp;
import com.lcit.dao.publisher.DaoPublisherImp;
import com.lcit.models.Author;
import com.lcit.models.Book;
import com.lcit.models.Genre;
import com.lcit.models.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Alexander Gutierrez
 */
public class DaoBookImp implements DaoBook{
	private Connection conn = Database.getConnection();
	
	@Override
	public int insert(Book book) throws SQLException {
		
		int rowsInserted = -1;
		String query = "INSERT INTO book (isbn, title, subtitle, language, price, publication_date, edition, publisher_id, availability) VALUES (?,?,?,?,?,?,?,?,?)";

		book.setPublisher(insertPublisherIfNotExist(book));		
		book.setAuthors(insertAuthorsIfNotExist(book));		
		book.setGenres(insertGenresIfNotExist(book));	
				
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, book.getIsbn());
		statement.setString(2, book.getTitle());
		statement.setString(3, book.getSubtitle());
		statement.setString(4, book.getLanguage());
		statement.setDouble(5, book.getPrice());
		statement.setDate(6, book.getPublicationDate());
		statement.setString(7, book.getEdition());
		statement.setInt(8, book.getPublisher().getId());
		statement.setInt(9, book.getAvailability());
		
		rowsInserted = statement.executeUpdate();

		if (rowsInserted != -1) {
			int bookId = selectOne(book.getIsbn()).getId();
			book.setId(bookId);
			for (Author author : book.getAuthors()) {
				insertRecordBookAuthor(bookId, author.getId());
			}
			
			for (Genre genre : book.getGenres()) {
				insertRecordBookGenre(bookId, genre.getId());
			}
		}
		
		return rowsInserted;
	}
	
	@Override
	public Book selectOne(int primaryKey) throws SQLException {
		Book book = null;
		String query = "SELECT * FROM book WHERE isbn = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, primaryKey);
		
		ResultSet result = statement.executeQuery();
		
		if (result.next()) {
			Publisher publisher = getPublisherById(result.getInt("publisher_id"));
			
			String isbn = result.getString("isbn");
			List<Author> authors = getAuthors(result.getString(isbn));
			List<Genre> genres = getGenres(result.getString(isbn));
			
			book = new Book(
					result.getInt(1),
					result.getString(2),
					result.getString(3),
					result.getString(4),
					result.getString(5),
					result.getDouble(6),
					authors,
					genres,
					result.getDate(7),
					result.getString(8),
					publisher,
					result.getInt(10)
			);
		}
		return book;
	}

	@Override
	public Book selectOne(String isbn) throws SQLException {
		
		Book book = null;
		String query = "SELECT * FROM book WHERE isbn = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, isbn);
		
		ResultSet result = statement.executeQuery();
		
		if (result.next()) {
			Publisher publisher = getPublisherById(result.getInt("publisher_id"));
			
			List<Author> authors = getAuthors(isbn);
			List<Genre> genres = getGenres(isbn);
			
			book = new Book(
					result.getInt(1),
					result.getString(2),
					result.getString(3),
					result.getString(4),
					result.getString(5),
					result.getDouble(6),
					authors,
					genres,
					result.getDate(7),
					result.getString(8),
					publisher,
					result.getInt(10)
			);
		}
		return book;
	}

	@Override
	public List<Book> selectAll() throws SQLException {
		List<Book> books = new ArrayList<>();
		String query = "SELECT * FROM book";
		Statement statement = conn.createStatement();

		ResultSet result = statement.executeQuery(query);
		
		while (result.next()) {
			Publisher publisher = getPublisherById(result.getInt("publisher_id"));
			
			String isbn = result.getString("isbn");
			List<Author> authors = getAuthors(isbn);
			List<Genre> genres = getGenres(isbn);
			
			books.add(new Book(
					result.getInt(1),
					result.getString(2),
					result.getString(3),
					result.getString(4),
					result.getString(5),
					result.getDouble(6),
					authors,
					genres,
					result.getDate(7),
					result.getString(8),
					publisher,
					result.getInt(10)
			));
		}
		return books;
	}

	@Override
	public int updateOne(Book book) throws SQLException {
		
		int rowsInserted = -1;
		
		String query = "UPDATE book SET title = ?, subtitle = ?, language = ?, "
				+ "price = ?, publication_date = ?, edition = ?, publisher_id = ?, availability = ? "
				+ "WHERE isbn = ?";
		
		book.setPublisher(insertPublisherIfNotExist(book));				
		book.setAuthors(insertAuthorsIfNotExist(book));		
		book.setGenres(insertGenresIfNotExist(book));
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, book.getTitle());
		statement.setString(2, book.getSubtitle());
		statement.setString(3, book.getLanguage());
		statement.setDouble(4, book.getPrice());
		statement.setDate(5, book.getPublicationDate());
		statement.setString(6, book.getEdition());
		statement.setInt(7, book.getPublisher().getId());
		statement.setInt(8, book.getAvailability());
		statement.setString(9, book.getIsbn());
		
		rowsInserted = statement.executeUpdate();
		if (rowsInserted != -1) {
			int bookId = selectOne(book.getIsbn()).getId();
			book.setId(bookId);
			
			deleteRecordBookAuthor(bookId);
			for (Author author : book.getAuthors()) {
				try {
					insertRecordBookAuthor(bookId, author.getId());
				} catch (SQLException e) {
					System.out.println("Skipped Book-Author");
				}
			}
			deleteRecordBookGenre(bookId);
			for (Genre genre : book.getGenres()) {
				try {
					insertRecordBookGenre(bookId, genre.getId());
				} catch (SQLException e) {
					System.out.println("Skipped Book-Genre");
				}
			}
		}
		return rowsInserted;
	}

	@Override
	public int deleteOne(String isbn) throws SQLException {
		
		int rowsAffected = - 1;
		int bookId = selectOne(isbn).getId();
		
		if ( deleteRecordBookAuthor(bookId) != -1 && deleteRecordBookGenre(bookId) != -1) {
			
			String query = "DELETE FROM book WHERE isbn = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, isbn);
			
			rowsAffected = statement.executeUpdate();
		}
		return rowsAffected;
	}
	
	@Override
	public int deleteOne(Book book) throws SQLException {
		return 0;
	}
	
	// Miscellaneous methods
	
	//Publisher
	private boolean checkPublisherNotExists(Publisher publisher) throws SQLException {
		DaoPublisherImp daoPublisher = new DaoPublisherImp();
		return daoPublisher.selectByName(publisher.getName()) == null;
	}
	
	private Publisher getPublisherByName(String publisherName) throws SQLException {
		DaoPublisherImp daoPublisher = new DaoPublisherImp();
		return daoPublisher.selectByName(publisherName);
	}
	
	private Publisher getPublisherById(int publisherId) throws SQLException {
		DaoPublisherImp daoPublisher = new DaoPublisherImp();
		return daoPublisher.selectOne(publisherId);
	}
	
	private Publisher insertPublisherIfNotExist(Book book) throws SQLException {
		if ( checkPublisherNotExists(book.getPublisher()) ) {
			DaoPublisherImp daoPublisher = new DaoPublisherImp();
			daoPublisher.insert(book.getPublisher());
		} 
		return getPublisherByName(book.getPublisher().getName());
	}
	
	//Author
	private boolean checkAuthorNotExists(Author author) throws SQLException {
		DaoAuthorImp daoAuthor = new DaoAuthorImp();
		return daoAuthor.selectOne(author) == null;
	}
	
	private Author getAuthor(Author author) throws SQLException {
		DaoAuthorImp daoAuthor = new DaoAuthorImp();
		return daoAuthor.selectOne(author);
	}
	
	public List<Author> insertAuthorsIfNotExist(Book book) throws SQLException {
		List<Author> authors = new ArrayList<>();
		if (book.getAuthors() != null) {
			for (Author author : book.getAuthors()) {
				if ( checkAuthorNotExists(author) ) {
					DaoAuthorImp daoAuthor = new DaoAuthorImp();
					daoAuthor.insert(author);
				}
				authors.add(getAuthor(author));
			}
		}
		return authors;
	}

	private List<Author> getAuthors(String isbn) throws SQLException {
		
		List<Author> authors = new ArrayList<>();
		String query = "SELECT a.id, a.first_name, a.last_name, a.pseudonym, a.date_of_birth, a.nationality, a.email "
				+ "FROM book AS b "
				+ "INNER JOIN book_author AS bk ON b.id = bk.book_id "
				+ "INNER JOIN author AS a ON a.id = bk.author_id "
				+ "WHERE b.isbn = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, isbn);
		
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			authors.add(new Author(
					result.getInt(1),
					result.getString(2),
					result.getString(3),
					result.getString(4),
					result.getDate(5),
					result.getString(6),
					result.getString(7)
			));
		}
		return authors;
	}
	
	// Genre
	private boolean checkGenreNotExists(String name) throws SQLException {
		DaoGenreImp daoGenre = new DaoGenreImp();
		return daoGenre.selectOne(name) == null;

	}
	
	private Genre getGenre(String name) throws SQLException {
		DaoGenreImp daoGenre = new DaoGenreImp();
		return daoGenre.selectOne(name);
	}
	
	private List<Genre> insertGenresIfNotExist(Book book) throws SQLException {
		List<Genre> genres = new ArrayList<>();
		if (book.getGenres() != null) {
			for (Genre genre : book.getGenres()) {
				if ( checkGenreNotExists(genre.getName()) ) {
					System.out.println(genre);
					DaoGenreImp daoGenre = new DaoGenreImp();
					daoGenre.insert(genre);
				}
				genres.add(getGenre(genre.getName()));
			}
		}
		return genres;
	}

	private List<Genre> getGenres(String isbn) throws SQLException {
		
		List<Genre> genres = new ArrayList<>();
		String query = "SELECT g.id, g.name, g.description "
				+ "FROM book AS b "
				+ "INNER JOIN book_genre AS bg ON b.id = bg.book_id "
				+ "INNER JOIN genre AS g ON g.id = bg.genre_id "
				+ "WHERE b.isbn = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, isbn);
		
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			genres.add(new Genre(
					result.getInt(1),
					result.getString(2),
					result.getString(3)					
			));
		}
		return genres;
		
	}
	
	// Book_Author	
	private int insertRecordBookAuthor(int bookId, int authorId) throws SQLException {
		
		int rowsInserted = -1;
		String query = "INSERT INTO book_author (book_id, author_id) VALUES (?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, bookId);
		statement.setInt(2, authorId);
		
		rowsInserted = statement.executeUpdate();
		return rowsInserted;
	}
	
	private int deleteRecordBookAuthor(int bookId) throws SQLException {
		
		int rowsAffected = -1;
		String query = "DELETE FROM book_author WHERE book_id = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, bookId);
		
		rowsAffected = statement.executeUpdate();
		return rowsAffected;
	}
	
	// Book_Genre
	private int insertRecordBookGenre(int bookId, int genreId) throws SQLException {
		
		int rowsInserted = -1;
		String query = "INSERT INTO book_genre (book_id, genre_id) VALUES (?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, bookId);
		statement.setInt(2, genreId);
		
		rowsInserted = statement.executeUpdate();
		return rowsInserted;
	}
	
	private int deleteRecordBookGenre(int bookId) throws SQLException {
		
		int rowsAffected = -1;
		String query = "DELETE FROM book_genre WHERE book_id = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, bookId);
		
		rowsAffected = statement.executeUpdate();
		return rowsAffected;
	}
}