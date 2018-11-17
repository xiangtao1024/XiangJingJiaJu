package com.xt.sentense.controller.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xt.sentense.constant.Res;
import com.xt.sentense.entity.Comment;
import com.xt.sentense.entity.CommentRepository;
import com.xt.sentense.service.CommentService;
import com.xt.sentense.service.SentenseService;
/**
 * 场景api接口类
 * @author XiangTao
 *
 */
@RestController
@RequestMapping("/comment")
public class CommentApiController {
	@Autowired
	private CommentRepository sceneRepository;
	@Autowired
	private CommentService commentService;
	@Autowired
	private SentenseService sentenseService;
	
	@RequestMapping("/add.api")
	public Res add(Comment comment){
		comment.setCreateTime(new Date());
		comment.setUpdateTime(new Date());
		try{
			comment = sceneRepository.saveAndFlush(comment);
			if(comment != null){
				sentenseService.addComment(comment.getSentenseId());
				return Res.NEW().code(Res.SUCCESS).msg("添加成功").data(comment);
			}else{
				return Res.NEW().code(Res.ERROR).msg("添加失败").data(comment);
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
	public Res update(Comment comment){
		Comment c = sceneRepository.getOne(comment.getId());
		if(c == null){
			return Res.NEW().code(Res.ERROR).msg("数据库没有该条记录");
		}
		c.setContent(comment.getContent());
		c.setSentenseId(comment.getSentenseId());
		c.setUserId(comment.getUserId());
		c.setUpdateTime(new Date());
		try{
			c = sceneRepository.saveAndFlush(c);
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
			sceneRepository.deleteById(id);
			return Res.NEW().code(Res.SUCCESS).msg("删除成功");
		}catch(Exception e){
			e.printStackTrace();
			return Res.NEW().code(Res.ERROR).msg("删除失败: " + e.getMessage());
		}
	}
	@RequestMapping("/finds.api")
	public Res finds(int page, int size){
		Pageable p = PageRequest.of(page - 1, size);
		Page<Comment> datas = sceneRepository.findAll(p);
		return Res.NEW().code(Res.SUCCESS).msg("ok").data(datas);
	}
	
	@RequestMapping("/findByUserId.api")
	public Res findByUserId(int page, int size, Long userId){
		return Res.NEW().code(Res.SUCCESS).msg("ok").data(commentService.findByUserId(page - 1, size, userId));
	}
	
	@RequestMapping("/findBySentenseId.api")
	public Res findBySentenseId(int page, int size, Long sentenseId){
		return Res.NEW().code(Res.SUCCESS).msg("ok").data(commentService.findBySentenseId(page - 1, size, sentenseId));
	}
	
	@RequestMapping("/findById.api")
	public Res findById(Long id){
		try{
			Comment u = sceneRepository.getOne(id);
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
