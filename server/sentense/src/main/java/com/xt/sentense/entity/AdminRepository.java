package com.xt.sentense.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	public List<Admin> findByUsername(String username);
}
