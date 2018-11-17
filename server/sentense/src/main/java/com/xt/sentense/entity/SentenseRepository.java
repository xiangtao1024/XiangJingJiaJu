package com.xt.sentense.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SentenseRepository extends JpaRepository<Sentense, Long>{
	Page<Sentense> findByCategoryId(Long categoryId, Pageable pageable);
	Page<Sentense> findByUserId(Long userId, Pageable pageable);
	Page<Sentense> findByScenesLike(String scenes, Pageable pageable);
	Page<Sentense> findByLabelsLike(String labels, Pageable pageable);
	
	@Query(value="select *from tb_sentense where scenes like :scene", nativeQuery=true)
	Page<Sentense> searchByScene(@Param("scene")String scene, Pageable pageable);
	
	@Query(value="select *from tb_sentense where labels like :label", nativeQuery=true)
	Page<Sentense> searchByLabel(@Param("label")String label, Pageable pageable);
	
	@Query(value="select *from tb_sentense where content like :content", nativeQuery=true)
	Page<Sentense> searchByContent(@Param("content")String content, Pageable pageable);
	
	@Query(value="select count(*) from tb_sentense where user_id=:userId", nativeQuery=true)
	int countByUserId(@Param("userId")Long userId);
}
