package com.xt.sentense.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xt.sentense.entity.Category;
import com.xt.sentense.entity.Comment;
import com.xt.sentense.entity.CommentRepository;
import com.xt.sentense.entity.Sentense;
import com.xt.sentense.entity.SentenseRepository;
import com.xt.sentense.entity.User;
import com.xt.sentense.entity.UserRepository;
import com.xt.sentense.vo.PageList;
import com.xt.sentense.vo.CommentVo;

@Service
public class CommentService {
	@Autowired
	private SentenseRepository sentenseRepository;
	@Autowired
	private UserRepository  userRepository;
	@Autowired
	private CommentRepository commentRepository;
	
	public PageList findByUserId(int page, int size, Long userId){
		Pageable p = PageRequest.of(page, size);
		PageList datas = new PageList(commentRepository.findByUserId(userId, p));
		return bindOtherData(datas);
	}
	public PageList findBySentenseId(int page, int size, Long sentenseId){
		Pageable p = PageRequest.of(page, size);
		PageList datas = new PageList(commentRepository.findBySentenseId(sentenseId, p));
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
		List<Comment> comments = (List<Comment>) datas.getContent();
		List<CommentVo> cvs = new ArrayList<>();
		for(int i = 0; i < comments.size(); i ++){
			Comment cm = comments.get(i);
			Optional<Sentense> sentense = sentenseRepository.findById(cm.getSentenseId());
			Optional<User> user = userRepository.findById(cm.getUserId());
			CommentVo sv = CommentVo.NEW(cm);
			sv.setSentense(sentense.isPresent()?sentense.get():null);
			sv.setUser(user.isPresent()?user.get(): null);
			cvs.add(sv);
		}
		datas.setContent(cvs);
		return datas;
	}
}
