# 스프링 시큐리티 OAuth V2

#### 페이스북, 구글 로그인 및 기본 시큐리티 연동

## 스프링 시큐리티 기본 V1 참고

#### https://github.com/Moonseonhyeon/SpringBoot-Security-Basic-V1

## JPA method names 참고

![image](https://user-images.githubusercontent.com/55937799/89255549-f8ef7780-d65c-11ea-8f05-0b32356bf67f.png)

## Google, Facebook 로그인 완료

![image](https://user-images.githubusercontent.com/55937799/89263676-a5852580-d66c-11ea-896c-d12fbff56509.png)

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
