package com.xt.sentense.controller.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xt.sentense.constant.Res;
import com.xt.sentense.entity.Sentense;
import com.xt.sentense.entity.SentenseRepository;
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
	
	
	@RequestMapping("/add.api")
	public Res add(Sentense sentense){
		sentense.setCreateTime(new Date());
		sentense.setUpdateTime(new Date());
		try{
			sentense = sentenseRepository.saveAndFlush(sentense);
			if(sentense != null){
				return Res.NEW().code(Res.SUCCESS).msg("添加成功").data(sentense);
			}else{
				return Res.NEW().code(Res.ERROR).msg("添加失败").data(sentense);
			}
		}catch(Exception e){
			e.printStackTrace();
			if(e.getMessage().matches(".*constraint.*UK.*")){
				return Res.NEW().code(Res.ERROR).msg("添加失败: 该条数据已经存在");
			}else{
				return Res.NEW().code(Res.ERROR).msg("添加失败: " + e.getMessage());
			}
		}
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
		Pageable p = new PageRequest(page - 1, size);
		Page<Sentense> datas = sentenseRepository.findAll(p);
		return Res.NEW().code(Res.SUCCESS).msg("ok").data(datas);
	}
	
	@RequestMapping("/findById.api")
	public Res findById(Long id){
		try{
			Sentense u = sentenseRepository.getOne(id);
			if(u != null){
				return Res.NEW().code(Res.SUCCESS).msg("Ok").data(u);
			}else{
				return Res.NEW().code(Res.ERROR).msg("获取失败,没有此记录");
			}
		}catch(Exception e){
			return Res.NEW().code(Res.ERROR).msg("获取失败: " + e.getMessage());
		}
	}
}
