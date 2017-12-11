package employee;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogInvocation {

	@AfterReturning(pointcut = "execution(* employee.controller.MyController.get*())", returning = "retVal")
	public void afterReturningAdvice(JoinPoint jp, Object retVal) {
		System.out.println("Method Signature: " + jp.getSignature());
		System.out.println("Returning:" + retVal.toString());
	}

	@Before("execution(* employee.*.*.*())")
	public void beforeAdvice(JoinPoint jp) {
		System.out.println("Before : Method Signature: " + jp.getSignature());
	}

	@AfterThrowing(pointcut = "execution(* employee.controller.MyController.delete(..))", throwing = "error")
	public void afterThrowingAdvice(JoinPoint jp, Throwable error) {
		System.out.println("Method Signature: " + jp.getSignature());
		System.out.println("Exception: " + error);
	}

	@Around("execution(* employee.controller.MyController.save())")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("Around advice");
		long before = System.currentTimeMillis();
		Object retVal = proceedingJoinPoint.proceed();

		long after = System.currentTimeMillis();

		long total = after - before;

		System.out.println("durée d'exécution : " + total);
		return retVal;
	}
}
