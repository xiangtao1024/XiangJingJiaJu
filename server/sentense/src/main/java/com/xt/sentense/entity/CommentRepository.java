package com.xt.sentense.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	Page<Comment> findBySentenseId(Long sentenseId, Pageable pageable);
	Page<Comment> findByUserId(Long userId, Pageable pageable);
}
