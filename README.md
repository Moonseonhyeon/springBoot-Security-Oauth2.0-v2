#스프링 시큐리티 기본 V1

### MySQL DB 및 사용자 생성

```sql
create user 'cos'@'%' identified by 'cos1234';
GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
create database security;
use security;
```

### SecurityConfig.java 설정 방법

```
// protected void configure(HttpSecurity http) 함수 내부에 권한 설정법
.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
```

### 컨트롤러의 함수에 직접 권한 설정 하는 방법

```java
//protected void configure(HttpSecurity http) 함수내부에 특정 설정법, 특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성화 SecurityConfig.java에  설정
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)

### 컨트롤러 내부에 어노테이션거는 방법
@PostAuthorize("hasRole('ROLE_MANAGER')")
@PreAuthorize("hasRole('ROLE_MANAGER')")
@Secured("ROLE_MANAGER")
```

###

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
