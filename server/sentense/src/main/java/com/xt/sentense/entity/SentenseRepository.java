package com.xt.sentense.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SentenseRepository extends JpaRepository<Sentense, Long>{
	Page<Sentense> findByCategoryId(Long categoryId, Pageable pageable);
	Page<Sentense> findByUserId(Long userId, Pageable pageable);
	Page<Sentense> findByScenesLike(String scenes, Pageable pageable);
	Page<Sentense> findByLabelsLike(String labels, Pageable pageable);
}
