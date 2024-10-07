package aop.demo1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SampleAdvice {

	// 아래 포인트컷 이름은 myPointcut
	@Pointcut("execution(* aop.demo1.*ServiceImpl.*(..))")
	private void myPointcut() {}
	
	@Before("myPointcut()") //when + where
	public void logging(JoinPoint joinPoint) { // what
		// 현재 실행중인 메소드명 출력하기
		// 현재 실행중인 메소드가 전달받은 매개변수값 출력하기
		// 공통기능이 적용된 대상 메소드의 이름을 출력하기
		String methodName = joinPoint.getSignature().getName();
		System.out.println("실행된 메소드: " + methodName);
		
		// 공통기능이 적용된 대상 메소드의 매개변수 값 
		Object[] args = joinPoint.getArgs();
		System.out.println("매개변수: ");
		if (args.length > 0) {
			for (Object arg : args) {
				System.out.println(" - " + arg);
			}
		} else {
			System.out.println("- 없음.");
		}
	}
	
	@Around("execution(* aop.demo1.*ServiceImpl.*(..))")
	public Object runningTimeCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		
		System.out.println("Around Advice: 대상 메소드 실행 전 코드");
		long startTime = System.currentTimeMillis();
		
		Object returnValue = joinPoint.proceed(); // 대상 메소드 실행
		System.out.println("Around Advice: 대상 메소드 실행 후 코드 실행");
		long endTime = System.currentTimeMillis();
		
		long runningTime = endTime - startTime;
		System.out.println("실행소요시간: " + runningTime + "밀리초");
		
		return returnValue;
	}
}
