package employee;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.dao.DataAccessException;

import employee.controller.MyController;
import employee.repository.Book;
import employee.repository.BookDAO;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		BookDAO bookDao = context.getBean(BookDAO.class);
		Book book = new Book(5, "Lion", "Pierre", 150);
		Book book2 = new Book(11, "Rat", "Pierre", 140);
		Book book3 = new Book(12, "Aigle", "Pierre", 130);
		Book book4 = new Book(13, "Renard", "Pierre", 180);
		
		bookDao.printBookstoCsvFile();
		/*
		 * MyController myController = context.getBean(MyController.class);
		 * myController.getInt(); myController.delete(15); //myController.delete(-1);
		 * myController.save(); context.close();
		 */
	}
}
