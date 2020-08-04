# 스프링 시큐리티 OAuth V2

#### 페이스북, 구글 로그인 및 기본 시큐리티 연동

## 스프링 시큐리티 기본 V1 참고

#### https://github.com/Moonseonhyeon/SpringBoot-Security-Basic-V1

## JPA method names 참고

![image](https://user-images.githubusercontent.com/55937799/89255549-f8ef7780-d65c-11ea-8f05-0b32356bf67f.png)

## Google, Facebook, Naver 로그인 완료

![image](https://user-images.githubusercontent.com/55937799/89282512-9ad78a00-d686-11ea-82ca-afe6e8f3d757.png)

## application.yml 설정

```
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: UTF-8
      enabled: true
      force: true

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/security?serverTimezone=Asia/Seoul
    username: cos
    password: cos1234

  mvc:
    view:
      prefix: /templates/
      suffix: .mustache

  jpa:
    hibernate:
      ddl-auto: update #create update none
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true

  security:
    oauth2:
      client:
        registration:
          google: # /oauth2/authorization/google 이 주소를 동작하게 한다.
            client-id: 머시기
            client-secret: 머시기
            scope:
              - email
              - profile

          facebook:
            client-id: 머시기
            client-secret: 머시기
            scope:
              - email
              - public_profile

          # 네이버는 OAuth2.0 공식 지원대상이 아니라서 provider 설정이 필요하다.
          # 요청주소도 다르고, 응답 데이터도 다르기 때문이다.
          naver:
            client-id: 머시기
            client-secret: 머시기
            scope:
              - name
              - email
              - profile_image
            client-name: Naver # 클라이언트 네임은 구글 페이스북도 대문자로 시작하더라.
            authorization-grant-type: authorization_code
            redirect-uri: http://localhost:8080/login/oauth2/code/naver

        provider:
          naver:
            authorization-uri: https://nid.naver.com/oauth2.0/authorize
            token-uri: https://nid.naver.com/oauth2.0/token
            user-info-uri: https://openapi.naver.com/v1/nid/me
            user-name-attribute: response # 회원정보를 json의 response 키값으로 리턴해줌.

```

```
Spring Boot DevTools
Lombok
Spring Data JPA
MySQL Driver
Spring Security
Spring Web
```

###

```maven mustache tamplate
<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-mustache</artifactId>
</dependency>
```
