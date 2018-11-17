package com.xt.sentense.controller.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xt.sentense.constant.Res;
import com.xt.sentense.entity.DianZan;
import com.xt.sentense.entity.DianZanRepository;
import com.xt.sentense.entity.Sentense;
import com.xt.sentense.entity.SentenseRepository;

/**
 * 点赞api
 * @author XiangTao
 *
 */
@RestController
@RequestMapping("/dianZan")
public class DianZanApiController {
	@Autowired
	private DianZanRepository dianzanRepository;
	@Autowired
	private SentenseRepository sentenseRepository;
	
	@RequestMapping("/add.api")
	public Res add(DianZan dianzan){
		dianzan.setCreateTime(new Date());
		dianzan.setUpdateTime(new Date());
		try{
			List<DianZan> ds = dianzanRepository.findByUserIdAndSentenseId(dianzan.getUserId(), dianzan.getSentenseId());
			if(ds != null && ds.size() > 0){
				return Res.NEW().code(Res.ERROR).msg("你已经点过赞了");
			}else{
				dianzan = dianzanRepository.saveAndFlush(dianzan);
				if(dianzan != null){
					Sentense sentense = sentenseRepository.findById(dianzan.getSentenseId()).get();
					sentense.setZanNum(sentense.getZanNum() + 1);
					sentenseRepository.saveAndFlush(sentense);
					return Res.NEW().code(Res.SUCCESS).msg("点赞成功");
				}else{
					return Res.NEW().code(Res.ERROR).msg("点赞失败");
				}
			}
		}catch(Exception e){
			return Res.NEW().code(Res.ERROR).msg("点赞失败: " + e.getMessage());
		}
	}
	
	/**
	 * 检查用户是否赞过了
	 * @param dianzan
	 * @return
	 */
	@RequestMapping("/userZan.api")
	public Res userZan(DianZan dianzan){
		try{
			List<DianZan> ds = dianzanRepository.findByUserIdAndSentenseId(dianzan.getUserId(), dianzan.getSentenseId());
			if(ds != null && ds.size() > 0){
				return Res.NEW().code(Res.SUCCESS).msg("你已经点过赞了").data(true);
			}else{
				return Res.NEW().code(Res.ERROR).msg("没有点赞");
			}
		}catch(Exception e){
			return Res.NEW().code(Res.ERROR).msg("异常: " + e.getMessage());
		}
	}
	
	@RequestMapping("/delete.api")
	public Res delete(DianZan dianzan){
		try{
			List<DianZan> ds = dianzanRepository.findByUserIdAndSentenseId(dianzan.getUserId(), dianzan.getSentenseId());
			if(ds != null && ds.size() > 0){
				dianzanRepository.delete(ds.get(0));
			}
			return Res.NEW().code(Res.SUCCESS).msg("取消点赞成功");
		}catch(Exception e){
			return Res.NEW().code(Res.ERROR).msg("取消点赞失败: " + e.getMessage());
		}
	}
}
