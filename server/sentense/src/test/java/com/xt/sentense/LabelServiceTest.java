package com.xt.sentense;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xt.sentense.entity.Label;
import com.xt.sentense.entity.LabelRepository;
import com.xt.sentense.service.LabelService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LabelServiceTest{
	@Autowired
	private LabelService labelService;
	@Autowired
	private LabelRepository labelRepository;
	
	@Test
	public void testTest(){
		List<Label> labels = labelRepository.findByNameLike("%,%");
		for(int i = 0; i < labels.size(); i ++){
			String[] str = labels.get(i).getName().split(",");
			for(String st : str){
				labelService.add(st.trim());
			}
			labelRepository.delete(labels.get(i));
			System.out.println(labels.get(i).getName());
		}
		//System.out.println(labels.size());
	}
}
