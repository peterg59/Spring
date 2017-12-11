package employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import employee.subemployee.MyService;

@Controller
public class MyController {
	
	@Autowired
	private MyService myService;
	
	public void doTheJob(String job) {
		myService.doTheJob(job);
	}

	public MyService getMyService() {
		return myService;
	}

	public void setMyService(MyService myService) {
		this.myService = myService;
	}
	
	public int getInt() {
		return 1;
	}
	
	public void delete(int n) {
		if(n > 0)
			System.out.println("Tout est bon");
		else 
			throw new IllegalArgumentException("L'exception est levee");
	}
	
	public void save() {
		for(int i = 0 ; i< 100000000; i++);
	}
}
