package com.cos.securityex01.config.oauth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.securityex01.config.auth.PrincipalDetails;
import com.cos.securityex01.config.oauth.provider.FacebookUserInfo;
import com.cos.securityex01.config.oauth.provider.GoogleUserInfo;
import com.cos.securityex01.config.oauth.provider.OAuth2UserInfo;
import com.cos.securityex01.model.User;
import com.cos.securityex01.repository.UserRepository;

//구글 로그인하면 여기로 온다.

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	//userRequest는 code를 받아서 access token을 응답 받은 객체	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
	OAuth2User oAuth2User = super.loadUser(userRequest); //코드+토큰+회원정보을 다 담아주는 마법의 로드! //google의 회원 프로필 정보
	// oAuth2User를 어디에 담아서 무엇을 리턴하면 될까요?
	// 결국 리턴을 principalDetails를 리턴해줄꺼야
	// 1. PrincipalDetails에 OAuth2User정보를 집어 넣어 준다.
	// 2. PrincipalDetails를 리턴한다.
	
	System.out.println("oAuth2User : " + oAuth2User); //token을 통해 응답받은 회원정보
	System.out.println("userRequest : " + userRequest.getAccessToken().getTokenValue());
	System.out.println("userRequest ClientRegistration: "+ userRequest.getClientRegistration()); // code를 통해 구성한 정보, 내 스프링서버가 들고 있는 메모리 서버
	
		
		// Name : google 아이디 값. 그러면 구글이랑 페이스북이랑 충돌날 수도 있음.
		// 프로바이더 : google
		// 프로바이더 아이디 : 
				return processOAuth2User(userRequest, oAuth2User);
	}
	
	
	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
		} else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
			oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
		} else {
			System.out.println("우리는 구글과 페이스북만 지원해요."); // 원래는 exception 던져야 한다.
		}
		
		Optional<User> userOptional = userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());
		
		User user;
		if(userOptional.isPresent()) {
			user = userOptional.get();
		}else {
			user = User.builder()
					.username(oAuth2UserInfo.getProvider()+"_"+oAuth2UserInfo.getProviderId())
					.email(oAuth2UserInfo.getEmail())
					.role("ROLE_ROLE")
					.provider(oAuth2UserInfo.getProvider())
					.providerId(oAuth2UserInfo.getProviderId())
					.build();
			userRepository.save(user); //
		}
		
		//일반적으로 로그인 할 때 유저 정보는  User
		//1. OAuth2로 로그인 할 때 유저 정보는 attributes <- 이거 구성해야함		
		//2. DB에 이사람있나?
		//3. 있으면  -> 그 사람의 유저정보를 Update하면 된다.
		//4. 없으면  -> insert해야함.
		//return PrincipalDetails()
		
	
		return new PrincipalDetails(user, oAuth2User.getAttributes()); // 이렇게 리턴하면 세션이 만들어진다.(일반적인 로그인
	}

}
