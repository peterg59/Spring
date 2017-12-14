package employee.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class BookListResultSet implements ResultSetExtractor<String> {
	
	private List<Book> books;
	
	public BookListResultSet() {
		books = new ArrayList<Book>();
	}
	
	public String extractData(ResultSet rs) throws SQLException, DataAccessException {
		StringBuffer sbf = new StringBuffer();
		while(rs.next()) {
			sbf.append(rs.getString("title"));
			sbf.append(";");
			sbf.append(rs.getString("nb_pages"));
			sbf.append(";");
			sbf.append(rs.getString("author"));
			sbf.append(";");
			sbf.append(rs.getString("publication_date"));
			sbf.append("\n");
		}
		return sbf.toString();
	}
	
	public List<Book> getBooks() {
		return books;
	}

}
