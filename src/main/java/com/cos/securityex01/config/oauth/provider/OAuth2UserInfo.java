package com.cos.securityex01.config.oauth.provider;

//구글 네이버 카카오 페이스북은 이 타입으로 통일 함.
//OAuth 2.0 제공자들 마다 응답해주는 속성값이 달라서 공통으로 만들어 준다.

public interface OAuth2UserInfo {
	String getProviderId();
	String getProvider();
	String getEmail();
	String getName();

}
