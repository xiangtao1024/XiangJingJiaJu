package com.xt.sentense.service;

import java.util.Optional;

import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xt.sentense.entity.CollectionRepository;
import com.xt.sentense.entity.SentenseRepository;
import com.xt.sentense.entity.User;
import com.xt.sentense.entity.UserRepository;
import com.xt.sentense.vo.UserVo;
/**
 * 用户service
 * @author XiangTao
 *
 */
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SentenseRepository sentenseRepository;
	@Autowired
	private CollectionRepository collectionRepository;
	/**
	 * 给用户userId原有的积分上加grade
	 * @param userid
	 * @param grade
	 */
	public void addGrade(Long userid, int grade){
		Optional<User> user = userRepository.findById(userid);
		if(user.isPresent()){
			User u = user.get();
			u.setGrade(u.getGrade() + grade);
			userRepository.saveAndFlush(u);
		}
	}
	
	/**
	 * 获取去用户
	 * @param userid
	 * @param grade
	 */
	public UserVo findById(Long userid){
		Optional<User> user = userRepository.findById(userid);
		if(user.isPresent()){
			UserVo u = UserVo.NEW(user.get());
			u.setJukuNum(sentenseRepository.countByUserId(u.getId()));
			u.setCollectionNum(collectionRepository.countByUserId(u.getId()));
			return u;
		}else{
			return null;
		}
	}
}
