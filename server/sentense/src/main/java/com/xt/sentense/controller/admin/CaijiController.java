package com.xt.sentense.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 分类后台类
 * @author XiangTao
 *
 */
@Controller
@RequestMapping("/admin/caiji")
public class CaijiController {
	@RequestMapping("/index.html")
	public String index(){
		return "admin/caiji/index";
	}
}
