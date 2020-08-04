package com.cos.securityex01.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.securityex01.model.User;

import lombok.Data;



// Authentication객체에 저장할 수 있는 유일한 타입입니다. 그리고 Authentication를 세션에 저장합니다.

public class PrincipalDetails implements UserDetails, OAuth2User{

		private User user;	//이렇게 콤포지션
		private Map<String, Object> attributes;
		
		public PrincipalDetails(User user) {
			super();
			this.user = user;
		}
		
		public User getUser() {
			return user;
		}

		//OAuth2.0 로그인 사용
		public PrincipalDetails(User user, Map<String, Object> attributes) {
			this.user = user;
			this.attributes = attributes;
		}
		

	@Override
	public String getPassword() {	
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; //이 계정 만료?
	}

	@Override
	public boolean isAccountNonLocked() {		
		return true; //계정 잠겼나?
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; //비밀번호 너무 오래되서 
	}

	@Override
	public boolean isEnabled() { //계정 활성화 되었는지
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add( () -> (user.getRole()));
		
		return authorities; //권한뭐야?
	}

	//리소스서버로 부터 받는 회원정보들
	
	@Override
	public Map<String, Object> getAttributes() { //OAuth2User타입으로 회원정보
		return attributes;
	}

	//User의 PrimaryKey
	@Override
	public String getName() {
		return user.getId()+"";
	}
	

}
