package com.xt.sentense.controller.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xt.sentense.constant.Res;
import com.xt.sentense.entity.Collection;
import com.xt.sentense.entity.CollectionRepository;
import com.xt.sentense.entity.DianZan;
import com.xt.sentense.service.CollectionService;

/**
 * 收藏api
 * @author XiangTao
 *
 */
@RestController
@RequestMapping("/collection")
public class CollectionApiController {
	@Autowired
	private CollectionRepository collectionRepository;
	@Autowired
	private CollectionService collectionService;
	
	@RequestMapping("/add.api")
	public Res add(Collection collection){
		collection.setCreateTime(new Date());
		collection.setUpdateTime(new Date());
		try{
			List<Collection> cs = collectionRepository.findByUserIdAndSentenseId(collection.getUserId(), collection.getSentenseId());
			if(cs != null && cs.size() > 0){
				return Res.NEW().code(Res.ERROR).msg("你已经收藏过了");
			}else{
				collection = collectionRepository.saveAndFlush(collection);
				if(collection != null){
					return Res.NEW().code(Res.SUCCESS).msg("收藏成功");
				}else{
					return Res.NEW().code(Res.ERROR).msg("收藏失败");
				}
			}
			
			
		}catch(Exception e){
			return Res.NEW().code(Res.ERROR).msg("收藏失败: " + e.getMessage());
		}
	}
	@RequestMapping("/findSentense.api")
	public Res findSentense(int page, int size, Long userId){
		return Res.NEW().code(Res.SUCCESS).msg("ok").data(collectionService.findCollectionSentense(page - 1, size, userId));
	}
	
	/**
	 * 检查用户是否收藏了
	 * @param collection
	 * @return
	 */
	@RequestMapping("/userCollection.api")
	public Res userCollection(Collection collection){
		try{
			List<Collection> ds = collectionRepository.findByUserIdAndSentenseId(collection.getUserId(), collection.getSentenseId());
			if(ds != null && ds.size() > 0){
				return Res.NEW().code(Res.SUCCESS).msg("你已经收藏了").data(true);
			}else{
				return Res.NEW().code(Res.ERROR).msg("没有收藏");
			}
		}catch(Exception e){
			return Res.NEW().code(Res.ERROR).msg("异常: " + e.getMessage());
		}
	}
	@RequestMapping("/delete.api")
	public Res delete(Collection collection){
		try{
			List<Collection> cs = collectionRepository.findByUserIdAndSentenseId(collection.getUserId(), collection.getSentenseId());
			if(cs != null && cs.size() > 0){
				collectionRepository.delete(cs.get(0));
			}
			return Res.NEW().code(Res.SUCCESS).msg("取消收藏成功");
		}catch(Exception e){
			return Res.NEW().code(Res.ERROR).msg("取消收藏失败: " + e.getMessage());
		}
	}
}
