package hello.aop.pointcut;

import hello.aop.order.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberService.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod () throws Exception {
        // execution(* ..pacake..Class..)
        //public abstract java.lang.String hello.aop.order.aop.member.MemberService.hello(java.lang.String)
        log.info("helloMethod={}", helloMethod);
    }
}
