package com.cos.securityex01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.securityex01.model.User;

//JpaRepository를 상속하면 자동 컴포넌트 스캔 됨.
public interface UserRepository extends JpaRepository<User, Integer>{ //Wrapper class적어야 한다. //Ioc하는것 같은거다
    
	// Jpa Naming전략이 있어요 - 함수만들면 함수이름에 맞게 쿼리가 작동됨
	//첫번째 파라미터라서 1이 붙음, 대문자 필수!
	
	//SELECT *FROM user WHERE username =1?	
	User findByUsername(String username);
	
	///SELECT * FROM user WHERE username = 1? and Password = 2? (String username, String password)
	//User findByUsernameAndPassword(String username, String password)
	
	//@Query(value="select*from user", nativeQuery=true)
	//User find 마음대로
	//Jpa Query Creation
}
