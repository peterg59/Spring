package employee;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import employee.controller.MyController;

public class Main {

	public static void main(String[] args) {
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		MyController myController = context.getBean(MyController.class);
		myController.getInt();
		myController.delete(15);
		//myController.delete(-1);
		myController.save();
		context.close();
	}
}
