package hello.aop.internalcall.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
public class CallLogAspect {

	@Before("execution(* hello.aop.internalcall..*.*(..))")
	public void doLog(JoinPoint joinPoint) {
		log.info("aop={}", joinPoint.getSignature());

	}
}
