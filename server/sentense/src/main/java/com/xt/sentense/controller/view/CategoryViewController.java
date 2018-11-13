package com.xt.sentense.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 分类前台类
 * @author XiangTao
 *
 */
@Controller
@RequestMapping("/category")
public class CategoryViewController {
	
	@RequestMapping("/index.html")
	public String index(){
		return "admin/category/index";
	}
}
