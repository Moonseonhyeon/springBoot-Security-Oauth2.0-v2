package com.cos.securityex01.config.oauth.provider;

import java.util.Map;


//내가 구글만 할거면 이 파일 만들 필요 없다.
public class GoogleUserInfo implements OAuth2UserInfo{
	
	private Map<String, Object> attributes;
	
	public GoogleUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getProviderId() {	
		return (String)attributes.get("sub");
	}

	@Override
	public String getProvider() {		
		return (String)attributes.get("google");
	}

	@Override
	public String getEmail() {
		return (String)attributes.get("email");
	}

	@Override
	public String getName() {
		return (String)attributes.get("name");
	}

}