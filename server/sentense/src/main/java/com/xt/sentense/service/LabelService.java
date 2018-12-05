package com.xt.sentense.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xt.sentense.entity.Label;
import com.xt.sentense.entity.LabelRepository;

@Service
public class LabelService {
	@Autowired
	private LabelRepository labelRepository;
	
	public void add(String labels){
		//想去考那，世界，德才
		String[] labelArr = labels.split(",");
		if(labelArr != null){
			for(String label : labelArr){
				label = label.trim();
				Label la = labelRepository.findByName(label);
				if(la == null){
					la = new Label();
					la.setName(label);
					la.setHotNum(0L);
					la.setSentenseNum(0L);
					la.setCreateTime(new Date());
					la.setUpdateTime(new Date());
				}
				la.setSentenseNum(la.getSentenseNum() + 1);
				labelRepository.saveAndFlush(la);
			}
		}
	}
	
	public void hot(String labels){
		//想去考那，世界，德才
		String[] labelArr = labels.split("，");
		if(labelArr != null){
			for(String label : labelArr){
				Label la = labelRepository.findByName(label);
				if(la == null){
					la = new Label();
					la.setName(label);
					la.setHotNum(0L);
					la.setSentenseNum(0L);
					la.setCreateTime(new Date());
					la.setUpdateTime(new Date());
				}
				la.setHotNum(la.getHotNum() + 1);
				labelRepository.saveAndFlush(la);
			}
		}
	}
}
