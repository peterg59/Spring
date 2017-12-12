package employee.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class Book {
	
	private String title, author;
	private Integer id, nbrPages;
	private Date date;
	
	public Book(int id, String title, String author, int nbrPages) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.nbrPages = nbrPages;
	}
	
	public Book() {
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}
	
	public Integer getNbrPages() {
		return nbrPages;
	}
	
	public void setNbrPages(Integer nbrPages) {
		this.nbrPages = nbrPages;
	}
	
	@Override
	public String toString() {
		return getAuthor() + " " + getTitle() + " " + getNbrPages() + " " + getDate();
	}
}
