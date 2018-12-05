package com.xt.sentense.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xt.sentense.constant.Const;
import com.xt.sentense.constant.Res;
import com.xt.sentense.entity.Category;
import com.xt.sentense.entity.CategoryRepository;
import com.xt.sentense.entity.Sentense;
import com.xt.sentense.entity.SentenseRepository;
import com.xt.sentense.entity.User;
import com.xt.sentense.entity.UserRepository;
import com.xt.sentense.service.LabelService;
import com.xt.sentense.service.SentenseService;
import com.xt.sentense.service.UserService;
import com.xt.sentense.vo.PageList;
import com.xt.sentense.vo.SentenseVo;
import com.xt.sentense.vo.UserVo;
/**
 * 句子api接口类
 * @author XiangTao
 *
 */
@RestController
@RequestMapping("/sentense")
public class SentenseApiController {
	@Autowired
	private SentenseRepository sentenseRepository;
	@Autowired
	private SentenseService sentenseService;
	@Autowired
	private LabelService labelService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/add.api")
	public Res add(Sentense sentense){
		sentense.setCreateTime(new Date());
		sentense.setUpdateTime(new Date());
		return sentenseService.add(sentense);
	}
	@RequestMapping("/update.api")
	public Res update(Sentense sentense){
		Sentense c = sentenseRepository.getOne(sentense.getId());
		if(c == null){
			return Res.NEW().code(Res.ERROR).msg("数据库没有该条记录");
		}
		c.setCategoryId(sentense.getCategoryId());
		c.setCommentNum(sentense.getCommentNum());
		c.setContent(sentense.getContent());
		c.setFormSource(sentense.getFormSource());
		c.setLabels(sentense.getLabels());
		c.setScenes(sentense.getScenes());
		c.setUserId(sentense.getUserId());
		c.setZanNum(sentense.getZanNum());
		c.setUpdateTime(new Date());
		try{
			c = sentenseRepository.saveAndFlush(c);
			if(c != null){
				return Res.NEW().code(Res.SUCCESS).msg("编辑成功").data(c);
			}else{
				return Res.NEW().code(Res.ERROR).msg("编辑失败").data(c);
			}
		}catch(Exception e){
			e.printStackTrace();
			return Res.NEW().code(Res.ERROR).msg("编辑失败: " + e.getMessage());
		}
	}
	@RequestMapping("/delete.api")
	public Res delete(Long id){
		try{
			sentenseRepository.deleteById(id);
			return Res.NEW().code(Res.SUCCESS).msg("删除成功");
		}catch(Exception e){
			e.printStackTrace();
			return Res.NEW().code(Res.ERROR).msg("删除失败: " + e.getMessage());
		}
	}
	
	@RequestMapping("/finds.api")
	public Res finds(int page, int size){
		return Res.NEW().code(Res.SUCCESS).msg("ok").data(sentenseService.finds(page - 1, size));
	}
	
	@RequestMapping("/search.api")
	public Res search(int page, int size, String scene, String label, String content){
		return Res.NEW().code(Res.SUCCESS).msg("ok").data(sentenseService.search(page - 1, size, scene, label, content));
	}
	
	@RequestMapping("/findByCatgegoryId.api")
	public Res findByCatgegoryId(int page, int size, Long categoryId){
		return Res.NEW().code(Res.SUCCESS).msg("ok").data(sentenseService.findByCatgegoryId(page - 1, size, categoryId));
	}
	
	@RequestMapping("/findByUserId.api")
	public Res findByUserId(int page, int size, Long userId){
		return Res.NEW().code(Res.SUCCESS).msg("ok").data(sentenseService.findByUserId(page - 1, size, userId));
	}
	
	@RequestMapping("/findById.api")
	public Res findById(Long id){
		try{
			SentenseVo u = sentenseService.findById(id);
			if(u != null){
				labelService.hot(u.getLabels()); //给相应的标签加热度
				return Res.NEW().code(Res.SUCCESS).msg("Ok").data(u);
			}else{
				return Res.NEW().code(Res.ERROR).msg("获取失败,没有此记录");
			}
		}catch(Exception e){
			return Res.NEW().code(Res.ERROR).msg("获取失败: " + e.getMessage());
		}
	}
}
