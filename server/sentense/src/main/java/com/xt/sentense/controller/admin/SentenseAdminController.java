package com.xt.sentense.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 句子后台类
 * @author XiangTao
 *
 */
@Controller
@RequestMapping("/admin/sentense")
public class SentenseAdminController {
	@RequestMapping("/index.html")
	public String index(){
		return "admin/sentense/index";
	}
}
