package employee.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.jdbc.core.RowCallbackHandler;

public class BookCallbackHandler implements RowCallbackHandler {
	
	private List<Book> books;
	private List<String> authors;
	
	public BookCallbackHandler() {
		books = new ArrayList<Book>();
		authors = new ArrayList<String>();
	}
	
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		Book book = new Book();
		book.setTitle(rs.getString("title"));
		book.setNbrPages(rs.getInt("nb_pages"));
		book.setAuthor(rs.getString("author"));
		book.setDate(rs.getDate("publication_date"));
		books.add(book);
		authors.add(book.getAuthor());
	}
	
	public List<Book> filterBooksByAuthor(String author) {
		List<Book> bookByAuthor = new ArrayList<>();
		for(Book b : books) {
			if(b.getAuthor().equals(author)) {
				bookByAuthor.add(b);
			}
		}
		return bookByAuthor;
	}
	
	public List<Book> getBooks() {
		return books;
	}
	
	public List<String> getAuthors() {
		return authors;
	}

}
