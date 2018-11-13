package com.xt.sentense.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 管理员前台类
 * @author XiangTao
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminViewController {
	@RequestMapping("/login.html")
	public String login(){
		return "admin/admin/login";
	}
}
