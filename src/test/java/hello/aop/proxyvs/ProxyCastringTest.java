package hello.aop.proxyvs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyCastringTest {
	
	@Test
	void jdkProxy () throws Exception{
		MemberServiceImpl target = new MemberServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setProxyTargetClass(false); // jdk 동적 프록시

		// 프록시를 인터페이스로 캐스팅 성공
		MemberService memberServiceProxy = (MemberService)proxyFactory.getProxy();

		// jdk 동적 프록시를 구현 클래스로 캐스팅 시도 실패 , ClassCastException 발생
		// MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
		Assertions.assertThrows(ClassCastException.class, () -> {
			MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
		});
	}

	@Test
	void cglibProxy () throws Exception{
		MemberServiceImpl target = new MemberServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setProxyTargetClass(true); // CGLIB 동적 프록시
		// 프록시를 인터페이스로 캐스팅 성공
		MemberService memberServiceProxy = (MemberService)proxyFactory.getProxy();
		// CGLIB 동적 프록시를 구현 클래스로 캐스팅 시도 성공
		MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
	}
}
