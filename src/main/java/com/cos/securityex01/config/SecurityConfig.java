package com.cos.securityex01.config;

import org.aspectj.weaver.BCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.securityex01.config.oauth.PrincipalOauth2UserService;

@Configuration //Ioc 빈(bean)을 등록 - 인스턴스
@EnableWebSecurity //시큐리티 필터 체인안에 항목들을 관리 시작
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) //특정 주소 접근시 권한 및 인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	
	@Bean //메서드를 IoC한다.
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable(); //csrf-token비활성화
		http.authorizeRequests()
		.antMatchers("/user/**").authenticated()	//인증?
		//.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') ")  //권한 - hasAnyRole이라는 함수 도 있다. //or 자리에 and도 가능.
		//.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') ") 
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") 
		.anyRequest().permitAll()
		//이부분은 내가 만든 로그인 페이지
		.and()		
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/loginProc") //요청을 시큐리티가 낚아채서 Authentication Manager로 타게 하는 설정이다.
			.defaultSuccessUrl("/") // 여기로 시큐리티가 리다이렉션으로 준다.
			// .configure(); // 정적인 파일은 ignore 알아서 공부해보자.
			//이 부분은 구글 로그인 페이지  // 어떤 인증과 권한이 있어야하지
		.and()		//구글로그인 누르면 얘가 낚아챔.
			.oauth2Login()
			.loginPage("/login")
			.userInfoEndpoint()
			.userService(principalOauth2UserService);		
		
			;
		
		
		
	}
	
}
