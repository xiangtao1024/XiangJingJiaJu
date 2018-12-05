package com.xt.sentense;

import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.lang.Encoding;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hankcs.hanlp.HanLP;
import com.xt.sentense.entity.Category;
import com.xt.sentense.entity.CategoryRepository;
import com.xt.sentense.entity.Label;
import com.xt.sentense.entity.LabelRepository;
import com.xt.sentense.entity.SceneRepository;
import com.xt.sentense.entity.Sentense;
import com.xt.sentense.entity.SentenseRepository;
import com.xt.sentense.service.JuziDaquanCaijiService;
import com.xt.sentense.service.SentenseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SentenseServiceTest {
	@Autowired
	private SentenseRepository sentenseRepository;
	@Autowired
	private SceneRepository sceneRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private JuziDaquanCaijiService juziDaquanCaijiService;
	@Autowired
	private SentenseService sentenseService;
	
	@Test
	public void testTest(){
		//juziDaquanCaijiService.caiji();
		
	}
}
