package com.xt.sentense.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xt.sentense.entity.Category;
import com.xt.sentense.entity.CategoryRepository;
import com.xt.sentense.entity.Sentense;
import com.xt.sentense.entity.SentenseRepository;
import com.xt.sentense.entity.User;
import com.xt.sentense.entity.UserRepository;
import com.xt.sentense.vo.PageList;
import com.xt.sentense.vo.SentenseVo;

@Service
public class SentenseService {
	@Autowired
	private SentenseRepository sentenseRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserRepository  userRepository;
	
	public PageList finds(int page, int size){
		Pageable p = PageRequest.of(page, size);
		PageList datas = new PageList(sentenseRepository.findAll(p));
		return bindOtherData(datas);
	}
	public PageList findByCatgegoryId(int page, int size, Long categoryId){
		Pageable p = PageRequest.of(page, size);
		PageList datas = new PageList(sentenseRepository.findByCategoryId(categoryId, p));
		return bindOtherData(datas);
	}
	
	public PageList findByUserId(int page, int size, Long userId){
		Pageable p = PageRequest.of(page, size);
		PageList datas = new PageList(sentenseRepository.findByUserId(userId, p));
		return bindOtherData(datas);
	}
	
	/**
	 * 绑定其他数据，比如分类，用户评论数等待
	 * @param page
	 * @param size
	 * @param categoryId -1 表示不需要判断
	 * @param userId -1 表示不需要判断
	 * @return
	 */
	private PageList bindOtherData(PageList datas){
		List<Sentense> sentenses = (List<Sentense>) datas.getContent();
		List<SentenseVo> svs = new ArrayList<>();
		for(int i = 0; i < sentenses.size(); i ++){
			Sentense se = sentenses.get(i);
			Optional<Category> category = categoryRepository.findById(se.getCategoryId());
			Optional<User> user = userRepository.findById(se.getUserId());
			SentenseVo sv = SentenseVo.NEW(se);
			sv.setCategory(category.isPresent()?category.get():null);
			sv.setUser(user.isPresent()?user.get(): null);
			svs.add(sv);
		}
		datas.setContent(svs);
		return datas;
	}
	
	public SentenseVo findById(Long id){
		Optional<Sentense> u = sentenseRepository.findById(id);
		SentenseVo sv = SentenseVo.NEW(u.isPresent()?u.get() : null);
		return sv;
	}
}
