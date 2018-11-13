package com.xt.sentense.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	public List<User> findByPhone(String phone);
}
