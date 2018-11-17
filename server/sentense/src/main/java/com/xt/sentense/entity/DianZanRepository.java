package com.xt.sentense.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DianZanRepository extends JpaRepository<DianZan, Long>{
	@Query(value="select count(*) from tb_dian_zan where sentense_id=:sentenseId")
	int countBySentenseId(@Param("sentenseId")Long sentenseId);
	public List<DianZan> findByUserIdAndSentenseId(Long userId, Long sentenseId);
}
