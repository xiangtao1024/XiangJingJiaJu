package com.xt.sentense.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员后台类
 * @author XiangTao
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminAdminController {
	
	@RequestMapping("/index.html")
	public String index(){
		return "admin/admin/index";
	}
}
