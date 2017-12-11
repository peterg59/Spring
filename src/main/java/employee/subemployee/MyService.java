package employee.subemployee;

import org.springframework.stereotype.*;

@Service
public class MyService {
	
	public MyService() {
		System.out.println("hello");
	}
	
	public void doTheJob(String job) {
		System.out.println(job);
	}
}
