package com.xt.sentense.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.xt.sentense.entity.Collection;
import com.xt.sentense.entity.CollectionRepository;
import com.xt.sentense.entity.Sentense;
import com.xt.sentense.entity.SentenseRepository;
import com.xt.sentense.entity.User;
import com.xt.sentense.entity.UserRepository;
import com.xt.sentense.vo.CollectionVo;
import com.xt.sentense.vo.PageList;
import com.xt.sentense.vo.SentenseVo;

@Service
public class CollectionService {
	@Autowired
	private CollectionRepository collectionRepository;
	@Autowired
	private SentenseRepository sentenseRepository;
	@Autowired
	private UserRepository  userRepository;
	@Autowired
	private SentenseService sentenseService;
	
	public PageList finds(int page, int size, Long userId){
		Sort sort = new Sort(Direction.DESC, "createTime");
		Pageable pageable = PageRequest.of(page, size, sort);
		PageList datas = new PageList(collectionRepository.findByUserId(userId, pageable));
		return bindOtherData(datas);
	}
	/**
	 * 获取userId收藏的句子
	 * @param page
	 * @param size
	 * @param userId
	 * @return
	 */
	public PageList findCollectionSentense(int page, int size, Long userId){
		Sort sort = new Sort(Direction.DESC, "createTime");
		Pageable pageable = PageRequest.of(page, size, sort);
		PageList datas = new PageList(collectionRepository.findByUserId(userId, pageable));
		List<Collection> cs = (List<Collection>) datas.getContent();
		List<SentenseVo> svs = new ArrayList<>();
		for(int i = 0; i < cs.size(); i ++){
			svs.add(sentenseService.findById(cs.get(i).getSentenseId()));
		}
		datas.setContent(svs);
		return datas;
	}
	
	public PageList bindOtherData(PageList datas){
		List<Collection> collections = (List<Collection>) datas.getContent();
		List<CollectionVo> collectionVos = new ArrayList<CollectionVo>();
		for(Collection collection : collections){
			CollectionVo cv = CollectionVo.NEW(collection);
			Optional<Sentense> sentense = sentenseRepository.findById(collection.getSentenseId());
			Optional<User> user = userRepository.findById(collection.getUserId());
			cv.setSentense(sentense.get());
		}
		return datas;
	}
}
