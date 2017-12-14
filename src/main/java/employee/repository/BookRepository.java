package employee.repository;

import java.util.*;

public class BookRepository {

	List<Book> books;
	
	public BookRepository() {
		books = new ArrayList<>();
	}

	public List<Book> getAll() {
		return books;
	}

	public Book getById(int id) {
		for (Book b : books) {
			if (b.getId() == id) {
				return b;
			}
		}
		return null;
	}

	public void delete(Book b) {
		books.remove(b);
	}

	public void add(Book b) {
		books.add(b);
	}

	public void update(Book b) {
		if(books.contains(b)) {
			books.remove(b);
			books.add(b);
		}
	}
}
