package com.cos.securityex01.test;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.securityex01.model.User;
import com.cos.securityex01.repository.UserRepository;

@RestController
public class OptionalControllerTest {

	@Autowired UserRepository userRepository;
	
	
	@GetMapping("/test/user/{id}")
	public User 옵셔널_유저찾기(@PathVariable int id) {
		
		//JPA는 기본적으로 CRUD를 들고 있음 findById 들어가면 Optional 적혀있음
		
		  Optional<User> userOptional = userRepository.findById(id); 
		  
		  	//1. get을 쓸 수있는 유일한 방법인데 쓸 필요는 없다.
		/*
		 * User user; if(userOptional.isPresent()) { user = userOptional.get(); } else {
		 * user = new User(); }
		 */
		 
		
		  //2. orElseGet 가장 많이씀 
		/*
		 * User user = userRepository.findById(id).orElseGet(() -> { return
		 * User.builder().id(4).username("아무개").email("아무개@naver.com").build(); });
		 */
		 
		
		//3.orElseThrow
		
		  User user = userRepository.findById(id).orElseThrow(() -> { return new
		  NullPointerException("없어 값");		 
		  });
					
		return user;
	}
}
