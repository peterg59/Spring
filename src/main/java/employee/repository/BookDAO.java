package employee.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	public int rowCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from book", Integer.class);
	}
	
	@Transactional
	public void getAll() {
		List<Book> books = this.jdbcTemplate.query("select * from book", new BookMapper());
		for(Book b : books) {
			System.out.println(b);
		}
	}
	
	@Transactional
	public void insert(Book book, Book book2, Book book3) throws InterruptedException {
		getAll();
		this.jdbcTemplate.update("insert into book (id, nb_pages, author, title, publication_date) values (?, ?, ?, ?, ?)",book.getId(),book.getNbrPages(),book.getAuthor(), book.getTitle(), new Date());
		Thread.sleep(5000);
		this.jdbcTemplate.update("insert into book (id, nb_pages, author, title, publication_date) values (?, ?, ?, ?, ?)",book2.getId(),book2.getNbrPages(),book2.getAuthor(), book2.getTitle(), new Date());
		Thread.sleep(5000);
		this.jdbcTemplate.update("insert into book (id, nb_pages, author, title, publication_date) values (?, ?, ?, ?, ?)",book3.getId(),book3.getNbrPages(),book3.getAuthor(), book3.getTitle(), new Date());
		Thread.sleep(5000);
		getAll();
	}
	
	public void update(Book book) {
		this.jdbcTemplate.update("update book set author = ? where id = ?", book.getAuthor(), book.getId());
	}
	
	
	public void delete(Book book) {
		this.jdbcTemplate.update("delete from book where id = ?", Long.valueOf(book.getId()));
	}
	
	private static final class BookMapper implements RowMapper<Book> {
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			Book book = new Book();
			book.setTitle(rs.getString("title"));
			book.setNbrPages(rs.getInt("nb_pages"));
			book.setAuthor(rs.getString("author"));
			book.setDate( (rs.getDate("publication_date")));
			return book;
		}
	}
}
