package com.xt.sentense.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LabelRepository extends JpaRepository<Label, Long>{
	public Label findByName(String name);
	
	@Query(value="select *from tb_label where name like :name", nativeQuery=true)
	List<Label> findByNameLike(@Param("name")String name);
	
}
