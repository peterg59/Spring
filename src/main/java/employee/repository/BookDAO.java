package employee.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAO {
	
	@PersistenceContext
	EntityManager em;
	private JdbcTemplate jdbcTemplate;
	private String sql = "SELECT * FROM book WHERE CATEGORY=? AND AUTHOR=?";

	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	public int rowCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from book", Integer.class);
	}

	/*@Transactional
	public void getAll() {
		List<Book> books = this.jdbcTemplate.query("select * from book", new BookMapper());
		for (Book b : books) {
			System.out.println(b);
		}
	}*/

	@Transactional
	public void getAllBooksByAuthor() {
		BookCallbackHandler booksByAuthor = new BookCallbackHandler();
		this.jdbcTemplate.query("select * from book", booksByAuthor);
		List<Book> books = booksByAuthor.filterBooksByAuthor("Pierre");
		for (Book b : books) {
			System.out.println(b);
		}
	}

	@Transactional
	public void getDataToCsv() {
		BookListResultSet booksToCsv = new BookListResultSet();
		this.jdbcTemplate.query("select * from book", booksToCsv);

	}

	@Transactional
	public void printBookstoCsvFile() throws IOException {
		BookListResultSet booksToCsv = new BookListResultSet();
		StringBuffer sbf = new StringBuffer();
		BufferedWriter bwr;
		this.jdbcTemplate.query("select * from book", booksToCsv);
		List<Book> books = booksToCsv.getBooks();
		for (Book b : books) {
			sbf.append(books);
			sbf.append(System.getProperty("line.separator"));
		}

		bwr = new BufferedWriter(new FileWriter(new File("C:\\Users\\pguyard\\Documents\fileTp.csv")));

		// write contents of StringBuffer to a file
		bwr.write(sbf.toString());

		// flush the stream
		bwr.flush();

		// close the stream
		bwr.close();

		System.out.println("Content of StringBuffer written to File.");
	}

	@Transactional
	public void insert(Book book, Book book2, Book book3) throws InterruptedException {
		getAll();
		this.jdbcTemplate.update(
				"insert into book (id, nb_pages, author, title, publication_date) values (?, ?, ?, ?, ?)", book.getId(),
				book.getNbrPages(), book.getAuthor(), book.getTitle(), new Date());
		Thread.sleep(5000);
		this.jdbcTemplate.update(
				"insert into book (id, nb_pages, author, title, publication_date) values (?, ?, ?, ?, ?)",
				book2.getId(), book2.getNbrPages(), book2.getAuthor(), book2.getTitle(), new Date());
		Thread.sleep(5000);
		this.jdbcTemplate.update(
				"insert into book (id, nb_pages, author, title, publication_date) values (?, ?, ?, ?, ?)",
				book3.getId(), book3.getNbrPages(), book3.getAuthor(), book3.getTitle(), new Date());
		Thread.sleep(5000);
		getAll();
	}

	protected Session getSession() {
		return em.unwrap(Session.class);
	}

	public void persist(Object entity) {
		em.persist(entity);
	}
	
	public void delete(Object entity) {
		em.remove(entity);
	}
	
	public void add(Object entity) {
		em.persist(entity);
	}
	
	public void update(Object entity) {
		em.merge(entity);
	}
	
	public List<Book> getAll() {
		return em.createQuery("select b from Book b",Book.class).getResultList();
	}

	/*public void update(Book book) {
		this.jdbcTemplate.update("update book set author = ? where id = ?", book.getAuthor(), book.getId());
	}

	public void delete(Book book) {
		this.jdbcTemplate.update("delete from book where id = ?", Long.valueOf(book.getId()));
	}*/

	private static final class BookMapper implements RowMapper<Book> {
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			Book book = new Book();
			book.setTitle(rs.getString("title"));
			book.setNbrPages(rs.getInt("nb_pages"));
			book.setAuthor(rs.getString("author"));
			book.setDate((rs.getDate("publication_date")));
			return book;
		}
	}
}
