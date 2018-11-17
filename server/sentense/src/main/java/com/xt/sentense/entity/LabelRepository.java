package com.xt.sentense.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long>{
	public Label findByName(String name);
}
