package com.xt.sentense.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import org.apache.logging.log4j.util.Strings;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xt.sentense.entity.Category;
import com.xt.sentense.entity.CategoryRepository;
import com.xt.sentense.entity.CollectionRepository;
import com.xt.sentense.entity.DianZanRepository;
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
	@Autowired
	private DianZanRepository dianZanRepository;
	@Autowired
	private CollectionRepository collectionRepository;
	
	public PageList finds(int page, int size){
		Sort sort = new Sort(Direction.DESC, "createTime");
		Pageable p = PageRequest.of(page, size, sort);
		PageList datas = new PageList(sentenseRepository.findAll(p));
		return bindOtherData(datas);
	}
	
	public PageList search(int page, int size, String scene, String label, String content){
		Sort sort = new Sort(Direction.DESC, "create_time");
		Pageable p = PageRequest.of(page, size, sort);
		PageList datas = null;
		
		if(!Strings.isEmpty(scene)){
			System.out.println("scene: " + scene);
			datas = new PageList(sentenseRepository.searchByScene("%"+ scene + "%", p));
		}else if(!Strings.isEmpty(label)){
			System.out.println("label: " + label);
			datas = new PageList(sentenseRepository.searchByLabel("%"+ label + "%", p));
		}else if(!Strings.isEmpty(content)){
			System.out.println("content: " + content);
			datas = new PageList(sentenseRepository.searchByContent("%"+ content + "%", p));
		}
		if(datas == null){
			return null;
		}
		return bindOtherData(datas);
	}
	
	public PageList findByCatgegoryId(int page, int size, Long categoryId){
		Sort sort = new Sort(Direction.DESC, "createTime");
		Pageable p = PageRequest.of(page, size, sort);
		PageList datas = new PageList(sentenseRepository.findByCategoryId(categoryId, p));
		return bindOtherData(datas);
	}
	
	public PageList findByUserId(int page, int size, Long userId){
		Sort sort = new Sort(Direction.DESC, "createTime");
		Pageable p = PageRequest.of(page, size, sort);
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
		if(u.isPresent()){
			Sentense se = u.get();
			Optional<Category> category = categoryRepository.findById(se.getCategoryId());
			Optional<User> user = userRepository.findById(se.getUserId());
			SentenseVo sv = SentenseVo.NEW(se);
			sv.setCategory(category.isPresent()?category.get():null);
			sv.setUser(user.isPresent()?user.get(): null);
			return sv;
		}else{
			return null;
		}
	}
	
	public void addComment(Long sentenseId){
		Optional<Sentense> se = sentenseRepository.findById(sentenseId);
		if(se.isPresent()){
			Sentense sentense = se.get();
			sentense.setCommentNum(sentense.getCommentNum() + 1);
			sentenseRepository.saveAndFlush(sentense);
		}
	}
}
