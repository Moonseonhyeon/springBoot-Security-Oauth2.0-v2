package com.cos.securityex01.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

//ORM - Object Relation Mapping

@Data
@Entity // Jpa가 스캔하다가 이 어노테이션을 찾으면 연결된 데이터베이스에 이 모델을 보고 테이블을 만들어줌.
public class User {
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //numbering 전략 - primary key에 auto increament
	private int id;
	private String username;
	private String password;
	private String email;
	private String role; //회원가입할 때는 user권한 //ROLE_USER, ROLE_ADMIN
	@CreationTimestamp//나중에는 localdate
	private Timestamp createDate;

}
