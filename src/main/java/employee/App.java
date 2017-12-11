package employee;

import org.springframework.context.annotation.*;
@EnableAspectJAutoProxy
@Configuration
@ComponentScan
public class App {
	
	public App() {
		System.out.println("Start created");
	}
}
