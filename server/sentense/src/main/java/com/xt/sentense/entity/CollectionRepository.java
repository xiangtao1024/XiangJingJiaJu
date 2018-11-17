package com.xt.sentense.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CollectionRepository extends JpaRepository<Collection, Long>{
	Page<Collection> findByUserId(Long userId, Pageable pageable);
	@Query(value="select count(*) from tb_collection where user_id=:userId")
	int countByUserId(@Param("userId")Long userId);
	
	List<Collection> findByUserIdAndSentenseId(Long userId, Long sentenseId);
}
