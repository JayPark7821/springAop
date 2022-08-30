package hello.aop.pointcut;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import hello.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;

/**
 * spring.aop.proxy-target-class=true CGLIB
 * spring.aop.proxy-target-class=false jdk동적 프록시
 */
@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
// @SpringBootTest(properties = "spring.aop.proxy-target-class=false")
@SpringBootTest(properties = "spring.aop.proxy-target-class=true")
public class ThisTargetTest {

	@Autowired
	MemberService memberService;

	@Test
	void success() throws Exception {
		log.info("memberService proxy={}", memberService.getClass());
		memberService.hello("helloA");
	}

	@Slf4j
	@Aspect
	static class ThisTargetAspect {

		// this. target 부모타입은 허용 한다.
		@Around("this(hello.aop.member.MemberService)")
		public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[this-interface] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}

		// this. target 부모타입은 허용 한다.
		@Around("target(hello.aop.member.MemberService)")
		public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[target-interface] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}

		@Around("this(hello.aop.member.MemberServiceImpl)")
		public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[this-Impl] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}

		@Around("target(hello.aop.member.MemberServiceImpl)")
		public Object doTarget(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[target-Impl] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}

	}

}
